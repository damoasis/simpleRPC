package com.sm.rpc.client;

import com.sm.rpc.transport.Transport;

/**
 * @project rpc-netty
 * @description:
 * @author: liumeng
 * @create: 2021-02-04 12:57
 */

public class DynamicStubFactory implements StubFactory{
    @Override
    public <T> T createStub(Transport transport, Class<T> serviceClass) {
        return null;
    }
}
