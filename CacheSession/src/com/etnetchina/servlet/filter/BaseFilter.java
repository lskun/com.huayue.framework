package com.etnetchina.servlet.filter;

import com.etnetchina.log.LogUtil;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import org.apache.commons.logging.LogFactory;

/**
 * 过滤器的超类。
 * @author Mike
 * @version 1.00 2010-6-25
 * @since 1.5
 */
public abstract class BaseFilter implements Filter {

    protected FilterConfig filterConfig;
    protected LogUtil logger;

    public BaseFilter() {
        logger = new LogUtil(LogFactory.getLog(BaseFilter.class));
    }

    /**
     * 初始化的实现。
     * @param config
     * @throws javax.servlet.ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;

        logger.infoLog("{0} filter initialization.",getClass().getName());

    }

    /**
     * 销毁方法。
     */
    public void destroy() {
        logger.infoLog("{0} filter destroy.",getClass().getName());
    }
}
