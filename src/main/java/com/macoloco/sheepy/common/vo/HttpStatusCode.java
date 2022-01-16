package com.macoloco.sheepy.common.vo;

/**
 * @author Aya
 * @date 2022/1/16
 */
public enum HttpStatusCode {

    /**
     * 请求成功
     */
    SUCCESS("0", "SUCCESS"),
    ERROR("500", "ERROR"),

    ;
    private String code;
    private String message;

    HttpStatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
