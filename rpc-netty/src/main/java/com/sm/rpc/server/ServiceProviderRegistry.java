package com.sm.rpc.server;

/**
 * @project rpc-netty
 * @description:
 * @author: liumeng
 * @create: 2021-02-04 12:39
 */

public interface ServiceProviderRegistry {
    <T> void addServiceProvider(Class<? extends T> serviceClass,T serviceProvider);
}
