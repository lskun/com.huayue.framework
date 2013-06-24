package com.etnetchina.id;

import java.util.UUID;

/**
 * 实体对象的ID生成方便方法。
 * @version 1.00, 08/5/14
 * @since 1.5
 * @author Mike
 */
public class IdGenerate {

    private IdGenerate() {
    }

    /**
     * 以UUID的策略生成一个32长度的字符串，在同一时空中保持唯一。
     * @return UUID128位长度字符串。
     */
    public static String getUUIDString() {
        String id = UUID.randomUUID().toString();
        id = id.replace("-", "");
        return id;
    }
}
