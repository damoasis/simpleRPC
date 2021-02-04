package com.sm.rpc.transport;

import com.sm.rpc.transport.command.Command;

/**
 * @project rpc-netty
 * @description: 请求处理器
 * @author: liumeng
 * @create: 2021-02-04 12:23
 */

public interface RequestHandler {
    /**
     * 处理请求
     * @param requestCommand: 请求命令
     * @return 响应命令
     * @Author: liumeng on 2021/2/4 12:24
    */
    Command handle(Command requestCommand);
    
    /**
     * 支持的请求类型
     
     * @return int 
     * @Author: liumeng on 2021/2/4 12:54
    */
    int type();
}
