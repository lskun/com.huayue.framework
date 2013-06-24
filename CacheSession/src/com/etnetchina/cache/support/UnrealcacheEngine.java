package com.etnetchina.cache.support;

import com.etnetchina.cache.AbstractCacheEngine;
import com.etnetchina.cache.CacheCASOperation;
import com.etnetchina.cache.CacheEngine;
import com.etnetchina.cache.CacheNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * 此缓存引擎的实现，实际不会进行任何缓存的动作。get方法永运抛出CacheNotFoundException异
 * 常。
 * 获取实例的唯一方法就是调用getInterFacer()这个静态方法。
 * 
 * @version 1.00 2009.09.16
 * @since 1.5
 * @author Mike
 */
public class UnrealcacheEngine extends AbstractCacheEngine {

    private static CacheEngine engine = new UnrealcacheEngine();
    private static CacheNotFoundException cacheNotFound =
            new CacheNotFoundException("Not found.");

    /**
     * 获取此实例的唯一实现。
     * @return UnrealcacheEngine的实例。
     */
    public static CacheEngine getInterfacer() {
        return engine;
    }

    public UnrealcacheEngine() {
    }

    public boolean containsKey(String key) {
        return false;
    }

    public boolean put(String key, Object value) {
        return true;
    }

    public boolean put(String key, Object value, int expiredTime) {
        return true;
    }

    public boolean put(String key, Object value, Date date) {
        return true;
    }

    public boolean put(String key, Object value, String[] group) {
        return true;
    }

    public Object get(String key) throws CacheNotFoundException {
        throw cacheNotFound;
    }

    /**
     * 批量获取缓存中的数据.
     * @param keys 需要的key列表.
     * @return key和值的哈希映射.
     */
    public Map<String, Object> get(String[] keys) {
        return Collections.emptyMap();
    }

    public void remove(String key) {
    }

    public void flushGroup(String group) {
    }

    public boolean put(String key, Object value, CacheCASOperation operation) {
        return true;
    }

    public boolean put(String key, Object value, int expiredTime,
            CacheCASOperation operation) {
        return true;
    }

    public boolean put(String key, Object value, Date date,
            CacheCASOperation operation) {
        return true;
    }

    public boolean put(String key, Object value, String[] group,
            CacheCASOperation operation) {
        return true;
    }

    public long incr(String key, long magnitude) {
        return 0;
    }

    public long decr(String key, long magnitude) {
        return 0;
    }

    public long getNumber(String key) throws CacheNotFoundException {
        throw cacheNotFound;
    }

    public boolean add(String key, Object value) {
        return true;
    }

    public boolean add(String key, Object value, int expiredTime) {
        return true;
    }

    @Override
    protected void doInit(Properties prop) {
    }

    @Override
    protected void doStop() {
    }
}
