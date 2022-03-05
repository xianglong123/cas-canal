package com.cas.utils;

import com.cas.common.Constants;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2022/3/5 4:35 下午
 * @desc
 */
public class RedisFixUtil {

    public static String getFix(String id) {
        return Constants.COUNTER_FREFIX + id;
    }

}
