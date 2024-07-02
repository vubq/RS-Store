package com.vubq.rsstore.utils;

import lombok.Getter;

import java.util.List;

@Getter
public class DataTableResponse {

    public static final int CODE_SUCCESS = 0;
    public static final int CODE_PERMISSION = 1;
    public static final int CODE_NOT_FOUND = 2;
    public static final int CODE_ALREADY_EXIST = 3;
    public static final int CODE_BUSINESS = 4;
    public static final int CODE_EXPIRED = 8888;
    public static final int CODE_INTERNAL_ERROR = 9999;

    protected Integer code;

    public DataTableResponse ok() {
        this.code = CODE_SUCCESS;
        return this;
    }

    public DataTableResponse code(int code) {
        this.code = code;
        return this;
    }

    private List<?> items;

    private Long totalRows;

    public static DataTableResponse build() {
        return new DataTableResponse();
    }

    public DataTableResponse items(List<?> items) {
        this.items = items;
        return this;
    }

    public DataTableResponse totalRows(Long totalRows) {
        this.totalRows = totalRows;
        return this;
    }
}
