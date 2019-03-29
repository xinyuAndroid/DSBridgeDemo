package com.example.dsbridgedemo.dsapi;

import android.os.CountDownTimer;
import android.webkit.JavascriptInterface;

import org.json.JSONException;
import org.json.JSONObject;

import wendu.dsbridge.CompletionHandler;


/**
 * class description here
 *
 * @author xinyuliu
 * @version 1.1.0
 * @since 2019-01-22 15：20：22
 */
public class JsApi {

    //同步API
    @JavascriptInterface
    public String getModel(Object msg) {
        if (msg.equals("1")) {
            return "手机型号：" + android.os.Build.MODEL;
        } else {
            return "获取失败";
        }
    }

    @JavascriptInterface
    public void getSystemVersion(Object msg, CompletionHandler<String> handler) {
        if ("1".equals(msg)) {
            handler.complete("系统版本号：" + android.os.Build.VERSION.RELEASE);
        } else {
            handler.complete("获取失败");
        }
    }


    //测试api
    @JavascriptInterface
    public String testSyn(Object msg) {
        return msg + "［syn call］";
    }

    @JavascriptInterface
    public void testAsyn(Object msg, CompletionHandler<String> handler) {
        handler.complete(msg + " [ asyn call]");
    }

    @JavascriptInterface
    public String testNoArgSyn(Object arg) throws JSONException {
        return "testNoArgSyn called [ syn call]";
    }

    @JavascriptInterface
    public void testNoArgAsyn(Object arg, CompletionHandler<String> handler) {
        handler.complete("testNoArgAsyn   called [ asyn call]");
    }

    //@JavascriptInterface
    public String testNever(Object arg) throws JSONException {
        JSONObject jsonObject = (JSONObject) arg;
        return jsonObject.getString("msg") + "[ never call]";
    }

    @JavascriptInterface
    public void callProgress(Object args, final CompletionHandler<Integer> handler) {

        new CountDownTimer(16000, 1000) {
            int i = 15;

            @Override
            public void onTick(long millisUntilFinished) {
                handler.setProgressData((i--));

            }

            @Override
            public void onFinish() {
                handler.complete(0);

            }
        }.start();
    }
}
