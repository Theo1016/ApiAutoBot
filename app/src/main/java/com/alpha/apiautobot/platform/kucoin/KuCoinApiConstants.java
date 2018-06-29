package com.alpha.apiautobot.platform.kucoin;

/**
 * Constants used throughout Binance's API.
 */
public class KuCoinApiConstants {

  /**
   * REST API base URL.
   */
  public static final String API_BASE_URL = "https://api.kucoin.com";

  /**
   * Streaming API base URL.
   */
  public static final String WS_API_BASE_URL = "wss://stream.binance.com:9443/ws";

  /**
   * Asset info base URL.
   */
  public static final String ASSET_INFO_API_BASE_URL = "https://www.binance.com/";

  /**
   * HTTP Header to be used for API-KEY authentication.
   */
  public static final String API_KEY_HEADER = "KC-API-KEY";

  public static final String API_TIMESTAMP="KC-API-NONCE";

  public static final String API_SIGNATURE="KC-API-SIGNATURE";

  public static final String API_LANGUAGE="Accept-Language";

  public static String SIGNATURE;

  public static String HEADER_LANGUAGE="zh_CN";

  /**
   * Decorator to indicate that an endpoint requires an API key.
   */
  public static final String ENDPOINT_SECURITY_TYPE_APIKEY = "APIKEY";
  public static final String ENDPOINT_SECURITY_TYPE_APIKEY_HEADER = ENDPOINT_SECURITY_TYPE_APIKEY + ": #";

  /**
   * Decorator to indicate that an endpoint requires a signature.
   */
  public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "SIGNED";
  public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = ENDPOINT_SECURITY_TYPE_SIGNED + ": #";

  /**
   * Default receiving window.
   */
  public static final long DEFAULT_RECEIVING_WINDOW = 6_000_000L;

}
