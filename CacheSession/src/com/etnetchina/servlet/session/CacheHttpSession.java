package com.etnetchina.servlet.session;

import com.etnetchina.cache.CacheEngine;
import com.etnetchina.cache.CacheNotFoundException;
import com.etnetchina.log.LogUtil;
import com.etnetchina.servlet.listener.cache.CacheEngineLoadListener;
import com.etnetchina.util.CheckUtil;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.commons.logging.LogFactory;

/**
 * 一个HttpSession的实现，实际的属性会储存在指定的缓存实现中。
 *
 * 以下方法没有实现。调用将抛出UnsupportedOperationException异常。
 * public HttpSessionContext getSessionContext();
 *
 * 现在底层将Session的信息和储存在Session中的信息分别进行储存。只有当Session中的键值
 * 对属性被改变时才会进行缓存的同步。
 *
 * @author Mike
 * @version 2.1 2011-04-07
 * @since 1.5
 */
public class CacheHttpSession implements HttpSession {

    /**
     * 会话在缓存中的KEY前辍
     */
    public static final String SESSION_CACHE_KEY = "com.etnetchina.session.";
    private static final LogUtil LOGGER =
            new LogUtil(LogFactory.getLog(CacheHttpSession.class));
    private String id;
    private CacheEngine cache;
    private int maxInactiveInterval = -1;
    private ServletContext context;
    private final String sessionCacheKeyHeader;
    private final String sessionCacheKeyAttribute;
    private CacheSessionHeader sessionHeader;
    private CacheSessionAttribute sessionAttribute;
    private boolean invalid = false;
    //是否需同步缓存（只有改变Session中的键值对才会进行同步）
    private boolean update = false;
    private HttpSessionAttributeListener[] sessionAttributeListeners;
    private HttpSessionListener[] sessionListeners;

    /**
     * 初始化时必须指定一个id字符串和缓存引擎实现。
     * @param request 当前请求。
     * @param response 当前响应。
     * @param id id字符串。
     * @param servletContext 环境变量.
     * @param sessionCookieName 失效时需要清理的Cookie名称 。
     */
    public CacheHttpSession(
            ServletContext servletContext,
            String id) {
        this.id = id;
        this.cache = (CacheEngine) servletContext.getAttribute(
                CacheEngineLoadListener.CACHE_USE_HOST_DOMAIN_KEY);
        this.context = servletContext;
        sessionCacheKeyHeader = SESSION_CACHE_KEY + this.id + ".header";
        sessionCacheKeyAttribute = SESSION_CACHE_KEY + this.id + ".attribute";
    }

    /**
     * 此Session的ID值。
     * @return id值。
     */
    public String getId() {
        return id;
    }

    /**
     * 获取此Session的创建时间。
     * @return 创建时间。
     */
    public long getCreationTime() {
        return sessionHeader.getCreateTime();
    }

    /**
     * 获取最后访问时间。
     * @return 最后访问时间。
     */
    public long getLastAccessedTime() {
        return sessionHeader.getLastAccessTime();
    }

    /**
     * 是否用户键值对被改变过。
     * @return true被改变过，false没有改变。
     */
    public boolean isUpdate() {
        return update;
    }

    /**
     * 获取当前的属性键值对。
     * @return 属性键值对。
     */
    public CacheSessionAttribute getSessionAttribute() {
        return sessionAttribute;
    }

    /**
     * 获取当前的Session本身属性。
     * @return 本身属性。
     */
    public CacheSessionHeader getSessionHeader() {
        return sessionHeader;
    }

    /**
     * 表示此Session被访问。
     */
    public void access() {
        sessionHeader.setLastAccessTime(Calendar.getInstance().getTimeInMillis());
    }

    /**
     * 获取相关的环境变量。
     * @return 环境变量。
     */
    public ServletContext getServletContext() {
        return this.context;
    }

    /**
     * 设定Session的最长不活动时限(秒），如果此时限没有活动的Session将被删除。
     * @param maxInactiveInterval 最长活动时限。
     */
    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    /**
     * 获取最长不活动时限(秒）。
     * @return 最长活动时限。
     */
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    /**
     * 获取属性值。
     * @param attributeName 属性名称。
     * @return 属性值。
     */
    public Object getAttribute(String attributeName) {
        checkSessionInvalild();
        return findCacheSessionAttribute().getAttribute(attributeName);
    }

    /**
     * 获取属性名称集合。
     * @return 属性名称集合。
     */
    public Enumeration getAttributeNames() {
        checkSessionInvalild();
        Set<String> attributeNameSet = findCacheSessionAttribute().
                getAttributeNames();
        Enumeration enumeration = new CacheEnumeration(attributeNameSet);
        return enumeration;
    }

    /**
     * 更新属性。属性值必须是一个实现了兼容java.io.Serializable接口的实例.
     * @param attributeName 属性名称.
     * @param attributeValue 属性值。
     */
    public void setAttribute(String attributeName, Object attributeValue) {
        checkSessionInvalild();
        checkSerializable(attributeValue);

        Object oldValue = findCacheSessionAttribute().getAttribute(attributeName);

        findCacheSessionAttribute().putAttribute(attributeName,
                (Serializable) attributeValue);
        update = true;

        doHttpSessionBindingListener(attributeName, attributeValue,
                AccessType.SET_ATTRIBUTE);

        if (oldValue == null) {
            doHttpSessionAttributeListener(attributeName, attributeValue,
                    AccessType.SET_ATTRIBUTE);
        } else {
            doHttpSessionAttributeListener(attributeName, oldValue,
                    AccessType.REPLACE_ATTRIBUTE);
        }
    }

    /**
     * 移除已有的属性。
     * @param attributeName 属性名称。
     */
    public void removeAttribute(String attributeName) {
        checkSessionInvalild();
        Object value = findCacheSessionAttribute().removeAttribute(attributeName);
        update = true;

        doHttpSessionBindingListener(attributeName, value,
                AccessType.REMOVE_ATTRIBUTE);

        doHttpSessionAttributeListener(attributeName, value,
                AccessType.REMOVE_ATTRIBUTE);
    }

    /**
     * Session过期。
     */
    public void invalidate() {
        LOGGER.debugLog("Session {0}, to fail.", id);

        doHttpSessionListener(AccessType.REMOVE_ATTRIBUTE);

        invalid = true;
    }

    /**
     * 判断是否已经超过了最大活动时间。
     * @return true超过，false没有超过。
     */
    public boolean isInvalid() {
        if (invalid) {
            return invalid;
        } else {
            if (getMaxInactiveInterval() <= 0) {
                invalid = false;

                LOGGER.debugLog("Session [{0}] non-perishable.", id);
            } else {
                long invalidMillis = getMaxInactiveInterval() * 1000;
                long lastAccessTime = getLastAccessedTime();
                long now = Calendar.getInstance().getTimeInMillis();
                invalid = (now - lastAccessTime) > invalidMillis;

                LOGGER.debugLog(
                        "Session {0}, last access time {1}, {2} the current time. Whether the failure of [{3}].",
                        id,
                        lastAccessTime,
                        now,
                        invalid);
            }

            return invalid;
        }
    }

    /**
     * 判断此Session是否为新的。
     * @return true 为新的，false为非新的。
     */
    public boolean isNew() {
        checkSessionInvalild();
        return sessionHeader.isNewbuild();
    }

    @Override
    public int hashCode() {
        return sessionCacheKeyHeader.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CacheHttpSession other = (CacheHttpSession) obj;
        if ((id == null) ? (other.getId() != null) : !id.equals(other.getId())) {
            return false;
        }
        return true;
    }

    /**
     * 获取当前HttpSessionAttributeListener监听器列表.
     * @return HttpSessionAttributeListener实例列表.
     */
    public HttpSessionAttributeListener[] getSessionAttributeListeners() {
        return Arrays.copyOf(sessionAttributeListeners,
                sessionAttributeListeners.length);
    }

    /**
     * 设置HttpSessionAttributeListener监听器列表.
     * @param sessionAttributeListeners HttpSessionAttributeListener监听器列表.
     */
    public void setSessionAttributeListeners(
            HttpSessionAttributeListener[] sessionAttributeListeners) {
        if (sessionAttributeListeners != null) {
            this.sessionAttributeListeners =
                    Arrays.copyOf(sessionAttributeListeners,
                    sessionAttributeListeners.length);
        }
    }

    /**
     * 获取HttpSessionListener监听器列表.
     * @return HttpSessionListener监听器列表.
     */
    public HttpSessionListener[] getSessionListeners() {
        return Arrays.copyOf(sessionListeners, sessionListeners.length);
    }

    /**
     * 设置 HttpSessionListener监听器列表.
     * @param sessionListeners HttpSessionListener监听器列表.
     */
    public void setSessionListeners(HttpSessionListener[] sessionListeners) {
        if (sessionListeners != null) {
            this.sessionListeners = Arrays.copyOf(sessionListeners,
                    sessionListeners.length);
        }
    }

    @Override
    public String toString() {
        String message = "Session id is {0},key is {1}.";
        return CheckUtil.replaceArgs(message, getId(), sessionCacheKeyHeader);
    }

    /**
     * 没有实现。
     * @return
     * @deprecated 
     */
    public HttpSessionContext getSessionContext() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 实际调用removeAttribute方法。
     * @param name 需要删除的属性名称。
     * @deprecated 已经过时，请使用removeAttribute.
     */
    public void removeValue(String name) {
        removeAttribute(name);
    }

    /**
     * 实际调用setAttribute方法。
     * @param name 属性名称。
     * @param value 属性方法。
     * @deprecated 已经过时，请使用setAttribute.
     */
    public void putValue(String name, Object value) {
        setAttribute(name, value);
    }

    /**
     * 返回getAttributeNames方法的返回结果集。
     * @return 属性名称数组。
     * @deprecated 已经过时，请使用getAttributeNames.
     */
    public String[] getValueNames() {
        Enumeration attributeNames = getAttributeNames();
        List<String> attributeNameList = new ArrayList<String>();
        while (attributeNames.hasMoreElements()) {
            attributeNameList.add((String) attributeNames.nextElement());
        }

        return attributeNameList.toArray(new String[0]);
    }

    /**
     * 返回getAttribute的调用结果。
     * @param name 属性名称。
     * @return 属性值。
     * @deprecated 已经过时，请使用getAttribute.
     */
    public Object getValue(String name) {
        return getAttribute(name);
    }

    /**
     * 更新当前请求至缓存。如果已经失效，将直接删除。
     * @return false此缓存已经失效,true缓存继续有效。
     */
    public boolean synchronizationCache() {
        if (invalid) {
            removeRemoteSessionForCache();

            LOGGER.debugLog("Session [{0}] has failed and empty the cache.", id);

            return false;
        } else {
            //头信息每次都需要同步
            updateCacheSessionHeader(sessionHeader);

            //属性键值对只有当改变时才更新。
            if (update) {
                updateCacheSessionAttribute(sessionAttribute);

                LOGGER.debugLog(
                        "Session[{0}] information to the cache synchronization.",
                        id);
            }

            update = false;

            return true;
        }
    }

    /**
     * 初始化方法。
     * 初始化日志记录器。
     * 初始化缓存中的属性容器。
     * 如果缓存中没有相就内容即新建并设定创建时间和最后访问时间为当前时间和为新的会话。
     */
    public void init() {
        if (!cache.containsKey(sessionCacheKeyHeader)) {

            LOGGER.debugLog(
                    "Cache {0} does not exist in the specified session container, so a creation.",
                    sessionCacheKeyHeader);

            initCacheSessionHeader(true);
            //新键需要同步缓存
            update = true;
        } else {

            LOGGER.debugLog(
                    "{0} exists in the cache specified in the session container to update the attribute (isNew = false).",
                    sessionCacheKeyHeader);
            sessionHeader = findCacheSessionHeader();
            sessionHeader.setNewbuild(false);
            //不需要同步缓存,除非有属性更新。
            update = false;
        }

        doHttpSessionListener(AccessType.SET_ATTRIBUTE);
    }

    /**
     * 初始化一个新的Session基本信息。
     */
    private void initCacheSessionHeader(boolean newBuild) {
        Calendar now = Calendar.getInstance();
        sessionHeader = new CacheSessionHeader(now.getTimeInMillis());
        sessionHeader.setLastAccessTime(now.getTimeInMillis());
        sessionHeader.setNewbuild(newBuild);

        sessionAttribute = new CacheSessionAttribute();

        LOGGER.infoLog("Structural properties of container Map[{0}].",
                sessionHeader);
    }

    /**
     * 操作类型
     */
    private enum AccessType {

        SET_ATTRIBUTE,//set
        REMOVE_ATTRIBUTE,//remove
        REPLACE_ATTRIBUTE//replace
    }

    /**
     * 属性值如何实现了HttpSessionBindingListener的处理方法.
     * @param name 操作的属性名称.
     * @param value 操作的属性值.
     * @param setOrRemove true绑定还是false取消绑定.
     */
    private void doHttpSessionBindingListener(
            String name, Object value, AccessType type) {
        if (HttpSessionBindingListener.class.isInstance(value)) {
            HttpSessionBindingListener listener =
                    (HttpSessionBindingListener) value;
            HttpSessionBindingEvent event =
                    new HttpSessionBindingEvent(this, name, value);

            try {
                switch (type) {
                    case SET_ATTRIBUTE:
                        listener.valueBound(event);
                        break;
                    case REMOVE_ATTRIBUTE:
                        listener.valueUnbound(event);
                        break;
                }
            } catch (Exception ex) {
                LOGGER.errorLog(ex);
            }
        }
    }

    /**
     * 操作所有的HttpSessionAttributeListener监听器.
     * @param name 操作的属性名称.
     * @param value 属性值.
     * @param type
     */
    private void doHttpSessionAttributeListener(
            String name, Object value, AccessType type) {
        HttpSessionAttributeListener listener;
        HttpSessionBindingEvent event =
                new HttpSessionBindingEvent(this, name, value);

        LOGGER.debugLog("cache Http sessionAttributeListeners: {0}", Arrays.
                toString(sessionAttributeListeners));

        for (int count = 0; count < sessionAttributeListeners.length; count++) {
            listener = sessionAttributeListeners[count];
            try {
                switch (type) {
                    case SET_ATTRIBUTE:
                        listener.attributeAdded(event);
                        break;
                    case REMOVE_ATTRIBUTE:
                        listener.attributeRemoved(event);
                        break;
                    case REPLACE_ATTRIBUTE:
                        listener.attributeReplaced(event);
                        break;
                }
            } catch (Exception ex) {
                LOGGER.errorLog(ex);
                continue;
            }
        }
    }

    /**
     * 操作Session创建和销毁的监听器列表.
     * @param type 操作类型.
     */
    private void doHttpSessionListener(AccessType type) {
        HttpSessionListener listener;
        HttpSessionEvent event = new HttpSessionEvent(this);

        LOGGER.debugLog("cache Http Session listeners: {0}", Arrays.toString(
                sessionListeners));

        for (int count = 0; count < sessionListeners.length; count++) {
            listener = sessionListeners[count];
            try {
                switch (type) {
                    case SET_ATTRIBUTE:
                        listener.sessionCreated(event);
                        break;
                    case REMOVE_ATTRIBUTE:
                        listener.sessionDestroyed(event);
                        break;
                }
            } catch (Exception ex) {
                LOGGER.errorLog(ex);
                continue;
            }
        }
    }

    /**
     * 判断给定对象是否实现了序列化接口.
     * @param value 被检查的对象.
     * @throws NotSerializableException 没有实现序列化接口
     */
    private void checkSerializable(Object value)
            throws NotSerializableException {
        if (value == null) {
            return;
        }
        if (!Serializable.class.isInstance(value)) {
            throw new NotSerializableException(value.getClass().getName());
        }
    }

    /**
     * 判断当前Session是否已经失效.
     * @throws IllegalStateException Session已经失效的异常.
     */
    private void checkSessionInvalild() throws IllegalStateException {
        if (invalid) {
            throw new IllegalStateException("Session is invalid.");
        }
    }

    /**
     * 查找缓存中的会话属性容器。如果缓存是失效的将会抛出IllegalStateException异常。
     * @return 会话属性容器。
     */
    private CacheSessionHeader findCacheSessionHeader() throws
            IllegalStateException {
        CacheSessionHeader header = null;
        try {
            header = (CacheSessionHeader) cache.get(sessionCacheKeyHeader);
        } catch (CacheNotFoundException ex) {
            //应该找到的远程容器没有找到，所以重新构造一个。原有属性将丢失。
            LOGGER.warnLog("SessionCacheKey[{0}] is not found.",
                    sessionCacheKeyHeader);
            initCacheSessionHeader(false);
            header = sessionHeader;
        }

        return header;
    }

    /**
     * 查找一个缓存中的属性储存bean.如果不存在将返回一个新的空BEAN.
     * @return 用户Session属性键键值对储存bean.
     */
    private CacheSessionAttribute findCacheSessionAttribute() {
        CacheSessionAttribute attribute = sessionAttribute;
        if (attribute != null) {
            return attribute;
        }
        try {
            attribute = (CacheSessionAttribute) cache.get(
                    sessionCacheKeyAttribute);
        } catch (CacheNotFoundException ex) {
            //应该找到的远程容器没有找到，所以重新构造一个。原有属性将丢失。
            LOGGER.warnLog("SessionCacheKey[{0}] is not found.",
                    sessionCacheKeyAttribute);
            attribute = new CacheSessionAttribute();
        }

        sessionAttribute = attribute;
        return attribute;
    }

    /**
     * 更新缓存中的Session属性。
     * @param header Session属性。
     */
    private void updateCacheSessionHeader(CacheSessionHeader header) {
        cache.put(sessionCacheKeyHeader, header);
    }

    /**
     * 更新缓存中的Session的属性键值对。
     * @param attribute Session中的键值对。
     */
    private void updateCacheSessionAttribute(
            CacheSessionAttribute attribute) {
        cache.put(sessionCacheKeyAttribute, attribute);

    }

    /**
     * 移除缓存中的属性容器。
     */
    private void removeRemoteSessionForCache() {
        cache.remove(sessionCacheKeyHeader);
        cache.remove(sessionCacheKeyAttribute);
    }

    /**
     * 迭代器的实现。
     */
    private static class CacheEnumeration implements Enumeration {

        private Iterator iter;

        public CacheEnumeration(Set<String> attributeNames) {
            this.iter = attributeNames.iterator();
        }

        public boolean hasMoreElements() {
            return iter.hasNext();
        }

        public Object nextElement() {
            return iter.next();
        }
    }

    /**
     * 实际进行缓存的储存Session的基本信息.
     */
    public static class CacheSessionHeader implements Externalizable {

        private static final long serialVersionUID = -4825209846787754991L;
        private boolean newbuild;
        private long lastAccessTime;
        private long createTime;

        //用以序列化的构造方法
        public CacheSessionHeader() {
        }

        public CacheSessionHeader(long createTime) {
            this.createTime = createTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getLastAccessTime() {
            return lastAccessTime;
        }

        public void setLastAccessTime(long lastAccessTime) {
            this.lastAccessTime = lastAccessTime;
        }

        public boolean isNewbuild() {
            return newbuild;
        }

        public void setNewbuild(boolean newbuild) {
            this.newbuild = newbuild;
        }

        @Override
        public String toString() {
            StringBuilder buff = new StringBuilder();
            buff.append("CacheSessionHeader{").append("newbuild=").append(
                    newbuild);
            buff.append(",lastAccessTime=").append(lastAccessTime);
            buff.append(",createTime=").append(createTime);
            buff.append("}");
            return buff.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final CacheSessionHeader other = (CacheSessionHeader) obj;
            if (this.newbuild != other.newbuild) {
                return false;
            }
            if (this.lastAccessTime != other.lastAccessTime) {
                return false;
            }
            if (this.createTime != other.createTime) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 29 * hash + (newbuild ? 1 : 0);
            hash = 29 * hash + (int) (lastAccessTime ^ (lastAccessTime >>> 32));
            hash = 29 * hash + (int) (createTime ^ (createTime >>> 32));
            return hash;
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeLong(createTime);
            out.writeLong(lastAccessTime);
            out.writeBoolean(newbuild);
        }

        public void readExternal(ObjectInput in)
                throws IOException, ClassNotFoundException {
            createTime = in.readLong();
            lastAccessTime = in.readLong();
            newbuild = in.readBoolean();
        }
    }

    /**
     * Session的相关属性键值对储存Bean.
     */
    public static class CacheSessionAttribute implements Externalizable {

        private static final long serialVersionUID = -3974429195972771120L;
        private static final String KEY_SPLIT = ",";
        private static String[] TYPE_STRING = new String[0];
        private Map<String, Serializable> attribute;

        public CacheSessionAttribute() {
            attribute = new HashMap<String, Serializable>();
        }

        public void putAttribute(String name, Serializable value) {
            attribute.put(name, value);

            LOGGER.debugLog(
                    "Attribute [name = {0}, value = {1}], into the Session.",
                    name,
                    value);
        }

        public Serializable removeAttribute(String name) {
            Serializable value = attribute.remove(name);

            LOGGER.debugLog(
                    "From the Session {0} removed property.",
                    name);

            return value;
        }

        public Serializable getAttribute(String name) {
            Serializable value = attribute.get(name);

            LOGGER.debugLog(
                    "Session to obtain property from [name ={0},value={1}].",
                    name,
                    value);

            return value;
        }

        public Set<String> getAttributeNames() {
            return attribute.keySet();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final CacheSessionAttribute other = (CacheSessionAttribute) obj;
            if (this.attribute != other.attribute && (this.attribute == null
                    || !this.attribute.equals(other.attribute))) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 61 * hash + (this.attribute != null ? this.attribute.hashCode() : 0);
            return hash;
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            Set<String> keys = attribute.keySet();
            String[] seriaKeys = keys.toArray(TYPE_STRING);
            Serializable[] seriaValues = new Serializable[seriaKeys.length];
            StringBuilder seriaKeyStr = new StringBuilder();
            for (int count = 0; count < seriaKeys.length; count++) {
                seriaValues[count] = attribute.get(seriaKeys[count]);
                seriaKeyStr.append(seriaKeys[count]);
                seriaKeyStr.append(KEY_SPLIT);
            }

            out.writeObject(seriaKeyStr.toString());
            out.writeObject(seriaValues);
        }

        public void readExternal(ObjectInput in)
                throws IOException, ClassNotFoundException {
            String seriaKeyStr = (String) in.readObject();
            String[] seriaKeys = seriaKeyStr.split(KEY_SPLIT);
            seriaKeyStr = null;
            Serializable[] seriaValues = (Serializable[]) in.readObject();

            if (seriaKeys != null
                    && seriaValues != null
                    && seriaKeys.length == seriaValues.length) {
                for (int count = 0; count < seriaKeys.length; count++) {
                    attribute.put(seriaKeys[count], seriaValues[count]);
                }
            } else {
                LOGGER.warnLog("Session attribute serialization fails.");
            }
        }
    }
}
