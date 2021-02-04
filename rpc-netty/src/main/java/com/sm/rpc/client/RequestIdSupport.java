package com.sm.rpc.client;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @project rpc-netty
 * @description: 请求Id生成类
 * @author: liumeng
 * @create: 2021-02-04 12:55
 */

public class RequestIdSupport {
    private final static AtomicInteger nextRequestId = new AtomicInteger(0);

    public static int next() {
        return nextRequestId.getAndIncrement();
    }
}
