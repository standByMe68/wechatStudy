package com.standbyme.wechatoa.error;

import java.net.URI;

/**
 * @author jcf
 * 用于存储异常类所需要的变量
 */

public class ErrorConstants {

    /**
     * 其他常量
     */
    public static final String PROBLEM_BASE_URL = "http://www.jhipster.tech/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");

    /**
     * 异常类文字
     */
    public static final String GET_ACCESS_TOKEN_ERROR = "get_wechatOA_access_token_is_error";
    public static final String WECHAT_INTERFACE_ERROR = "wechatErr";

}
