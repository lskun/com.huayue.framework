package com.etnetchina.cache;

/**
 * 自定义的缓存异常，用于缓存出错时使用
 * @version 1.0
 * @since 1.6.2
 * @author Stone
 */
public class CacheNotFoundException extends Exception {

    private static final long serialVersionUID = -3786332327251211771L;

    /**
     * 构造详细消息为 null 的新缓存没有找到异常。
     */
    public CacheNotFoundException() {
        super();
    }

    /**
     * 构造带指定详细消息的新缓存没有找到异常。
     * @param message 缓存消息。
     */
    public CacheNotFoundException(String message) {
        super(message);
    }

    /**
     * 根据指定的原因和 (cause==null ? null : cause.toString()) 的详细消息构造
     * 新异常（它通常包含 cause 的类和详细消息）。
     * @param throwable 原因（保存此原因，以便以后通过Throwable.getCause()
     * 方法获取它）。（允许使用 null 值，指出原因不存在或者是未知的。）
     */
    public CacheNotFoundException(Throwable throwable) {
        super(throwable);
    }

    /**
     * 构造带指定详细消息和原因的新缓存没有找到异常。
     * @param message 详细消息.
     * @param throwable 原因.
     */
    public CacheNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}