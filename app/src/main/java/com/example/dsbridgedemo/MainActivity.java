package com.example.dsbridgedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView callJs;
    private TextView callNative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callJs = findViewById(R.id.callJs);
        callJs.setOnClickListener(this);
        callNative = findViewById(R.id.callNative);
        callNative.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.callJs:
                startActivity(new Intent(MainActivity.this, CallJavascriptActivity.class));
                break;
            case R.id.callNative:
                startActivity(new Intent(MainActivity.this, JavascriptCallNativeActivity.class));
                break;
        }
    }
}
