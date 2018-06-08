package com.alpha.apiautobot.domain.response.binance.rest;

/**
 * A deposit address for a given asset.
 */
public class DepositAddress {

  private String address;

  private boolean success;

  private String addressTag;

  private String asset;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getAddressTag() {
    return addressTag;
  }

  public void setAddressTag(String addressTag) {
    this.addressTag = addressTag;
  }

  public String getAsset() {
    return asset;
  }

  public void setAsset(String asset) {
    this.asset = asset;
  }

}