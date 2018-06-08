package com.alpha.apiautobot.domain.response.binance.rest;

import java.util.List;

/**
 * Account information.
 */
public class Account {

  /**
   * Maker commission.
   */
  private int makerCommission;

  /**
   * Taker commission.
   */
  private int takerCommission;

  /**
   * Buyer commission.
   */
  private int buyerCommission;

  /**
   * Seller commission.
   */
  private int sellerCommission;

  /**
   * Whether or not this account can trade.
   */
  private boolean canTrade;

  /**
   * Whether or not it is possible to withdraw from this account.
   */
  private boolean canWithdraw;

  /**
   * Whether or not it is possible to deposit into this account.
   */
  private boolean canDeposit;

  /**
   * Last account update time.
   */
  private long updateTime;

  /**
   * List of asset balances of this account.
   */
  private List<AssetBalance> balances;

  public int getMakerCommission() {
    return makerCommission;
  }

  public void setMakerCommission(int makerCommission) {
    this.makerCommission = makerCommission;
  }

  public int getTakerCommission() {
    return takerCommission;
  }

  public void setTakerCommission(int takerCommission) {
    this.takerCommission = takerCommission;
  }

  public int getBuyerCommission() {
    return buyerCommission;
  }

  public void setBuyerCommission(int buyerCommission) {
    this.buyerCommission = buyerCommission;
  }

  public int getSellerCommission() {
    return sellerCommission;
  }

  public void setSellerCommission(int sellerCommission) {
    this.sellerCommission = sellerCommission;
  }

  public boolean isCanTrade() {
    return canTrade;
  }

  public void setCanTrade(boolean canTrade) {
    this.canTrade = canTrade;
  }

  public boolean isCanWithdraw() {
    return canWithdraw;
  }

  public void setCanWithdraw(boolean canWithdraw) {
    this.canWithdraw = canWithdraw;
  }

  public boolean isCanDeposit() {
    return canDeposit;
  }

  public void setCanDeposit(boolean canDeposit) {
    this.canDeposit = canDeposit;
  }

  public long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(long updateTime) {
    this.updateTime = updateTime;
  }

  public List<AssetBalance> getBalances() {
    return balances;
  }

  public void setBalances(List<AssetBalance> balances) {
    this.balances = balances;
  }

  /**
   * Returns the asset balance for a given symbol.
   *
   * @param symbol asset symbol to obtain the balances from
   * @return an asset balance for the given symbol which can be 0 in case the symbol has no balance in the account
   */
  public AssetBalance getAssetBalance(String symbol) {
    for (AssetBalance assetBalance : balances) {
      if (symbol.equals(assetBalance.getAsset())) {
        return assetBalance;
      }
    }
    AssetBalance emptyBalance = new AssetBalance();
    emptyBalance.setAsset(symbol);
    emptyBalance.setFree("0");
    emptyBalance.setLocked("0");
    return emptyBalance;
  }

}
