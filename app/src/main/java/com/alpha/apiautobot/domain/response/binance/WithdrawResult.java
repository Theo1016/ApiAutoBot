package com.alpha.apiautobot.domain.response.binance;

/**
 * A withdraw result that was done to a Binance account.
 */
public class WithdrawResult {

    /**
     * Withdraw message.
     */
    private String msg;

    /**
     * Withdraw success.
     */
    private boolean success;

    /**
     * Withdraw id.
     */
    private String id;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}
