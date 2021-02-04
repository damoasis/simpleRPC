package com.sm.rpc.transport.netty;

import com.sm.rpc.transport.command.Header;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.List;

public class RequestEncoder extends CommandEncoder {
    @Override
    protected void encodeHeader(ChannelHandlerContext channelHandlerContext, Header header, ByteBuf byteBuf) {
        super.encodeHeader(channelHandlerContext, header, byteBuf);
    }
}
