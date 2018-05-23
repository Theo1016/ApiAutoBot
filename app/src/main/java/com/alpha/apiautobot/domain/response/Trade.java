package com.alpha.apiautobot.domain.response;

/**
 * Represents an executed trade.
 */
public class Trade {

  /**
   * Trade id.
   */
  private Long id;

  /**
   * Price.
   */
  private String price;

  /**
   * Quantity.
   */
  private String qty;

  /**
   * Commission.
   */
  private String commission;

  /**
   * Asset on which commission is taken
   */
  private String commissionAsset;

  /**
   * Trade execution time.
   */
  private long time;

  private boolean buyer;

  private boolean maker;

  private boolean bestMatch;

  private String orderId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTradeId(Long id) {
    if (this.id == null) {
      setId(id);
    }
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getQty() {
    return qty;
  }

  public void setQty(String qty) {
    this.qty = qty;
  }

  public String getCommission() {
    return commission;
  }

  public void setCommission(String commission) {
    this.commission = commission;
  }

  public String getCommissionAsset() {
    return commissionAsset;
  }

  public void setCommissionAsset(String commissionAsset) {
    this.commissionAsset = commissionAsset;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public boolean isBuyer() {
    return buyer;
  }

  public void setBuyer(boolean buyer) {
    this.buyer = buyer;
  }

  public boolean isMaker() {
    return maker;
  }

  public void setMaker(boolean maker) {
    this.maker = maker;
  }

  public boolean isBestMatch() {
    return bestMatch;
  }

  public void setBestMatch(boolean bestMatch) {
    this.bestMatch = bestMatch;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

}
