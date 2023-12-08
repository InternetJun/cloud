package org.example.common.core.holder;

import java.util.Map;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/12/8 15:11
 */
public class UserContextHolder {
    private ThreadLocal<Map<String, String>> threadLocal;

    private UserContextHolder() {
        this.threadLocal = new ThreadLocal();
    }

    public static UserContextHolder getInstance() {
        return UserContextHolder.SingletonHolder.sInstance;
    }

    public void setContext(Map<String, String> map) {
        this.threadLocal.set(map);
    }

    public Map<String, String> getContext() {
        return (Map)this.threadLocal.get();
    }

    public void clear() {
        this.threadLocal.remove();
    }

    private static class SingletonHolder {
        private static final UserContextHolder sInstance = new UserContextHolder();

        private SingletonHolder() {
        }
    }
}

