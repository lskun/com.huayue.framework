package com.etnetchina.serialize;

/**
 * 无法进行反序列化。
 * @author Mike
 * @version 1.00 2011-8-9
 * @since 1.5
 */
public class CanNotBeUnSerializedException extends Exception {

    private static final long serialVersionUID = -4928715628900219251L;

    public CanNotBeUnSerializedException(Throwable cause) {
        super(cause);
    }

    public CanNotBeUnSerializedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotBeUnSerializedException(String message) {
        super(message);
    }

    public CanNotBeUnSerializedException() {
    }
}
