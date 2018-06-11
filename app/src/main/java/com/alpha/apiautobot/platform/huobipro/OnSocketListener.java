package com.alpha.apiautobot.platform.huobipro;

import okhttp3.Response;
import okio.ByteString;

/**
 * socket消息回调
 */
public interface OnSocketListener {
    public void onOpen(Response response);

    public void onMessage(String text);

    public void onClosed(int code, String reason);

    public void onFailure(Throwable t, Response response);
}
