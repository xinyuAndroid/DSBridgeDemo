package com.example.dsbridgedemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.dsbridgedemo.dsapi.JsApi;
import com.example.dsbridgedemo.dsapi.JsEchoApi;
import com.example.dsbridgedemo.dsapi.Swift;

import wendu.dsbridge.DWebView;

public class JavascriptCallNativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javascript_call_native);

        DWebView dwebView = findViewById(R.id.webview);
        DWebView.setWebContentsDebuggingEnabled(true);
        dwebView.addJavascriptObject(new JsApi(), null);
        dwebView.addJavascriptObject(new Swift(), "swift");
        dwebView.addJavascriptObject(new JsEchoApi(), "echo");
        dwebView.setWebChromeClient(new TestWebChromeClient());
//        dwebView.loadUrl("file:///android_asset/js-call-native.html");
        dwebView.loadUrl("https://tm.ofashion.com.cn/web_native_bridge/test.html");
    }

    class TestWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("对话框")
                    .setMessage(message)
                    .setPositiveButton("确定", null);

//            // 不需要绑定按键事件
//            // 屏蔽keycode等于84之类的按键
//            builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
//                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                    Log.v("onJsAlert", "keyCode==" + keyCode + "event=" + event);
//                    return true;
//                }
//            });
            // 禁止响应按back键的事件
//            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }
    }

}
