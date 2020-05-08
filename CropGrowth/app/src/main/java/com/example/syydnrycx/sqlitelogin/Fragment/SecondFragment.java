package com.example.syydnrycx.sqlitelogin.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.syydnrycx.sqlitelogin.R;

public class SecondFragment extends Fragment {

    private SecondViewModel mViewModel;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }
    WebView webView = null;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.second_fragment, container, false);

        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SecondViewModel.class);
        // TODO: Use the ViewModel
        webView = getActivity().findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);// 让WebView能够执行javaScript
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);// 让JavaScript可以自动打开windows
        settings.setAppCacheEnabled(true);      // 设置缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置缓存模式,一共有四种模式
        settings.setSupportZoom(true);// 支持缩放(适配到当前屏幕)
        settings.setUseWideViewPort(true);      // 将图片调整到合适的大小
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 支持内容重新布局,一共有四种方式 默认的是NARROW_COLUMNS
        settings.setDisplayZoomControls(true);   // 设置可以被显示的屏幕控制
        settings.setDefaultFontSize(12);   // 设置默认字体大小
        webView.loadUrl("http://www.baidu.com");

        //实现：WebView里的链接，都在自身打开，不调用系统浏览器
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });

        //实现：按手机回退键，如果浏览器有上一个网页，则返回上一个网页
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack() ) {
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
    }

}

