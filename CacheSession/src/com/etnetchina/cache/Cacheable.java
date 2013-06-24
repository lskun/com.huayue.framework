
package com.etnetchina.cache;

/**
 * 可缓存的接口
 * @author Stone
 */
public interface Cacheable {
    /**
     * 配置缓存引擎
     * @param engine 实现接口缓存引擎的实例
     */
    public void setCache(CacheEngine engine);

}
