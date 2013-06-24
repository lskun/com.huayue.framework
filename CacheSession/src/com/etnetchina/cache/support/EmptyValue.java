package com.etnetchina.cache.support;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 表示一个值为null。
 * @version 1.01 2009-12-23
 * @since 1.5
 * @author Mike
 */
public class EmptyValue implements Externalizable {

    private static final long serialVersionUID = -7781500946567204891L;
    private static final EmptyValue value = new EmptyValue();

    /**
     * 构造方法。
     */
    public EmptyValue() {
    }

    /**
     * 获取已经有的一个空值代表，此方法返回的将是唯一的一个实例。
     * @return 唯的一空值代表。
     */
    public static EmptyValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return null;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
    }

    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
    }
}