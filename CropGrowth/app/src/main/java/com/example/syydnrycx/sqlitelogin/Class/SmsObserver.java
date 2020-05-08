package com.example.syydnrycx.sqlitelogin.Class;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.telephony.SmsMessage;
import android.content.ClipboardManager;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.example.syydnrycx.sqlitelogin.Activity.LoginActivity;
import com.example.syydnrycx.sqlitelogin.Activity.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 短信监听
 * @author
 *
 */
public class SmsObserver extends ContentObserver {

    private Context mContext;
    private Handler mHandler;

    public SmsObserver(Context context, Handler handler) {
        super(handler);
        mContext = context;
        mHandler = handler;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);

        Log.e("DEBUG", "SMS has changed!");
        Log.e("DEBUG", uri.toString());

        String code = "";

        if (uri.toString().equals("content://sms/raw")) {
            return;
        }

        Uri inboxUri = Uri.parse("content://sms/inbox");
        Cursor c = mContext.getContentResolver().query(inboxUri, null, null, null, "date desc");
        if (c != null) {
            if (c.moveToFirst()) {
                String address = c.getString(c.getColumnIndex("address"));
                String body = c.getString(c.getColumnIndex("body"));

                Log.e("DEBUG", "发件人为：" + address + " " + "短信内容为：" + body);

                Pattern pattern = Pattern.compile("(\\d{4,6})");
                Matcher matcher = pattern.matcher(body);

                if (matcher.find()) {
                    code = matcher.group(0);
                    Log.e("DEBUG", "code is " + code);

                    ClipboardManager cmb = (ClipboardManager)mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                    cmb.setText(code);

                    mHandler.obtainMessage(LoginActivity.MSG_RECEIVED_CODE, code).sendToTarget();
                }

            }
            c.close();
        }

    }

}
