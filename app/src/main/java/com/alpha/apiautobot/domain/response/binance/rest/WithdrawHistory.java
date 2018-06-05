package com.alpha.apiautobot.domain.response.binance.rest;

import java.util.List;

/**
 * History of account withdrawals.
 *
 * @see Withdraw
 */
public class WithdrawHistory {

  private List<Withdraw> withdrawList;

  private boolean success;

  public List<Withdraw> getWithdrawList() {
    return withdrawList;
  }

  public void setWithdrawList(List<Withdraw> withdrawList) {
    this.withdrawList = withdrawList;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

}
