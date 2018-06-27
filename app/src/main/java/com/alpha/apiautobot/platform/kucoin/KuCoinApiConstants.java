package com.alpha.apiautobot.platform.kucoin;

/**
 * Created by Theo
 */
public class KuCoinApiConstants {

  /**
   * REST API base URL.
   */
  public static final String API_BASE_URL = "https://api.kucoin.com";

  /**
   * Streaming API base URL.
   */
  public static final String WS_API_BASE_URL = "";

  /**
   * Asset info base URL.
   */
  public static final String ASSET_INFO_API_BASE_URL = "";

  /**
   * HTTP Header to be used for API-KEY authentication.
   */
  public static final String API_KEY_HEADER = "KC-API-KEY";

  public static final String API_TIMESTAMP="KC-API-NONCE";

  public static final String API_SIGNATURE="KC-API-SIGNATURE";

  public static final String API_LANGUAGE="Accept-Language";

  public static String SIGNATURE;

  public static String HEADER_LANGUAGE="zh_CN";


}
