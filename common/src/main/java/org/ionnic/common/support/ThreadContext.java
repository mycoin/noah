package org.ionnic.common.support;

import java.util.HashMap;
import java.util.LinkedList;

public class ThreadContext {
    private static ThreadLocal<ThreadContext> localContext = new ThreadLocal<ThreadContext>();

    private LinkedList<HashMap<Object, Object>> hashMap = new LinkedList<HashMap<Object, Object>>();

    {
        hashMap.addFirst(new HashMap<Object, Object>());
    }

    /**
     * @return
     */
    public static ThreadContext getCurrent() {
        ThreadContext threadContext = localContext.get();
        if (threadContext == null) {
            threadContext = new ThreadContext();
            localContext.set(threadContext);
        }
        return threadContext;
    }

    /**
     * @param key
     * @param value
     */
    public void put(Object key, Object value) {
        hashMap.getFirst().put(key, value);
    }

    /**
     * @param key
     * @return
     */
    public Object get(Object key) {
        for (HashMap<Object, Object> stackFrame : hashMap) {
            Object value = stackFrame.get(key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * @param runnable
     */
    public void runInSubcontext(Runnable runnable) {
        hashMap.addFirst(new HashMap<Object, Object>());
        try {
            runnable.run();
        } finally {
            hashMap.removeFirst();
        }
    }

}