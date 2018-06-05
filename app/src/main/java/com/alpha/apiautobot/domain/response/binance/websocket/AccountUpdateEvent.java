package com.alpha.apiautobot.domain.response.binance.websocket;

import com.alpha.apiautobot.domain.response.binance.rest.AssetBalance;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

/**
 * Account update event which will reflect the current position/balances of the account.
 *
 * This event is embedded as part of a user data update event.
 *
 * @see UserDataUpdateEvent
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountUpdateEvent {

  @JsonProperty("e")
  private String eventType;

  @JsonProperty("E")
  private long eventTime;

  @JsonProperty("B")
  @JsonDeserialize(contentUsing = AssetBalanceDeserializer.class)
  private List<AssetBalance> balances;

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public long getEventTime() {
    return eventTime;
  }

  public void setEventTime(long eventTime) {
    this.eventTime = eventTime;
  }

  public List<AssetBalance> getBalances() {
    return balances;
  }

  public void setBalances(List<AssetBalance> balances) {
    this.balances = balances;
  }


}
