package com.sm.rpc.client;

import com.sm.rpc.transport.Transport;

/**
 * @project rpc-netty
 * @description:
 * @author: liumeng
 * @create: 2021-02-04 12:54
 */

public interface ServiceStub {
    void setTransport(Transport transport);
}
