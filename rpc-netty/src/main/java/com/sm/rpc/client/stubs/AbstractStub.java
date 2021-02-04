package com.sm.rpc.client.stubs;

import com.sm.rpc.client.RequestIdSupport;
import com.sm.rpc.client.ServiceStub;
import com.sm.rpc.client.ServiceTypes;
import com.sm.rpc.serialize.SerializeSupport;
import com.sm.rpc.transport.Transport;
import com.sm.rpc.transport.command.Code;
import com.sm.rpc.transport.command.Command;
import com.sm.rpc.transport.command.Header;
import com.sm.rpc.transport.command.ResponseHeader;

import java.util.concurrent.ExecutionException;

/**
 * @project rpc-netty
 * @description:
 * @author: liumeng
 * @create: 2021-02-04 12:58
 */

public abstract class AbstractStub implements ServiceStub {
    protected Transport transport;

    protected byte[] invokeRemote(RpcRequest request) {

        Header header = new Header(ServiceTypes.TYPE_RPC_REQUEST, 1, RequestIdSupport.next());
        byte[] payload = SerializeSupport.serialize(request);
        Command requestCommand = new Command(header, payload);
        try {
            Command responseCommand = transport.send(requestCommand).get();
            ResponseHeader responseHeader = (ResponseHeader) responseCommand.getHeader();
            if (responseHeader.getCode() == Code.SUCCESS.getCode()) {
                return responseCommand.getPayload();
            } else {
                throw new RuntimeException(responseHeader.getError());
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setTransport(Transport transport) {
        this.transport = transport;
    }
}
