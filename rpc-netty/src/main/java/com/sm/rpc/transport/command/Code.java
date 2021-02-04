package com.sm.rpc.transport.command;

import java.util.HashMap;
import java.util.Map;

/**
 * @project rpc-netty
 * @description:
 * @author: liumeng
 * @create: 2021-02-04 11:30
 */

public enum Code {
    SUCCESS(0, "Success"),
    NO_PROVIDER(-2, "No Provider"),
    UNKNOWN_ERROR(-1, "Unknown Error");

    private static Map<Integer, Code> codes = new HashMap<>();
    private int code;
    private String message;

    static {
        for (Code code : Code.values()) {
            codes.put(code.code, code);
        }
    }

    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Code valueOf(int code) {
        return codes.get(code);
    }

    public int getCode(){
        return code;
    }

    public String getMessage(Object... args) {
        if (args.length < 1) {
            return message;
        }
        return String.format(message, args);
    }
}
