package com.sm.rpc;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;

/**
 * 注册中心
 *
 * @Author: liumeng on 2021/2/3 22:15
 */
public interface NameService {

    /**
     * 所有支持的协议
     */
    Collection<String> supportedSchemes();

    /**
     * 连接注册中心
     *
     * @param nameServiceUri:注册中心地址
     * @return void
     * @Author: liumeng on 2021/2/3 22:19
     */
    void connect(URI nameServiceUri);

    /**
     * 注册服务
     *
     * @param serviceName:服务名称
     * @param uri:服务地址
     * @return void
     * @Author: liumeng on 2021/2/3 22:19
     */
    void registerService(String serviceName, URI uri) throws IOException;

    /**
     * 查询服务地址
     *
     * @param serviceName: 服务名称
     * @return java.net.URI 服务地址
     * @Author: liumeng on 2021/2/3 22:19
     */
    URI lookupService(String serviceName) throws IOException;

}
