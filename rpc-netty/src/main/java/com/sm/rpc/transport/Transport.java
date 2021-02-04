package com.sm.rpc.transport;

import com.sm.rpc.transport.command.Command;

import java.util.concurrent.CompletableFuture;

/**
 * @project rpc-netty
 * @description:
 * @author: liumeng
 * @create: 2021-02-04 12:31
 */

public interface Transport {
    /**
     * 发送请求命令
     *
     * @param request: 请求命令
     * @return java.util.concurrent.CompletableFuture<com.sm.rpc.transport.command.Command>
     * @Author: liumeng on 2021/2/4 12:32
     */
    CompletableFuture<Command> send(Command request);
}
