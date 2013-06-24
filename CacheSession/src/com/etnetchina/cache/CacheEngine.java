package com.etnetchina.cache;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * 缓存引擎的接口
 * @version 1.0 2009.06.08
 * @since 1.6.2
 * @author Stone
 */
public interface CacheEngine {

    /**
     * 表示此缓存的值一经写入就不会被改变.
     * 至于使用与否将有实现类自行决定.
     */
    public static final String UNCHANGING_VALUE_FLAG = "[unchanging]";
    
    /**
     * 初始化引擎。
     * @param prop 配置属性。
     */
    public void init(Properties prop);

    /**
     * 停止缓存，执行清空其中的内容等动作
     */
    public void stop();

    /**
     * 缓存中是否包含key
     * 找到返回为true,找不到返回为false
     * @param key 查找的值
     * @return 是否找到
     */
    public boolean containsKey(String key);

    /**
     * 向缓存中添加对象
     * @param key 添加对象的key
     * @param value 添加的对象
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value);

    /**
     * 更新缓存中指定key的值,同时如果更新出现冲突那么将回调给定的CacheCASOperation接
     * 口的实现.如果回调接口为null,那么将退化到put(key,value);
     * @param key 需要更新的缓存key.
     * @param value 需要更新的缓存值.
     * @param operation 同步更新冲突处理回调接口.
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value, CacheCASOperation operation);

    /**
     * 更新缓存中指定key的值
     * @param key 缓存key.
     * @param value 缓存的值.
     * @param expiredTime 缓存过期的秒数。如果为小于0的值将取绝对值。
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value, int expiredTime);

    /**
     * 更新缓存中指定key的值,同时如果更新出现冲突那么将回调给定的CacheCASOperation接
     * 口的实现.如果回调接口为null,那么将退化到put(key,value,expiredTime);
     * @param key 缓存的key.
     * @param value 缓存的值.
     * @param expiredTime 缓存过期的秒数。如果为小于0的值将取绝对值。
     * @param operation 同步更新冲突处理回调接口.
     * @return true更新成功,false更新失败.
     */
    public boolean put(
            String key,
            Object value,
            int expiredTime,
            CacheCASOperation operation);

    /**
     * 往缓存中存一个键值对，指定过期时间。
     * @param key 缓存key.
     * @param value 缓存的值。
     * @param date 过期时间。
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value, Date date);

    /**
     * 往缓存中存一个键值对，指定过期时间。
     * @param key 缓存key.
     * @param value 缓存的值。
     * @param date 过期时间。
     * @param operation 同步更新冲突处理回调接口.
     * @return true更新成功,false更新失败.
     */
    public boolean put(
            String key,
            Object value,
            Date date,
            CacheCASOperation operation);

    /**
     * 向缓存中添加对应的对象，并在对应组中登记
     * @param key 缓存的key.
     * @param value 缓存的值.
     * @param group 组名称列表
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value, String[] group);

    /**
     * 向缓存中添加对应的对象，并在对应组中登记
     * @param key 对象的key
     * @param value 对象
     * @param group 组名称
     * @param operation 同步更新冲突处理回调接口.
     * @return true更新成功,false更新失败.
     */
    public boolean put(
            String key,
            Object value,
            String[] group,
            CacheCASOperation operation);

    /**
     * 向缓存中增加一个映射,如果缓存中已经存在此key那么增加失败,否则写入缓存.
     * 多个线程同时操作时如果key相同那么只有第一个进入的成功返回true.其后线程都将返回false.
     * @param key　缓存的key.
     * @param value 缓存的值.
     * @return true成功写入缓存,false缓存中已经存在此key.
     */
    public boolean add(String key, Object value);

    /**
     * 意义同add(key,value)方法,但是主动设置一个过期时间.
     * 多个线程同时操作时如果key相同那么只有第一个进入的成功返回true.后部线程都将返回false.
     * @param key　缓存的key.
     * @param value 缓存的值.
     * @param expiredTime　此映射如果成功写入,那么将在此值指定的秒数后失效.
     * @return true增加成功,false增加失败,此key已经存在.
     */
    public boolean add(String key, Object value, int expiredTime);

    /**
     * 取得缓存对象
     * @param key 缓存对象的key
     * @return 查询到的缓存的对象
     * @throws CacheNotFoundException 在缓存中不存在指定KEY所代表的值。
     */
    public Object get(String key) throws CacheNotFoundException;

    /**
     * 批量获取缓存中的对象.如果指定的key不存在于缓存中将不会包含在返回的哈希表中.
     * 总是会返回一个只读的Map的实例.
     *
     * @param keys 缓存的key列表.
     * @return 缓存key和值的哈希映射表.
     */
    public Map<String, Object> get(String[] keys);

    /**
     * 获取缓存中由增量的当前数据.
     * @param key 缓存的key.
     * @return 当前的缓存中的数值.
     * @throws com.etnetchina.cache.CacheNotFoundException
     */
    public long getNumber(String key) throws CacheNotFoundException;

    /**
     * 如果当前的key为long的数值那么会进行原子的增加操作。
     * 如果缓存不存在，那么将默认为0的基础上操作。
     * 如果只需要获取当前的值，建议使用getNumber方法获取。
     * @param key 需要操作的key.
     * @param magnitude 幅度。如果小于0将取绝对值.
     * @return 新的值。
     */
    public long incr(String key, long magnitude);

    /**
     * 对key指定的值进行原子减法操作。如果缓存不存在，那么将默认为0的基础上操作。
     * 数据最小为0，如果减去的数据会造成当前数据小于0那当前值作为0作理。
     * 比如当前值为10，减去20，那么结果为－20，当前值变为0。
     * 如果只需要获取当前的值，建议使用getNumber方法获取。
     * @param key 需要操作的key.
     * @param magnitude 幅度。如果小于0那么将取绝对值.
     * @return 新的值。
     */
    public long decr(String key, long magnitude);

    /**
     * 删除缓存中对应的key的对象
     * @param key 缓存对象的key
     */
    public void remove(String key);

    /**
     * 清空对应的组
     * @param group 对应组的名称
     */
    public void flushGroup(String group);

    /**
     * 返回当前缓存实例是否已经初始化完成.
     * @return true 完成,false没有完成.
     */
    public boolean isInitialized();
}
