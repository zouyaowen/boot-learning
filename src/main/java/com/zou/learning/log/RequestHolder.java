package com.zou.learning.log;

import java.util.HashMap;

/**
 * @author zouyaowen
 * @date 2020-05
 */
public class RequestHolder {

    private final static ThreadLocal<HashMap<String, Object>> REQUEST_HOLDER = new ThreadLocal<>();

    /**
     * 设置线程变量map
     *
     * @param map 线程属性
     */
    public static void set(HashMap<String, Object> map) {
        REQUEST_HOLDER.set(map);
    }

    /**
     * 存储数据
     *
     * @param key    数据key
     * @param object 数据value
     */
    public static void add(String key, Object object) {
        REQUEST_HOLDER.get().put(key, object);
    }

    /**
     * 获取存储的数据
     *
     * @param key 存储数据的key
     * @return 获取数据
     */
    public static Object get(String key) {
        return REQUEST_HOLDER.get().get(key);
    }

    /**
     * 删除数据
     */
    public static void remove() {
        REQUEST_HOLDER.remove();
    }
}
