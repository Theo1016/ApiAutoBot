package com.alpha.apiautobot.domain.response.binance;

import java.util.List;

/**
 * Current exchange trading rules and symbol information.
 * https://github.com/binance-exchange/binance-official-api-docs/blob/master/rest-api.md
 */
public class ExchangeInfo {

  private String timezone;

  private Long serverTime;

  private List<RateLimit> rateLimits;

  // private List<String> exchangeFilters;

  private List<SymbolInfo> symbols;

  public String getTimezone() {
    return timezone;
  }

  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  public Long getServerTime() {
    return serverTime;
  }

  public void setServerTime(Long serverTime) {
    this.serverTime = serverTime;
  }

  public List<RateLimit> getRateLimits() {
    return rateLimits;
  }

  public void setRateLimits(List<RateLimit> rateLimits) {
    this.rateLimits = rateLimits;
  }

  public List<SymbolInfo> getSymbols() {
    return symbols;
  }

  public void setSymbols(List<SymbolInfo> symbols) {
    this.symbols = symbols;
  }

//  /**
//   * @param symbol the symbol to obtain information for (e.g. ETHBTC)
//   * @return symbol exchange information
//   */
//  @TargetApi(Build.VERSION_CODES.N)
//  @RequiresApi(api = Build.VERSION_CODES.N)
//  public SymbolInfo getSymbolInfo(String symbol) throws Throwable {
//    return symbols.stream().filter(symbolInfo -> symbolInfo.getSymbol().equals(symbol))
//        .findFirst()
//        .orElseThrow(() -> new BinanceApiException("Unable to obtain information for symbol " + symbol));
//  }

}
