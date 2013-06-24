package com.etnetchina.cache.support;

import com.etnetchina.cache.AbstractCacheEngine;
import com.etnetchina.cache.CacheCASOperation;
import com.etnetchina.cache.CacheEngine;
import com.etnetchina.cache.CacheNotFoundException;
import com.etnetchina.util.DateCount;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import com.opensymphony.oscache.web.filter.ExpiresRefreshPolicy;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * OScacheEngine缓存引擎实现
 * 需要一个属性配置文件来配置缓存相关属性，请参考OSCache的配置定义。
 * @version 1.0 2009.06.10
 * @since 1.5
 * @author Stone
 */
public class OScacheEngine extends AbstractCacheEngine {

    private GeneralCacheAdministrator cache;
    private static CacheNotFoundException cachNotFoundException =
            new CacheNotFoundException("Cache not found.");

    /**
     * 总是以默认属性构造一个新的实例。使用的属性如下。
     * cache.memory=true
     * cache.algorithm=com.opensymphony.oscache.base.algorithm.LRUCache
     * cache.blocking=true
     * cache.capacity=5000
     * 
     * @return 一个新的使用默认属性的实例。
     */
    public static CacheEngine getDefaultCache() {
        Properties prop = new Properties();
        prop.setProperty("cache.memory", "true");
        prop.setProperty("cache.algorithm",
                "com.opensymphony.oscache.base.algorithm.LRUCache");
        prop.setProperty("cache.blocking", "true");
        prop.setProperty("cache.capacity", "5000");

        return new OScacheEngine(prop);
    }

    /**
     * 默认构造方法，此时缓存没有初始化需要调用init方法后方可使用。
     */
    public OScacheEngine() {
    }

    /**
     * 初始化方法，需要提供配置信息。构造好后，引擎已经初始化完成。
     * @param properties 配置信息。
     */
    public OScacheEngine(Properties properties) {
        init(properties);
    }

    /**
     * 从一个URL中读取配置信息。构造好后，引擎已经初始化完成。
     * @param url 配置信息所在的URL.
     * @throws java.io.IOException 读取时出现异常。
     */
    public OScacheEngine(URL url) throws IOException {
        this(url.openStream());
    }

    /**
     * 读取类路径中的配置信息。构造好后，引擎已经初始化完成。
     * @param classPath 类路径。
     * @throws java.io.IOException
     */
    public OScacheEngine(String classPath) throws IOException {
        this(OScacheEngine.class.getResourceAsStream(classPath));
    }

    /**
     * 从指定的输入流中读取配置信息。构造好后，引擎已经初始化完成。
     * @param in 配置信息输入流。
     * @throws java.io.IOException 读取配置信息I/O异常。
     */
    public OScacheEngine(InputStream in) throws IOException {
        Properties config = new Properties();
        config.load(in);
        init(config);
    }

    /**
     * 缓存中是否包含查找的key对应value对象
     * 有返回true,无返回false
     * @param key
     * @return Boolean
     */
    public boolean containsKey(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        boolean flag = false;
        try {
            cache.getFromCache(key);
            flag = true;
        } catch (NeedsRefreshException e) {
            cache.cancelUpdate(key);
            flag = false;
        }

        return flag;
    }

    /**
     * 将对象放入缓存
     * 放入成功返回true,失败返回false
     * value不能为null,因为memcache不支持向缓存中保存为null的对象
     * 因此value为null传入的情况下，也返回false
     * @param key 对象的key
     * @param value 对象的value
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        cache.putInCache(key, value);
        if (LOG.isDebugEnabled()) {
            LOG.debugLog(
                    "Add to OSCache cache KEY equal to the {0} content.", key);
        }
        return true;
    }

    /**
     * OsCache放入缓存中，到达过期时间过期
     * @param key 保存到内存中的key
     * @param value 对象
     * @param expiredTime 缓存过期的秒数。如果为小于0的值将取绝对值。
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value, int expiredTime) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        int nowExpiredTime = Math.abs(expiredTime);
        ExpiresRefreshPolicy polic =
                new ExpiresRefreshPolicy(nowExpiredTime);
        cache.putInCache(key, value, polic);

        if (LOG.isDebugEnabled()) {
            LOG.debugLog(
                    "Add to OSCache cache KEY equal to the {0} content, defined as time expired and {1} seconds.",
                    key,
                    nowExpiredTime);
        }

        return true;
    }

    /**
     * 将对象放入OSCache中，在指定日期过期。如果指定的日期早于当前日期，那么将不会存入缓存。
     * @param key 缓存key.
     * @param value 缓存值。
     * @param date 过期时间。
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value, Date date) {
        if (date == null) {
            return put(key, value);
        }

        Date now = Calendar.getInstance().getTime();
        if (date.before(now)) {
            return false;
        } else {
            long second = DateCount.timeGap(now, date).longValue() / 1000L;
            put(key, value, (int) second);
            return true;
        }
    }

    /**
     * OsCache放入缓存组中
     * @param key 缓存对象在缓存中的key
     * @param Value 放入缓存的对象
     * @param groups 指定放入的缓存组
     */
    public boolean put(String key, Object Value, String[] groups) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        cache.putInCache(key, Value, groups);

        if (LOG.isDebugEnabled()) {
            LOG.debugLog("Add to OSCache cache KEY equal to the {0} content, defined as a group {1}.",
                    key,
                    Arrays.toString(groups));
        }

        return true;
    }

    /**
     * 清空选择的组
     * @param group 组的名称
     */
    public void flushGroup(String group) {
        if (group != null && !group.isEmpty()) {
            cache.flushGroup(group);
        }
    }

    /**
     * 从缓存中取出对应key的对象，
     * 如果缓存中没有此对象会抛出CacheNotFoundException,
     * OScache,Memcache对于不存在的对象会返回null值
     * @param key 对象的对应key值
     * @return Object 对应key的对象
     * @throws com.etnetchina.cache.CacheNotFoundException
     */
    public Object get(String key) throws CacheNotFoundException {
        if (key == null || key.isEmpty()) {
            throw cachNotFoundException;
        }
        try {
            Object obj = null;
            obj = cache.getFromCache(key);

            if (LOG.isDebugEnabled()) {
                LOG.debugLog("KEY equal to the cache had been found {0}.", key);
            }

            return obj;

        } catch (NeedsRefreshException ex) {
            cache.cancelUpdate(key);

            if (LOG.isDebugEnabled()) {
                LOG.debugLog("KEY equal to the cache did not found {0}", key);
            }

            throw cachNotFoundException;
        }
    }

    /**
     * 批量获取缓存中的数据.
     * @param keys 需要的key列表.
     * @return key和值的哈希映射.
     */
    public Map<String, Object> get(String[] keys) {
        if (keys == null || keys.length == 0) {
            return Collections.emptyMap();
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String key;
        for (int count = 0; count < keys.length; count++) {
            key = keys[count];
            if (key != null && !key.isEmpty()) {
                try {
                    resultMap.put(key, get(key));
                } catch (CacheNotFoundException ex) {
                    continue;
                }
            }
        }

        return Collections.unmodifiableMap(resultMap);
    }

    /**
     * 获取缓存中由增量的当前数据.
     * @param key 缓存的key.
     * @return 当前的缓存中的数值.
     * @throws com.etnetchina.cache.CacheNotFoundException
     */
    public long getNumber(String key) throws CacheNotFoundException {
        Long value = (Long) get(key);
        return value.longValue();
    }

    /**
     * 删除缓存中对应key的对象。
     * @param key 需要删除的缓存key.
     */
    public void remove(String key) {
        if (key != null && !key.isEmpty()) {
            cache.flushEntry(key);
            if (LOG.isDebugEnabled()) {
                LOG.debugLog("KEY to delete the cache equal to {0}.", key);
            }
        }
    }

    /**
     * 并没有实际实现,行为等同于put(key,value)
     *
     * @param key 需要更新的缓存key.
     * @param value 需要更新的缓存值.
     * @param operation 同步更新冲突处理回调接口.
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value, CacheCASOperation operation) {
        return put(key, value);
    }

    /**
     * 并没有实际实现,行为等同于put(key,value,expiredTime);
     * @param key
     * @param value
     * @param expiredTime
     * @param operation
     * @return
     */
    public boolean put(
            String key,
            Object value,
            int expiredTime,
            CacheCASOperation operation) {
        return put(key, value, expiredTime);
    }

    /**
     * 并没有实际实现,行为等同于put(key,value,date);
     * @param key
     * @param value
     * @param date
     * @param operation
     * @return
     */
    public boolean put(
            String key,
            Object value,
            Date date,
            CacheCASOperation operation) {
        return put(key, value, date);
    }

    /**
     * 并没有实际实现,行为等同于put(key,value,group);
     * @param key
     * @param value
     * @param group
     * @param operation
     * @return
     */
    public boolean put(
            String key,
            Object value,
            String[] group,
            CacheCASOperation operation) {
        return put(key, value, group);
    }

    /**
     * 向缓存中增加一个映射,如果缓存中已经存在此key那么增加失败,否则写入缓存.
     * @param key　缓存的key.
     * @param value 缓存的值.
     * @return true成功写入缓存,false缓存中已经存在此key.
     */
    public boolean add(String key, Object value) {
        return add(key, value, Integer.MAX_VALUE);
    }

    /**
     * 意义同add(key,value)方法,但是主动设置一个过期时间.
     * @param key　缓存的key.
     * @param value 缓存的值.
     * @param expiredTime　此映射如果成功写入,那么将在此值指定的秒数后失效.
     * @return true增加成功,false增加失败,此key已经存在.
     */
    public synchronized boolean add(String key, Object value, int expiredTime) {
        boolean being = false;
        being = containsKey(key);
        if (being) {
            return false;
        } else {
            return put(key, value, expiredTime);
        }
    }

    /**
     * 指定的key增加指定数值，但是此实现没有实现原子增加。
     * 如果缓存不存在，那么将默认为0的基础上操作。
     * @param key 需要操作key
     * @param magnitude 幅度。
     * @return 新值。
     */
    public long incr(String key, long magnitude) {
        long absValue = Math.abs(magnitude);
        long cacheValue;
        try {
            cacheValue = getNumber(key);
        } catch (CacheNotFoundException ex) {
            put(key, Long.valueOf(absValue));
            return absValue;
        }

        long newValue = cacheValue + absValue;
        put(key, Long.valueOf(newValue));
        return newValue;
    }

    /**
     * 指定的key减少指定数值，但是此实现没有实现原子操作。
     * 如果缓存不存在，那么将默认为0的基础上操作。
     * @param key 需要操作的key.
     * @param magnitude 操作幅度。
     * @return 新值。
     */
    public long decr(String key, long magnitude) {
        long absValue = Math.abs(magnitude);
        long cacheValue;
        final long emptyValue = 0;
        try {
            cacheValue = getNumber(key);
        } catch (CacheNotFoundException ex) {
            put(key, Long.valueOf(emptyValue));
            return absValue;
        }

        long newValue = cacheValue - absValue;
        long newCacheValue = newValue < emptyValue ? emptyValue : newValue;
        put(key, Long.valueOf(newCacheValue));
        return newCacheValue;
    }

    @Override
    protected void doInit(Properties prop) {
        if (prop == null) {
            cache = new GeneralCacheAdministrator();
        } else {
            cache = new GeneralCacheAdministrator(prop);
        }

        LOG.infoLog("OSCache normal start.");
    }

    @Override
    protected void doStop() {
        cache.destroy();
        LOG.infoLog("OSCache has stopped.");
    }
}
