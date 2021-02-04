package com.sm.rpc.transport;

import java.io.Closeable;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.concurrent.TimeoutException;

/**
 * @project rpc-netty
 * @description:
 * @author: liumeng
 * @create: 2021-02-04 12:33
 */

public interface TransportClient extends Closeable {

    Transport createTransport(SocketAddress address,long connectionTimeout) throws InterruptedException, TimeoutException;

    @Override
    void close();
}
