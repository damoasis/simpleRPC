package com.sm.rpc.transport.netty;

import com.sm.rpc.transport.InFlightRequests;
import com.sm.rpc.transport.ResponseFuture;
import com.sm.rpc.transport.Transport;
import com.sm.rpc.transport.command.Command;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;

import java.util.concurrent.CompletableFuture;

public class NettyTransport implements Transport {
    private final Channel channel;
    private final InFlightRequests inFlightRequests;

    public NettyTransport(Channel channel, InFlightRequests inFlightRequests) {
        this.channel = channel;
        this.inFlightRequests = inFlightRequests;
    }

    @Override
    public CompletableFuture<Command> send(Command request) {
        CompletableFuture<Command> completableFuture = new CompletableFuture<>();
        try {
            // 将在途请求放到inFlightRequest中
            inFlightRequests.put(new ResponseFuture(request.getHeader().getRequestId(), completableFuture));
            // 发送命令
            channel.writeAndFlush(request).addListener((ChannelFutureListener) channelFuture -> {
                // 处理发送失败情况
                if (!channelFuture.isSuccess()) {
                    completableFuture.completeExceptionally(channelFuture.cause());
                    channel.close();
                }
            });
        } catch (Throwable t) {
            // 处理发送异常
            inFlightRequests.remove(request.getHeader().getRequestId());
            completableFuture.completeExceptionally(t);
        }

        return completableFuture;
    }
}
