package com.zt.javastudy.async;

public interface IClientCallback {

    void onSuccess(String result);

    void onFail(Exception e);

    void onConnect();
}
