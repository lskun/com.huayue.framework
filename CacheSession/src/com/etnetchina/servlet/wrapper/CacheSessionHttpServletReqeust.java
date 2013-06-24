package com.etnetchina.servlet.wrapper;

import com.etnetchina.id.IdGenerate;
import com.etnetchina.log.LogUtil;
import com.etnetchina.servlet.util.WebUtil;
import com.etnetchina.servlet.session.CacheHttpSession;
import java.util.Arrays;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import org.apache.commons.logging.LogFactory;

/**
 * HttpServletRequest的包装器，修改了获取Session方法的行为。
 * 现在将返回一个实际型别是com.etnetchina.session.cache.CacheHttpSession的HttpSession
 * 实例。
 *
 * 判断Session是否为失效超过最大不活动时间的时机是每次获取Session时。
 * 比如getSession()和getSession(true)都将造成更新最后访问时间。
 * 但是getSession(false)被调用时，如果本身没有绑定Session那么不会有这个动作。
 *
 * @author Mike
 * @version 2.00 2010-11-12
 * @since 1.5
 * @see com.etnetchina.session.cache.CacheHttpSession
 */
public class CacheSessionHttpServletReqeust extends HttpServletRequestWrapper {

    private static final int COOKIE_TIMELIVE = -1;//缓存记录Cookie的生存期为关闭浏览器
    private ServletContext context;
    private int maxInactiveInterval = 0;
    private CacheHttpSession cacheSession;
    private String sessionCookieName;
    private String cookieDomain;
    private String cookieContextPath;
    private HttpServletResponse response;
    private LogUtil logger = new LogUtil(
            LogFactory.getLog(CacheSessionHttpServletReqeust.class));
    private HttpSessionAttributeListener[] sessionAttributeListeners;
    private HttpSessionListener[] sessionListeners;

    /**
     * 构造一个HttpServletRequest的包装器。
     * @param request 原始请求。
     * @param response 原始响应。
     * @param context 环境。
     */
    public CacheSessionHttpServletReqeust(
            HttpServletRequest request,
            HttpServletResponse response,
            ServletContext context) {
        super(request);
        this.context = context;
        this.response = response;
    }

    /**
     * 获取会话实例，如果不存在则创建。
     * @return 会话实例。
     */
    @Override
    public HttpSession getSession() {
        return doGetSession(true);
    }

    /**
     * 获取会话实例，调用者指定如果不存在时是否创建一个新的。
     * @param create true不存在则创建，false不存在返回null.
     * @return 会话实例。
     */
    @Override
    public HttpSession getSession(boolean create) {
        return doGetSession(create);
    }

    /**
     * 获取在Cookie中保存sessionID的属性名称.
     * @return 属性名称.
     */
    public String getSessionCookieName() {
        return sessionCookieName;
    }

    /**
     * 设置在Cookie中保存sessionID的属性名称.
     * @param sessionCookieName 属性名称。
     */
    public void setSessionCookieName(String sessionCookieName) {
        this.sessionCookieName = sessionCookieName;
    }

    /**
     * cookie的存放域。
     * @return 存放域。
     */
    public String getCookieDomain() {
        return cookieDomain;
    }

    /**
     * 设置cookie的存放域。
     * @param cookieDomain 存放域。
     */
    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    /**
     * Cookie的有效路径。
     * @return 有效路径。
     */
    public String getCookieContextPath() {
        return cookieContextPath;
    }

    /**
     * 设置Session的Cookie有效路径。
     * @param cookieContextPath 有效路径。
     */
    public void setCookieContextPath(String cookieContextPath) {
        this.cookieContextPath = cookieContextPath;
    }

    /**
     * 会话的最大不活动时间。单位秒。
     * @return 最大不活动时间。
     */
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    /**
     * 设置会话的最大不活动时间。单位秒。
     * @param maxInactiveInterval 最大不活动时间。
     */
    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    /**
     * 获取当前的CacheHttpSession实例。
     * @return　CacheHttpSession实例。
     */
    public CacheHttpSession currentSession() {
        return cacheSession;
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

    /**
     * 实际构造会话实例的方法。根据参数来决定如果当前没有绑定时是否进行创建。
     * @param create true创建，false不创建。
     * @return 会话实例。
     */
    private HttpSession doGetSession(boolean create) {
        if (cacheSession != null) {
            //本地已经有对象，直接返回。
            logger.debugLog("Session[{0}] was existed.", cacheSession.getId());
        } else {
            Cookie cookie = WebUtil.findCookie(this, getSessionCookieName());
            if (cookie != null) {

                logger.debugLog("Find session`s id from cookie.[{0}]",
                        cookie.getValue());

                cacheSession = buildCacheHttpSession(cookie.getValue(), false);
            } else {
                cacheSession = buildCacheHttpSession(create);
            }
        }

        if (cacheSession != null) {
            //判断是否已经超过了最大不活动时间
            if (cacheSession.isInvalid()) {
                cacheSession.invalidate();
                cacheSession.synchronizationCache();
                cacheSession = buildCacheHttpSession(create);
            }

            if (cacheSession != null) {
                cacheSession.access();
            }
        }

        return cacheSession;
    }

    /**
     * 根据指定的id构造一个新的会话实例。
     * @param sessionId 会话id.
     * @param cookie 是否更新cookie值。true更新，false不更新。
     * @return 会话实例。
     */
    private CacheHttpSession buildCacheHttpSession(String sessionId,
            boolean cookie) {
        CacheHttpSession session = null;
        session = new CacheHttpSession(
                context,
                sessionId);
        session.setMaxInactiveInterval(maxInactiveInterval);
        session.setSessionAttributeListeners(sessionAttributeListeners);
        session.setSessionListeners(sessionListeners);
        session.init();

        if (cookie) {
            WebUtil.addCookie(
                    this,
                    response,
                    getSessionCookieName(),
                    sessionId,
                    getCookieDomain(),
                    getCookieContextPath(),
                    COOKIE_TIMELIVE);
        }

        return session;
    }

    /**
     * 以UUID的方式构造一个会话实例。如果create为false则返回null.
     * @param create false方法调用返回null.
     * @return 会话实例。
     */
    private CacheHttpSession buildCacheHttpSession(boolean create) {
        if (create) {
            CacheHttpSession session = buildCacheHttpSession(
                    IdGenerate.getUUIDString(),
                    true);
            logger.debugLog("Build new session[{0}].", session.getId());
            return session;
        } else {
            return null;
        }
    }
}
