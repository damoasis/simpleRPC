package com.sm.rpc;

import com.sm.rpc.spi.ServiceSupport;
import com.sm.rpc.transport.Transport;
import com.sm.rpc.transport.TransportClient;
import com.sm.rpc.transport.TransportServer;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @project rpc-netty
 * @description:
 * @author: liumeng
 * @create: 2021-02-04 11:16
 */

public class NettyRpcAccessPoint implements RpcAccessPoint{
    private final String host="localhost";
    private final int port=9999;
    private final URI uri=URI.create("rpc://"+host+":"+port);
    private TransportServer server=null;
    private TransportClient client= ServiceSupport.load(TransportClient.class);
    private final Map<URI, Transport> clientMap=new ConcurrentHashMap<>();



    @Override
    public <T> T getRemoteService(URI uri, Class<T> serviceClass) {
        return null;
    }

    @Override
    public <T> URI addServiceProvider(T service, Class<T> serviceClass) {
        return null;
    }

    @Override
    public Closeable startServer() throws Exception {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
