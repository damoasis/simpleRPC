package com.sm.rpc.transport;

import com.sm.rpc.transport.RequestHandlerRegistry;

/**
 * @project rpc-netty
 * @description:
 * @author: liumeng
 * @create: 2021-02-04 12:35
 */

public interface TransportServer {
    void start(RequestHandlerRegistry requestHandlerRegistry,int port) throws Exception;
    void stop();
}
