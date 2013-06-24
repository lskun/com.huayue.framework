package com.etnetchina.cache;

import java.util.Properties;

/**
 * FilterCacheEngine包含其他一个缓存引擎实现，此接口的子类可以进行一步重写原有引擎的方法
 * 实现。
 * 
 * @version 1.00 2009.09.29
 * @since 1.5
 * @author Mike
 */
public abstract class FilterCacheEngine extends AbstractCacheEngine {

    protected CacheEngine cache;

    /**
     * 构造一个缓存引擎的装备器。并指定被装钸的实现。
     * @param cache 被装钸的引擎实现。
     */
    public FilterCacheEngine(CacheEngine cache) {
        this.cache = cache;
    }

    @Override
    protected void doInit(Properties prop) {
        cache.init(prop);
        LOG.infoLog(
                "GZIP Compression Cache initialization complete loading of plutonium.");
    }

    @Override
    protected void doStop() {
        cache.stop();
        LOG.infoLog("GZIP compression cache loaded plutonium stops.");
    }
}
