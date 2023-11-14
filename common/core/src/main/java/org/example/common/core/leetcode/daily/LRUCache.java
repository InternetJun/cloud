package org.example.common.core.leetcode.daily;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;

/**
 * @Author: lejun
 * @project: cloud
 * @description:lru缓存，它应该删除最近最少使用的项目。
 * @time: 2023/9/3 12:23
 */
public class LRUCache {
    Hashtable<String, Integer> map = new Hashtable<>();
    HashMap<String, Integer> map1 = new HashMap<>();
    int cap;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();
    public LRUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (!cache.containsValue(key)) {
            return -1;
        }
        // key为最近使用。
        makeRecent(key);
        return cache.get(key);
    }

    private void makeRecent(int key) {
        //加入到最后。
        int value = cache.get(key);
        cache.remove(key);
        cache.put(key, value);
    }

    public void put(int key, int value) {
        if (cache.containsValue(key)) {
            //
            cache.put(key, value);
            makeRecent(key);
            return;
        }
        if (cache.size() >  cap) {
            int old = cache.keySet().iterator().next();
            cache.remove(old);
        }
        // key放入
        cache.put(key, value);
    }
}
