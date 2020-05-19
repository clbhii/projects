package com.cl.common.util.common;

/**
 * by cl at 2020/5/18 0018
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }
}
