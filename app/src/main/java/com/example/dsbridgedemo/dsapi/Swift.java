package com.example.dsbridgedemo.dsapi;

import android.webkit.JavascriptInterface;

import wendu.dsbridge.CompletionHandler;


/**
 * class description here
 *
 * @author xinyuliu
 * @version 1.1.0
 * @since 2019-01-22 15：20：22
 */
public class Swift {

    @JavascriptInterface
    public String testSyn(Object msg) {
        return msg + "［swift test syn］";
    }

    @JavascriptInterface
    public void testAsyn(Object msg, CompletionHandler<String> handler) {
        handler.complete(msg + " [ asyn call]");
    }
}
