package com.example.dsbridgedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import wendu.dsbridge.DWebView;
import wendu.dsbridge.OnReturnValue;

public class CallJavascriptActivity extends AppCompatActivity implements View.OnClickListener {

    private DWebView dWebView;
    private TextView multiplyValue;
    private TextView append;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_javascript);

        DWebView.setWebContentsDebuggingEnabled(true);
        dWebView = findViewById(R.id.webview);
//        dWebView.loadUrl("file:///android_asset/native-call-js.html");
        dWebView.loadUrl("https://tm.ofashion.com.cn/web_native_bridge/test.html");

        multiplyValue = findViewById(R.id.multiplyValue);
        multiplyValue.setOnClickListener(this);

        append = findViewById(R.id.append);
        append.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.multiplyValue:
                dWebView.callHandler("addValue", new Object[]{3, 4}, new OnReturnValue<Integer>() {
                    @Override
                    public void onValue(Integer retValue) {
                        showToast(retValue);
                    }
                });
                break;
            case R.id.append:
                dWebView.callHandler("append", new Object[]{"I", "love", "you"}, new OnReturnValue<String>() {
                    @Override
                    public void onValue(String retValue) {
                        showToast(retValue);
                    }
                });
                break;
        }
    }

    void showToast(Object o) {
        Toast.makeText(this, o.toString(), Toast.LENGTH_SHORT).show();
    }
}
