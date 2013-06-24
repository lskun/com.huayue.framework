package com.etnetchina.log;

import com.etnetchina.util.CheckUtil;
import org.apache.commons.logging.Log;

/**
 * 一个日志记录的帮助对象。
 * 记录消息时消息中可以使用{0}之类的来表示一个占位符，此占位符会在写入日志后被指定的数据替换。
 *
 * 例如：写入一下DEBUG消息。LogUtil.debugLog("当前时间是{0}.","12:00");
 * 那么最终写入日志的将是这样一条语句，"当前时间是12:00."。
 * 其中占位符中的数字代表是需要使用第几个参数来替换。
 * 参考"com.etnetchina.util.CheckUtil"中的"replaceArgs"方法。
 *
 * @author Mike
 * @version 1.00 2010-5-6
 * @since 1.5
 * @see com.etnetchina.util.CheckUtil
 */
public class LogUtil {

    private Log logger;//日志记录器。
    private static Object[] empty = null;

    /**
     * 构造一个新的日志帮助实例。
     * @param logger 使用的日志记录器。
     */
    public LogUtil(final Log logger) {
        this.logger = logger;
    }

    /**
     * 记录一个无参数的消息。
     * @param message 消息。
     */
    public void infoLog(String message) {
        infoLog(message, empty);
    }

    /**
     * 记录一个消息，消息中的参数将替换成params指定的值。
     * @param message 消息。
     * @param params 消息中的替换值。
     */
    public void infoLog(String message, Object... params) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException(
                    "Can not record the message blank.");
        }

        if (logger != null) {
            if (logger.isInfoEnabled()) {
                logger.info(
                        CheckUtil.replaceArgs(message, params));
            }
        }

    }

    /**
     * 记录一个DEBUG消息。
     * @param message 消息。
     */
    public void debugLog(String message) {
        debugLog(message, empty);
    }

    /**
     * 记录一个DEBUG消息，消息中的参数将替换成params指定的值。
     * @param message DEBUG消息。
     * @param params 消息中的替换值。
     */
    public void debugLog(String message, Object... params) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException(
                    "Can not record the message blank.");
        }
        if (logger != null) {
            if (logger.isDebugEnabled()) {
                logger.debug(
                        CheckUtil.replaceArgs(message, params));
            }
        }
    }

    /**
     * 记录一个警告消息。
     * @param message 警告消息。
     */
    public void warnLog(String message) {
        warnLog(message, empty);
    }

    /**
     * 记录一个警告消息，消息中的参数将替换成params指定的值。
     * @param message 警告消息。
     * @param params 消息中的替换值。
     */
    public void warnLog(String message, Object... params) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException(
                    "Can not record the message blank.");
        }
        if (logger != null) {
            if (logger.isWarnEnabled()) {
                logger.warn(
                        CheckUtil.replaceArgs(message, params));
            }
        }
    }

    /**
     * 记录一个错误消息。
     * @param ex 错误消息。
     */
    public void errorLog(Exception ex) {
        errorLog(ex, ex.getMessage() == null ? "" : ex.getMessage(), empty);
    }

    /**
     * 往日志中输出一个错误。错误和错误消息必须设置。
     * @param ex 错误实例。
     * @param message 错误消息。
     * @param params 消息中的替换值。
     */
    public void errorLog(Exception ex, String message, Object... params) {
        if (ex == null) {
            throw new IllegalArgumentException(
                    "Can not record the error message empty.");
        }

        if (message == null) {
            throw new IllegalArgumentException(
                    "Can not record the message blank.");
        }

        if (logger != null) {
            if (logger.isErrorEnabled()) {
                String nowMessage = CheckUtil.replaceArgs(message, params);
                logger.error(nowMessage, ex);
            }
        }
    }

    /**
     * 是否打开了警告日志缓别.
     * @return true打开,false没有打开.
     */
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    /**
     * 是否打开了DEBUG日志缓别.
     * @return true打开,false没有打开.
     */
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * 是否打开了信息日志缓别.
     * @return true打开,false没有打开.
     */
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * 是否打开了错误日志缓别.
     * @return true打开,false没有打开.
     */
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }
}
