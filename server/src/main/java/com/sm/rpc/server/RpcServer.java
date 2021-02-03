package com.sm.rpc.server;

import com.sm.rpc.NameService;
import com.sm.rpc.RpcAccessPoint;
import com.sm.rpc.hello.HelloService;
import com.sm.rpc.spi.ServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.File;
import java.net.URI;

/**
 * RPC Server
 *
 * @Author: liumeng on 2021/2/3 23:07
 */
public class RpcServer {
    private static final Logger log = LoggerFactory.getLogger(RpcServer.class);

    public static void main(String[] args) {
        String serviceName = HelloService.class.getCanonicalName();
        File tmpDirFile = new File(System.getProperty("java.io.tmpdir"));
        File file = new File(tmpDirFile, "simple_rpc_name_service.data");
        HelloService helloService = new HelloServiceImpl();
        log.info("创建并启动RpcAccessPoint...");
        try (RpcAccessPoint rpcAccessPoint = ServiceSupport.load(RpcAccessPoint.class);
             Closeable ignored = rpcAccessPoint.startServer()) {

            NameService nameService = rpcAccessPoint.getNameService(file.toURI());
            assert nameService != null;
            log.info("向RpcAccessPoint注册{}服务...", serviceName);
            URI uri = rpcAccessPoint.addServiceProvider(helloService, HelloService.class);
            log.info("服务名：{}，向NameService注册...", serviceName);
            nameService.registerService(serviceName, uri);
            log.info("开始提供服务，按任意键退出.");
            System.in.read();
            log.info("Bye!");

        } catch (Exception e) {
            log.error("启动RpcAccessPoint异常", e);
        }
    }
}
