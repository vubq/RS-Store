package com.vubq.rsstore.utils;

import lombok.Getter;

@Getter
public class Response {

    public static final int CODE_SUCCESS = 0;
    public static final int CODE_PERMISSION = 1;
    public static final int CODE_NOT_FOUND = 2;
    public static final int CODE_ALREADY_EXIST = 3;
    public static final int CODE_BUSINESS = 4;
    public static final int CODE_EXPIRED = 8888;
    public static final int CODE_INTERNAL_ERROR = 9999;

    protected Integer code;

    public Response code(int code) {
        this.code = code;
        return this;
    }

    private Object data;

    public static Response build() {
        return new Response();
    }

    public Response data(Object data) {
        this.data = data;
        return this;
    }

    public Response ok() {
        Response response = new Response();
        response.code = CODE_SUCCESS;
        return response;
    }
}
