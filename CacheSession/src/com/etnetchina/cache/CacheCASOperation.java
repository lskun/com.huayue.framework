package com.etnetchina.cache;

/**
 * 缓存更新时的回调接口,实现此接口可以指定更新数据时如果冲突时的应对策略.
 *
 * @param <VALUE> 实际的值类型。
 * @version 1.00 2009-12-29
 * @since 1.5
 * @author Mike
 */
public interface CacheCASOperation<VALUE> {

    /**
     * 需要重试的次数.返回值必须是一个非负整数.
     * @return 需要重试的次数.
     */
    public int getMaxTries();

    /**
     * 此方法返回一个最新需要储存在缓存中的值.此方法在更新发生冲突时会被调用,所以此方法
     * 必须包含失败时的处理逻辑.
     * @param currentValue 缓存中的当前值.
     * @return 需要缓存的值.
     */
    public VALUE getNewValue(VALUE currentValue);
}
