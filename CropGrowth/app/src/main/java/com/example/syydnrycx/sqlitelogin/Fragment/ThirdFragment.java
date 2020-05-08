package com.example.syydnrycx.sqlitelogin.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.syydnrycx.sqlitelogin.Activity.LoginActivity;
import com.example.syydnrycx.sqlitelogin.Activity.MainActivity;
import com.example.syydnrycx.sqlitelogin.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ThirdFragment<markDates> extends Fragment {
    private Button btnCalendar;
    private Button btnalarms;
    private ThirdViewModel mViewModel;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.third_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ThirdViewModel.class);
        // TODO: Use the ViewModel
        Button btnalarms = (Button)getActivity().findViewById(R.id.btn_alarm);
        btnalarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alarms = new Intent(AlarmClock.ACTION_SET_ALARM);
                startActivity(alarms);
            }
        });

        Button btnCalendar = (Button)getActivity().findViewById(R.id.btn_Calendar);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sdk = android.os.Build.VERSION.SDK_INT;
                int ICE_CREAM_BUILD_ID = android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;
                if(sdk < ICE_CREAM_BUILD_ID) {
                    // all SDK below ice cream sandwich
                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setType("vnd.android.cursor.item/event");
                    startActivity(intent);
                } else {
                    // ice cream sandwich and above
                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setType("vnd.android.cursor.item/event");;
                    startActivity(intent);
                }
            }
        });

    }


}