package com.pcwang.order.utils;

import java.util.Random;

/**
 * 生成ID
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式；时间戳+随机数
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 1000000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
