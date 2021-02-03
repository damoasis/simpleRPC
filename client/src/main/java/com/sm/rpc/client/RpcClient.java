package com.sm.rpc.client;

import com.sm.rpc.NameService;
import com.sm.rpc.RpcAccessPoint;
import com.sm.rpc.hello.HelloService;
import com.sm.rpc.spi.ServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class RpcClient {
    private static final Logger log = LoggerFactory.getLogger(RpcClient.class);

    public static void main(String[] args) {
        String serviceName = HelloService.class.getCanonicalName();
        File tmpDirFile = new File(System.getProperty("java.io.tmpdir"));
        File file = new File(tmpDirFile, "simple_rpc_name_service.data");
        String name = "Master MQ";
        try (RpcAccessPoint rpcAccessPoint = ServiceSupport.load(RpcAccessPoint.class)) {
            NameService nameService = rpcAccessPoint.getNameService(file.toURI());
            assert nameService != null;
            URI uri = nameService.lookupService(serviceName);
            assert uri != null;
            log.info("找到服务{}，提供者: {}.", serviceName, uri);
            HelloService helloService = rpcAccessPoint.getRemoteService(uri, HelloService.class);
            log.info("请求服务, name: {}...", name);
            String response = helloService.hello(name);
            log.info("收到响应: {}.", response);
        } catch (IOException e) {
            log.error("IO error.", e);
        }
    }
}
