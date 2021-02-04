package com.sm.rpc.serialize;

/**
 * @project rpc-netty
 * @description: 序列化异常类
 * @author: liumeng
 * @create: 2021-02-04 17:04
 */

public class SerializeException extends RuntimeException {
    public SerializeException(String message) {
        super(message);
    }

    public SerializeException(Throwable throwable) {
        super(throwable);
    }
}
