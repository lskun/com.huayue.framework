package com.etnetchina.servlet.session;

/**
 * 表示没有实现序列化的运行时异常.
 * @author Mike
 * @version 1.00 2010-11-12
 * @since 1.5
 */
public class NotSerializableException extends RuntimeException {

    private static final long serialVersionUID = 3073643491559111709L;

    /**
     * 给出不能序列化的实例class名称.
     * @param className class名称.
     */
    public NotSerializableException(String className) {
        super(className);
    }

    /**
     * 不标示出不能序列化的类名称.
     */
    public NotSerializableException() {
    }
}
