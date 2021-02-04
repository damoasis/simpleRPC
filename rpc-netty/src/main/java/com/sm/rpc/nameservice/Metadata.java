package com.sm.rpc.nameservice;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

/**
 * @project rpc-netty
 * @description:注册中心元数据 key:服务名,value:服务提供者URI列表
 * @author: liumeng
 * @create: 2021-02-04 17:20
 */

public class Metadata extends HashMap<String, List<URI>> {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Metadata:").append("\n");
        for (Entry<String, List<URI>> entry : entrySet()) {
            sb.append("\t").append("Classname: ")
                    .append(entry.getKey()).append("\n");
            sb.append("\t").append("URIs:").append("\n");
            for (URI uri : entry.getValue()) {
                sb.append("\t\t").append(uri).append("\n");
            }
        }
        return sb.toString();
    }
}
