package com.alpha.apiautobot.domain.response.binance;

import java.util.List;

/**
 * History of account deposits.
 *
 * @see Deposit
 */
public class DepositHistory {

  private List<Deposit> depositList;

  private boolean success;

  private String msg;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public List<Deposit> getDepositList() {
    return depositList;
  }

  public void setDepositList(List<Deposit> depositList) {
    this.depositList = depositList;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

}
