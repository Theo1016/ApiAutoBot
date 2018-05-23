package com.alpha.apiautobot.domain.response;

/**
 * An aggregated trade event for a symbol.
 */
public class AggTrade {

  private long aggregatedTradeId;

  private String price;

  private String quantity;

  private long firstBreakdownTradeId;

  private long lastBreakdownTradeId;

  private long tradeTime;

  private boolean isBuyerMaker;

  public long getAggregatedTradeId() {
    return aggregatedTradeId;
  }

  public void setAggregatedTradeId(long aggregatedTradeId) {
    this.aggregatedTradeId = aggregatedTradeId;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public long getFirstBreakdownTradeId() {
    return firstBreakdownTradeId;
  }

  public void setFirstBreakdownTradeId(long firstBreakdownTradeId) {
    this.firstBreakdownTradeId = firstBreakdownTradeId;
  }

  public long getLastBreakdownTradeId() {
    return lastBreakdownTradeId;
  }

  public void setLastBreakdownTradeId(long lastBreakdownTradeId) {
    this.lastBreakdownTradeId = lastBreakdownTradeId;
  }

  public long getTradeTime() {
    return tradeTime;
  }

  public void setTradeTime(long tradeTime) {
    this.tradeTime = tradeTime;
  }

  public boolean isBuyerMaker() {
    return isBuyerMaker;
  }

  public void setBuyerMaker(boolean buyerMaker) {
    isBuyerMaker = buyerMaker;
  }

}
