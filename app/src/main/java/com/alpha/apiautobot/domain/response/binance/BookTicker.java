package com.alpha.apiautobot.domain.response.binance;

/**
 * Represents the best price/qty on the order book for a given symbol.
 */
public class BookTicker {

  /**
   * Ticker symbol.
   */
  private String symbol;

  /**
   * Bid price.
   */
  private String bidPrice;

  /**
   * Bid quantity
   */
  private String bidQty;

  /**
   * Ask price.
   */
  private String askPrice;

  /**
   * Ask quantity.
   */
  private String askQty;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(String bidPrice) {
    this.bidPrice = bidPrice;
  }

  public String getBidQty() {
    return bidQty;
  }

  public void setBidQty(String bidQty) {
    this.bidQty = bidQty;
  }

  public String getAskPrice() {
    return askPrice;
  }

  public void setAskPrice(String askPrice) {
    this.askPrice = askPrice;
  }

  public String getAskQty() {
    return askQty;
  }

  public void setAskQty(String askQty) {
    this.askQty = askQty;
  }

}
