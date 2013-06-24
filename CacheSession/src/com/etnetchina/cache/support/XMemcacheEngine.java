package com.etnetchina.cache.support;

import com.etnetchina.cache.AbstractCacheEngine;
import com.etnetchina.cache.CacheCASOperation;
import com.etnetchina.cache.CacheEngine;
import com.etnetchina.cache.CacheNotFoundException;
import com.etnetchina.log.LogUtil;
import com.etnetchina.serialize.CanNotBeSerializedException;
import com.etnetchina.serialize.CanNotBeUnSerializedException;
import com.etnetchina.serialize.DefaultSerializeStrategy;
import com.etnetchina.serialize.HessianSerializeStrategy;
import com.etnetchina.serialize.SerializeStrategy;
import com.etnetchina.util.CheckUtil;
import com.etnetchina.util.DateCount;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import net.rubyeye.xmemcached.CASOperation;
import net.rubyeye.xmemcached.HashAlgorithm;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.apache.commons.logging.LogFactory;

/**
 * 基于xmemcached客户端的一个CacheEngine的接口实现。
 * 依赖于yanf4j-1.0.1或者以上，和slf4j.
 * 此实现不同于MemcacheEngine，使用了nio非阻塞模型。
 * xmemcached参考http://code.google.com/p/xmemcached/。
 *
 * 以下是此实现的相关配置文件实例。
 * --------------------------------------------------------------------
 * memcached node list, the format for the host: port,
 * multiple nodes in order to "," separated.
 * This value must be set.
 * servers=127.0.0.1:11211
 *
 * memcached node weight, only to receive a pure figure. A multi-"" separated,
 * its length must be equal to the length of servers.
 * Note that weights are more connections to be achieved,
 * the connection between the non-synchronized.
 * This value must be set.
 * weigths=1
 *
 * Access to data timeout, the default is 1000 millsecond.
 * getTimeOut = 1000;
 *
 *
 * A node, how many connections, pay attention to the various connections
 * are non-synchronous. The value of this setting defaults to 1,
 * set the value of one if it would be less than the default one.
 * connectionPoolSize=1
 *
 * Whether to use a binary protocol,
 * the default for text protocol.
 * Is only equal to true (not case-sensitive) only turns it on.
 * The default value is false.
 * useBinaryCommand=false
 *
 * Compressed data threshold,
 * if the value is larger than this setting will be compressed.
 * The default is 16382 bytes.
 * compressionThreshold=16382
 *
 * In the sequence of numeric type,
 * when there was special treatment,
 * if the previous N bytes are 0,
 * then it will remove the 0,
 * the reduced data will be smaller,
 * such as digital three serialization is 0x0003,
 * then the previous 3 0 will go to get rid of into a byte 0x3.
 * When deserialized will automatically fill in front of the type based on the
 * values 0. This feature is turned on by default, is set to false will be closed.
 * packZers=false
 *
 * Consistent Hashing algorithms are enabled client distribution.
 * True to enable, false to not enabled. is set to false will use the
 * current number of connections to key the hashCode for the distribution model.
 * The default is false.
 * consistentHashing=false
 *
 * Use "Hessian" serializer object if set hessian.
 * Use Java serialize object if set java.
 * false is default.
 * serializable=java
 *
 * Merge multiple get request.
 * Will be closed if set 0.
 * Default is 150.
 * You should set 0 if care response time.
 * mergeFactor = 150
 *
 * Heartbeat interval time (in milliseconds).
 * Default is 10000 milliseconds.
 * Use default value if set 0.
 * heartBeatTime = 10000
 * ---------------------------------------------------------------------------
 * 
 * @version 1.00 2009-12-23
 * @since 1.6
 * @author Mike
 */
public class XMemcacheEngine extends AbstractCacheEngine {

    private static final LogUtil LOG =
            new LogUtil(LogFactory.getLog(XMemcacheEngine.class));
    private URL configFileUrl = null;
    private String configFileClassPath = null;
    private File configFileLocalFile = null;
    private MemcachedClient client = null;
    /**
     * 最终使用的压缩阀值。这里定义的原因是在对象编码器上会使用这个值作性能判断。
     */
    private int compressionThreshold;

    /**
     * 构造一个新的XMemcacheEngine的实例，但是不初始化。
     */
    public XMemcacheEngine() {
    }

    /**
     * 以一个URL指定配置文件的所在地构造一个XMemcacheEngie的实例。
     * 同时也会进行初始化。
     *
     * @param url 配置文件的url.
     * @throws java.io.IOException 读取配置文件错误。
     */
    public XMemcacheEngine(URL url) throws IOException {
        this(url.openStream());
        configFileUrl = url;
    }

    /**
     * 以一个配置文件的类路径来构造一个XMemcacheEngine的实例。
     * 同时会进行初始化动作。
     *
     * @param classPath 配置文件的类路径。
     * @throws java.io.IOException 读取配置文件错误。
     */
    public XMemcacheEngine(String classPath) throws IOException {
        this(XMemcacheEngine.class.getResourceAsStream(classPath));
        configFileClassPath = classPath;
    }

    /**
     * 以一个本地文件来构造XMemcacheEngine实例，同时进行初始化。
     *
     * @param file 配置文件。
     * @throws java.io.IOException 读取配置文件错误。
     */
    public XMemcacheEngine(File file) throws IOException {
        this(new FileInputStream(file));
        configFileLocalFile = file;
    }

    /**
     * 实际构造XMemcacheEngine的构造方法，以一个输入流读取配置文件。
     *
     * @param stream 配置文件的读取流。
     * @throws java.io.IOException 读取配置文件错误。
     */
    private XMemcacheEngine(InputStream stream) throws IOException {
        Properties prop = new Properties();
        prop.load(stream);
        stream.close();

        init(prop);
    }

    /**
     * 判断指定的key是否存在.
     * @param key 缓存的key.
     * @return true存在,false不存在.
     */
    public boolean containsKey(String key) {
        checkInit();
        try {
            get(key);
        } catch (CacheNotFoundException ex) {
            return false;
        }
        return true;
    }

    /**
     * 将数据放入缓存,存放的秒数为Integer.MAX_VALUE的值.
     * @param key 缓存key.
     * @param value 缓存的值.
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value) {
        checkInit();
        return put(key, value, Integer.MAX_VALUE);
    }

    /**
     * 将数据放入缓存,存放的秒数为expiredTime指定的过期时间.
     * @param key 缓存key.
     * @param value 缓存的值.
     * @param expiredTime 过期秒数.
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value, int expiredTime) {
        checkInit();
        if (key == null || key.isEmpty()) {
            return false;
        }

        //如果储存的值为null,那么使用一个标示对象储存.
        Object storageValue = (value == null ? EmptyValue.getValue() : value);

        int nowExpiredTime = Math.abs(expiredTime);
        try {
            client.setWithNoReply(key, nowExpiredTime, storageValue);
        } catch (InterruptedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        } catch (MemcachedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        }

        return true;
    }

    /**
     * 更新数据进缓存,并在指定的时间后过期.实际会将指定的时间减去当前时间取绝对值.
     * @param key 缓存key.
     * @param value 缓存的值.
     * @param date 过期时间.
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value, Date date) {
        checkInit();
        if (date == null) {
            return put(key, value);
        } else {
            long timeInMillis = Math.abs(DateCount.timeGap(date,
                    Calendar.getInstance().getTime()).longValue());
            long expiredTime = timeInMillis / 1000;
            return put(key, value, (int) expiredTime);
        }

    }

    /**
     * 此方法同put(key,value);
     * @param key
     * @param value
     * @param group
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value, String[] group) {
        return put(key, value);
    }

    /**
     * 更新缓存中指定key的值,并永久保存.更新数据采用memcached的cas协议进
     * 行数据更新.
     * @param key 需要保存的key.
     * @param value 需要保存的值.
     * @param operation 同步更新冲突的回调接口.
     * @return true更新成功,false更新失败.
     */
    public boolean put(String key, Object value, CacheCASOperation operation) {
        return put(key, value, Integer.MAX_VALUE, operation);
    }

    /**
     * 更新缓存中的指定key的值,并在指定秒数后过期.更新数据采用memcached的cas协议进
     * 行数据更新.
     * @param key 缓存的key.
     * @param value 缓存的值.
     * @param expiredTime 过期秒数.
     * @param operation 同步更新冲突的回调接口.
     * @return true更新成功,false更新失败.
     */
    public boolean put(
            String key,
            Object value,
            int expiredTime,
            CacheCASOperation operation) {
        checkInit();
        checkKey(key);
        int nowExpiredTime = Math.abs(expiredTime);
        if (operation == null) {
            return put(key, value, nowExpiredTime);
        } else {
            if (containsKey(key)) {
                try {
                    return client.cas(key, nowExpiredTime,
                            new CASOperationImpl(operation));
                } catch (TimeoutException ex) {
                    LOG.errorLog(ex);
                    throw new IllegalStateException(ex.getMessage(), ex);
                } catch (InterruptedException ex) {
                    LOG.errorLog(ex);
                    throw new IllegalStateException(ex.getMessage(), ex);
                } catch (MemcachedException ex) {
                    LOG.errorLog(ex);
                    throw new IllegalStateException(ex.getMessage(), ex);
                }
            } else {
                try {
                    client.addWithNoReply(key, nowExpiredTime, value);
                } catch (InterruptedException ex) {
                    LOG.errorLog(ex);
                    throw new IllegalStateException(ex.getMessage(), ex);
                } catch (MemcachedException ex) {
                    LOG.errorLog(ex);
                    throw new IllegalStateException(ex.getMessage(), ex);
                }

                return true;
            }
        }
    }

    /**
     * 更新指定key的值,并在指定的日期/时间后过期.更新数据采用memcached的cas协议进
     * 行数据更新.
     * @param key 缓存的key.
     * @param value 缓存的值.
     * @param date 过期日期/时间.
     * @param operation 同步更新冲突的回调接口.
     * @return true更新成功,false更新失败.
     */
    public boolean put(
            String key,
            Object value,
            Date date,
            CacheCASOperation operation) {
        if (date == null) {
            return put(key, value, operation);
        } else {
            long timeInMillis = Math.abs(DateCount.timeGap(date,
                    Calendar.getInstance().getTime()).longValue());
            long expiredTime = timeInMillis / 1000;
            return put(key, value, (int) expiredTime, operation);
        }
    }

    /**
     * 此方法在此退化到put(key,value,operation);
     * @param key 缓存的key.
     * @param value 缓存的值.
     * @param group 组列表.
     * @param operation 同步更新冲突的回调接口.
     * @return true更新成功,false更新失败.
     */
    public boolean put(
            String key,
            Object value,
            String[] group,
            CacheCASOperation operation) {
        return put(key, value, operation);
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
    public boolean add(String key, Object value, int expiredTime) {
        checkInit();
        checkKey(key);
        int nowExpiredTime = Math.abs(expiredTime);
        try {
            return client.add(key, nowExpiredTime, value);
        } catch (TimeoutException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        } catch (InterruptedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        } catch (MemcachedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

    /**
     * 获取缓存中的数据.
     * @param key 缓存的key.
     * @return 缓存的值.
     * @throws com.etnetchina.cache.CacheNotFoundException 数据没有找到.
     */
    public Object get(String key) throws CacheNotFoundException {
        checkInit();
        checkKey(key);
        Object value = null;
        try {
            value = client.get(key);
        } catch (TimeoutException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        } catch (InterruptedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        } catch (MemcachedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        }

        if (value == null) {
            throw new CacheNotFoundException("Cache not found.");
        } else {
            if (EmptyValue.getValue().getClass().isInstance(value)) {
                value = null;
            }
            return value;
        }
    }

    /**
     * 批量获取缓存中的对象.如果指定的key不存在于缓存中将不会包含在返回的哈希表中.
     * @param keys 缓存的key列表.
     * @return 缓存key和值的哈希映射表.
     */
    public Map<String, Object> get(String[] keys) {
        checkInit();
        if (keys == null || keys.length == 0) {
            return Collections.emptyMap();
        }

        List<String> keyList = new ArrayList<String>(keys.length);
        String key;
        for (int count = 0; count < keys.length; count++) {
            key = keys[count];
            if (key != null && !key.isEmpty()) {
                keyList.add(key);
            }
        }

        Map<String, Object> resultMap = null;
        try {
            resultMap = client.get(keyList);
        } catch (TimeoutException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        } catch (InterruptedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        } catch (MemcachedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        }

        if (resultMap == null || resultMap.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, Object> filterMap =
                new HashMap<String, Object>(resultMap.size());
        Set<String> keySet = resultMap.keySet();
        Object cacheValue = null;
        for (String k : keySet) {
            cacheValue = resultMap.get(k);
            if (EmptyValue.class.isInstance(cacheValue)) {
                filterMap.put(k, null);
            } else if (cacheValue == null) {
            } else {
                filterMap.put(k, cacheValue);
            }
        }

        return Collections.unmodifiableMap(filterMap);
    }

    /**
     * 获取缓存中由增量的当前数据.
     * @param key 缓存的key.
     * @return 当前的缓存中的数值.
     * @throws com.etnetchina.cache.CacheNotFoundException
     */
    public long getNumber(String key) throws CacheNotFoundException {
        String value = (String) get(key);
        return Long.parseLong(value.trim());
    }

    /**
     * 从缓存中移除指定的key.
     * @param key 缓存的key.
     */
    public void remove(String key) {
        checkInit();
        checkKey(key);
        try {
            client.deleteWithNoReply(key);
        } catch (InterruptedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        } catch (MemcachedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

    public long incr(String key, long magnitude) {
        checkInit();
        checkKey(key);
        try {
            return client.incr(key, Math.abs(magnitude), Math.abs(magnitude));
        } catch (TimeoutException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        } catch (InterruptedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        } catch (MemcachedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

    public long decr(String key, long magnitude) {
        checkInit();
        checkKey(key);
        try {
            long oldValue = client.decr(key, Math.abs(magnitude));
            return oldValue;
        } catch (TimeoutException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        } catch (InterruptedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        } catch (MemcachedException ex) {
            LOG.errorLog(ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

    /**
     * 空方法.
     * @param group
     */
    public void flushGroup(String group) {
    }

    /**
     * 检查是否已经初始化.如果没有将抛出RuntimeException异常.
     */
    private void checkInit() {
        if (!this.isInitialized()) {
            IllegalStateException ex = new IllegalStateException(
                    "This client has not properly initialized.");
            LOG.errorLog(ex);
            throw ex;
        }
    }

    private void checkKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException(
                    "Cache key is null or not a length of 0.");
        }
    }

    @Override
    protected void doInit(Properties prop) {
        Config config = new Config(prop);
        String[] servers;
        int[] weigths;
        try {
            servers = config.servers();
            weigths = config.weigths();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }

        StringBuilder serverList = new StringBuilder();
        for (String server : servers) {
            if (serverList.length() > 0) {
                serverList.append(" ");
            }
            serverList.append(server);
        }
        //构造工厂
        MemcachedClientBuilder builder =
                new XMemcachedClientBuilder(
                AddrUtil.getAddresses(serverList.toString()), weigths);


        config.consistentHashing(builder);
        config.connectionPoolSize(builder);
        config.serializable(builder);
        config.userBinaryCommand(builder);
        config.headBeatTime(builder);


        //实例化客户端
        try {
            client = builder.build();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }

        config.timeout(client);
        config.compressionThreshold(client);
        config.isPackZers(client);
        config.mergeFactor(client);

        LOG.infoLog("Xmemcache config: {0}", prop);
    }

    @Override
    protected void doStop() {
        try {
            client.shutdown();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }

        LOG.infoLog("XMemcacheEngine stopped.");
    }

    /**
     * 配置信息处理类。
     */
    private class Config {

        private Properties prop = null;
        //服务器节点配置项
        private static final String CONFIG_SERVERS = "servers";
        //节点权重
        private static final String CONFIG_WEIGTHS = "weigths";
        //获取数据时的超时时间
        private static final String CONFIG_GETTIMEOUT = "getTimeOut";
        //原子更新最多尝试次数
        //private static final String CONFIG_MAX_TRIES = "maxTries";
        //连接池大小.
        private static final String CONFIG_CONNECTION_POOL_SIZE =
                "connectionPoolSize";
        //使用二进制协议
        private static final String CONFIG_USE_BINARY_COMMAND =
                "useBinaryCommand";
        //值压缩的阀值
        private static final String CONFIG_COMPRESSION_THRESHOLD =
                "compressionThreshold";
        //是否对值进和前导去0处理.
        private static final String CONFIG_PACK_ZERS = "packZers";
        //是否启用consistentHashing分布式算法
        private static final String CONFIG_CONSISTENT_HASHING = "consistentHashing";
        //序列化对象的实现策略
        private static final String CONFIG_SERIALIZABLE = "serializable";
        //多个请求的合并因子
        private static final String CONFIG_MERGE_FACTOR = "mergeFactor";
        //心跳连接是否有效的检测时间(毫秒)
        private static final String CONFIG_HEARTBEAT_TIME = "heartBeatTime";

        public Config(Properties prop) {
            this.prop = prop;
        }

        /**
         * 获取配置文件中的节点列表。
         * @return 节点列表数组。
         * @throws java.lang.Exception 配置异常。
         */
        public String[] servers() throws Exception {
            String configServers = prop.getProperty(CONFIG_SERVERS);
            if (configServers == null) {
                throw new Exception(
                        "May not be configured Memcached nodes.");
            }
            String[] arrayServers = configServers.split(",");


            return arrayServers;
        }

        /**
         * 解析并返回配置文件指定的节点权重列表。小数将被忽略。
         * @return 节点权重列表。
         * @throws java.lang.NumberFormatException 权重设置了非数字
         * @throws java.lang.Exception 没有指定权重，权重与节点长度不匹配。
         */
        public int[] weigths() throws NumberFormatException, Exception {
            String configWeigths = prop.getProperty(CONFIG_WEIGTHS);
            String[] arrayWeigths = null;
            int[] intArrayWeigths = null;
            String[] arrayServers = this.servers();
            if (configWeigths == null) {
                throw new Exception("You must set the node weights.");
            } else {
                arrayWeigths = configWeigths.split(",");
                if (arrayWeigths.length != arrayServers.length) {
                    throw new Exception(
                            "Node weights and the number of nodes must be consistent.");
                } else {
                    intArrayWeigths = new int[arrayWeigths.length];
                    for (int count = 0; count < intArrayWeigths.length; count++) {
                        try {
                            intArrayWeigths[count] = Math.abs(Integer.parseInt(
                                    arrayWeigths[count]));
                        } catch (NumberFormatException ex) {
                            throw new NumberFormatException(
                                    CheckUtil.replaceArgs(
                                    "Configure the wrong weight, the weight can only be a number.[{0}]",
                                    arrayWeigths[count]));

                        }
                    }
                }
            }
            return intArrayWeigths;
        }

        /**
         * 获取拿取数据时的超时时间,默认为1秒.
         * @return 获取数据时的最大超时时间.
         */
        public void timeout(MemcachedClient client) {
            String defaultTimeOut = "1000";
            String strTimeOut = prop.getProperty(
                    CONFIG_GETTIMEOUT, defaultTimeOut);

            //将秒换算成毫秒
            int timeOut = Math.abs(Integer.parseInt(strTimeOut));
            client.setOpTimeout(timeOut);
        }

        /**
         * 是否启用ConsistentHashing算法.
         * @return true启用,false不启用.
         */
        public void consistentHashing(MemcachedClientBuilder builder) {
            boolean value = Boolean.parseBoolean(
                    prop.getProperty(CONFIG_CONSISTENT_HASHING, "false"));

            if (value) {
                builder.setSessionLocator(
                        new KetamaMemcachedSessionLocator(
                        HashAlgorithm.KETAMA_HASH));
            }

        }

        /**
         * 获取连接池设置大小,默认为1.
         * @return 设置的连接池大小.
         */
        public void connectionPoolSize(MemcachedClientBuilder builder) {
            String defaultConnectionPoolSize = "1";
            String strSize = prop.getProperty(CONFIG_CONNECTION_POOL_SIZE,
                    defaultConnectionPoolSize);

            int poolSize = Math.abs(Integer.parseInt(strSize));
            builder.setConnectionPoolSize(poolSize);
        }

        /**
         * 多久进行一次心跳检测.
         * @param builder
         */
        public void headBeatTime(MemcachedClientBuilder builder) {
            String defaultTime = "10000";
            String strTime = prop.getProperty(
                    CONFIG_HEARTBEAT_TIME, defaultTime);

            //将秒换算成毫秒
            int time = Math.abs(Integer.parseInt(strTime));
            if (time == 0) {
                time = Math.abs(Integer.parseInt(defaultTime));
            }
            builder.getConfiguration().setSessionIdleTimeout(time);
        }

        /**
         * 设置获取对象的合并因子,连续的多个GET请求将被合并.
         * 如果値小于等于0,将认为需要关闭.
         * @param client
         */
        public void mergeFactor(MemcachedClient client) {
            String defaultMergeFactor = "150";
            String mergeFactorStr = prop.getProperty(
                    CONFIG_MERGE_FACTOR, defaultMergeFactor);

            int factor = Integer.parseInt(mergeFactorStr);
            if (factor <= 0) {
                client.setOptimizeGet(false);
            } else {
                client.setOptimizeGet(true);
                client.setMergeFactor(factor);
            }
        }

        /**
         * 是否使用二进制协议.true使用,false不使用(默认).
         * @return true使用,false不使用(默认).
         * @throws java.lang.Exception 读取配置文件错误.
         */
        public void userBinaryCommand(MemcachedClientBuilder builder) {
            boolean value = Boolean.parseBoolean(
                    prop.getProperty(CONFIG_USE_BINARY_COMMAND, "false"));

            if (value) {
                builder.setCommandFactory(new BinaryCommandFactory());

            }
        }

        /**
         * 获取压缩的阀值,默认为16384字节(16K).负数取绝对值.
         * @return 压缩的阀值,默认为16384字节(16K).
         */
        public void compressionThreshold(MemcachedClient client) {
            String defaultCompressionThreshold = "16384";
            String strSompressionThreshold = prop.getProperty(
                    CONFIG_COMPRESSION_THRESHOLD,
                    defaultCompressionThreshold);

            int compressSize;
            compressSize = Math.abs(Integer.parseInt(strSompressionThreshold));

            //记录下最终使用的值。
            XMemcacheEngine.this.compressionThreshold = compressSize;

            client.getTranscoder().setCompressionThreshold(compressSize);
        }

        /**
         * 是否启用前导去0处理,默认为true启动.
         * @return true启动,false不启动.
         */
        public void isPackZers(MemcachedClient client) {
            boolean value = Boolean.parseBoolean(
                    prop.getProperty(CONFIG_PACK_ZERS, "true"));

            client.getTranscoder().setPackZeros(value);
        }

        /**
         * 设备序列化实现.
         * 字符串"java"表示使用默认的JAVA序列化机制,
         * "hessian"表示使用hessian来序列化对象.
         *
         * 默认使用java.
         *
         * 字符串大小写不敏感.
         * @param builder
         */
        public void serializable(MemcachedClientBuilder builder) {
            final String javaSer = "java";
            final String hessianSer = "hessian";
            String value = prop.getProperty(CONFIG_SERIALIZABLE, javaSer);
            value = value.toLowerCase();

            SerializeStrategy serialize = null;

            if (hessianSer.equals(value)) {
                serialize = new HessianSerializeStrategy();
            } else {
                serialize = new DefaultSerializeStrategy();
            }

            builder.setTranscoder(new CustomTranscoders(serialize));
        }
    }

    /**
     * 更新数据时的CAS操作实现.
     */
    private class CASOperationImpl implements CASOperation<Object> {

        private int maxTries;
        private CacheCASOperation operation;

        public CASOperationImpl(CacheCASOperation operation) {
            this.operation = operation;
            this.maxTries = operation.getMaxTries();
        }

        public int getMaxTries() {
            return maxTries;
        }

        @SuppressWarnings("unchecked")
        public Object getNewValue(long currentCAS, Object currentValue) {
            return operation.getNewValue(currentValue);
        }
    }

    //使用Hessian2进行对象序列化
    private class CustomTranscoders extends SerializingTranscoder {

        private SerializeStrategy serialize = null;

        public CustomTranscoders(SerializeStrategy serialize) {
            this.serialize = serialize;
        }

        @Override
        protected byte[] serialize(Object obj) {
            byte[] objBytes = null;
            try {
                objBytes = serialize.serialize(obj);
            } catch (CanNotBeSerializedException ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }

            if (objBytes != null
                    && objBytes.length > XMemcacheEngine.this.compressionThreshold) {
                
                LOG.warnLog("{0} bytes will be written to the cache.",
                        objBytes.length);
            }

            return objBytes;
        }

        @Override
        protected Object deserialize(byte[] in) {
            if (in != null
                    && in.length > XMemcacheEngine.this.compressionThreshold) {
                LOG.warnLog("From the cache read {0} bytes.", in.length);
            }

            try {
                return serialize.unserialize(in);
            } catch (CanNotBeUnSerializedException ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        CacheEngine engine = new XMemcacheEngine();
        Properties prop = new Properties();
        prop.setProperty("servers", "192.168.151.138:11211");
        prop.setProperty("weigths", "1");
        prop.setProperty("consistentHashing", "true");
        prop.setProperty("serializable", "hessian");
        prop.setProperty("mergeFactor", "0");
        prop.setProperty("heartBeatTime", "20000");
        engine.init(prop);

        List<Long> cacheList = new ArrayList<Long>();
        for (int i = 0; i < 500; i++) {
            cacheList.add(Long.valueOf(i));
        }
        String key = "test.hessian2.key";
        engine.put(key, cacheList);

        cacheList = (List<Long>) engine.get(key);
        System.out.println(cacheList);

        engine.remove(key);
        engine.stop();
    }
}
