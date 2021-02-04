package com.sm.rpc.serialize;

import com.sm.rpc.spi.ServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @project rpc-netty
 * @description: 序列化支持类
 * @author: liumeng
 * @create: 2021-02-04 13:05
 */
@SuppressWarnings("unchecked")
public class SerializeSupport {
    private static final Logger log = LoggerFactory.getLogger(SerializeSupport.class);
    private static Map<Class<?>/*序列化对象类型*/, Serializer<?>/*序列化实现*/> serializerMap = new HashMap<>();
    private static Map<Byte, Class<?>> typeMap = new HashMap<>();

    static {
        for (Serializer serializer : ServiceSupport.loadAll(Serializer.class)) {
            registerType(serializer.type(), serializer.getSerializeClass(), serializer);
            log.info("Found serializer, class: {},type:{}.", serializer.getSerializeClass().getCanonicalName(), serializer.type());
        }
    }

    public static <E> E parse(byte[] buffer) {
        return parse(buffer, 0, buffer.length);
    }

    public static <E> byte[] serialize(E entry) {
        @SuppressWarnings("unchecked")
        Serializer<E> serializer = (Serializer<E>) serializerMap.get(entry.getClass());
        if (serializer==null){
            throw new SerializeException(String.format("Unknown entry class type: %s!",entry.getClass().toString()));
        }
        byte[] bytes=new byte[serializer.size(entry)+1];
        bytes[0]=serializer.type();
        serializer.serialize(entry,bytes,1,bytes.length-1);
        return bytes;
    }

    private static <E> E parse(byte[] buffer, int offset, int length) {
        byte type = parseEntryType(buffer);
        @SuppressWarnings("unchecked")
        Class<E> entryClass = (Class<E>) typeMap.get(type);
        if (null == entryClass) {
            throw new SerializeException(String.format("Unknown entry type: %d!", type));
        } else {
            return parse(buffer, offset + 1, length - 1, entryClass);
        }
    }

    private static byte parseEntryType(byte[] buffer) {
        return buffer[0];
    }

    private static <E> void registerType(byte type, Class<E> entryClass, Serializer<E> serializer) {
        serializerMap.put(entryClass, serializer);
        typeMap.put(type, entryClass);
    }

    @SuppressWarnings("unchecked")
    private static <E> E parse(byte[] buffer, int offset, int length, Class<E> entryClass) {
        Object entry = serializerMap.get(entryClass).parse(buffer, offset, length);
        if (entryClass.isAssignableFrom(entry.getClass())) {
            return (E) entry;
        } else {
            throw new SerializeException("Type mismatch!");
        }
    }


}
