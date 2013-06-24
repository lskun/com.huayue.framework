package com.etnetchina.serialize;

/**
 * 序列化策略接口。
 * @author Mike
 * @version 1.00 2011-8-9
 * @since 1.5
 */
public interface SerializeStrategy {

    /**
     * 将指定的对象进行序列化。
     * @param source 需要序列化的对象。
     * @return 序列化后的字节数组。
     * @throws CanNotBeSerializedException 无法进行序列化。
     */
    public byte[] serialize(Object source)
            throws CanNotBeSerializedException;

    /**
     * 反序列化，将指定的字节反序列化成原始对象。
     * @param datas 需要返序列化的字节数据。
     * @return 原始对象。
     * @throws CanNotBeUnSerializedException 无法进行反序列化。
     */
    public Object unserialize(byte[] datas)
            throws CanNotBeUnSerializedException;
}
