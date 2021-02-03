package com.sm.rpc.server;

import com.sm.rpc.hello.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HelloService Implementation
 * @Author: liumeng on 2021/2/3 23:05
*/
public class HelloServiceImpl implements HelloService {
    private static final Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public String hello(String name) {
        log.info("HelloServiceImpl收到: {}.", name);
        String ret = "Hello, " + name;
        log.info("HelloServiceImpl返回: {}.", ret);
        return ret;
    }
}
