package com.alpha.apiautobot.domain.response.binance;

/**
 * A deposit that was done to a Binance account.
 */
public class Deposit {

  /**
   * Amount deposited.
   */
  private String amount;

  /**
   * Symbol.
   */
  private String asset;

  /**
   * Deposit time.
   */
  private String insertTime;

  /**
   * Transaction id
   */
  private String txId;

  /**
   * (0:pending,1:success)
   */
  private int status;

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getAsset() {
    return asset;
  }

  public void setAsset(String asset) {
    this.asset = asset;
  }

  public String getInsertTime() {
    return insertTime;
  }

  public void setInsertTime(String insertTime) {
    this.insertTime = insertTime;
  }

  public String getTxId() {
    return txId;
  }

  public void setTxId(String txId) {
    this.txId = txId;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

}
