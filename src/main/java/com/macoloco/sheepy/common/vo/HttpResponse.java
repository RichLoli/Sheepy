package com.macoloco.sheepy.common.vo;

/**
 * @author Aya
 * @date 2022/1/16
 */
public class HttpResponse<T> {

    private String code;

    private String message;

    private T data;

    private HttpResponse() {
        this.code = HttpStatusCode.SUCCESS.getCode();
        this.message = HttpStatusCode.SUCCESS.getMessage();
    }

    public static <T> HttpResponse<T> build() {
        return new HttpResponse<>();
    }

    public HttpResponse<T> code(HttpStatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        return this;
    }

    public HttpResponse<T> data(T data) {
        this.data = data;
        return this;
    }

}
