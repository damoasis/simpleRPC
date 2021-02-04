package com.sm.rpc.transport.command;

import java.nio.charset.StandardCharsets;

/**
 * @project rpc-netty
 * @description:
 * @author: liumeng
 * @create: 2021-02-04 12:06
 */

public class ResponseHeader extends Header {
    private int code;
    private String error;

    public ResponseHeader(int type, int version, int requestId, Throwable throwable) {
        this(type, version, requestId, Code.UNKNOWN_ERROR.getCode(), throwable.getMessage());
    }

    public ResponseHeader(int tyep, int version, int requestId) {
        this(tyep, version, requestId, Code.SUCCESS.getCode(), null);
    }

    public ResponseHeader(int type, int version, int requestId, int code, String error) {
        super(requestId, version, type);
        this.code = code;
        this.error = error;
    }

    @Override
    public int length() {
        return super.length()+Integer.BYTES+(error==null?0:error.getBytes(StandardCharsets.UTF_8).length);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
