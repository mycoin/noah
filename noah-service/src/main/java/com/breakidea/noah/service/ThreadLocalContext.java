package com.breakidea.noah.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ThreadLocalContext {

    private final static ThreadLocal<String> bizCodeLocal = new ThreadLocal<>();

    private final static ThreadLocal<Long> bizIdLocal = new ThreadLocal<>();

    private final static ThreadLocal<String> languageLocal = new ThreadLocal<>();

    private final static ThreadLocal<Map<String, Object>> requestContext = new ThreadLocal<>();

    private final static ThreadLocal<String> sellerNameLocal = new ThreadLocal<>();

    private final static ThreadLocal<String> shortLangLocal = new ThreadLocal<>();

    private final static ThreadLocal<Long> siteCategoryIdLocal = new ThreadLocal<>();

    private final static ThreadLocal<Long> siteIdLocal = new ThreadLocal<>();

    private final static ThreadLocal<String> uniqueKeyLocal = new ThreadLocal<>();

    public static Map<String, Object> buildModuleContext() {
        Map<String, Object> context = new HashMap<String, Object>();
        context.put("clientType", getContext("clientType"));
        context.put("version", getContext("version"));
        context.put("aliMemberId", getContext("bizMemberId"));
        context.put("bizId", getContext("bizMemberId"));
        context.put("needMock", getContext("needMock"));

        return context;
    }

    public static void clear() {
        bizCodeLocal.remove();
        bizIdLocal.remove();
        siteCategoryIdLocal.remove();
        siteIdLocal.remove();
        languageLocal.remove();
        sellerNameLocal.remove();
        requestContext.remove();
        uniqueKeyLocal.remove();
        shortLangLocal.remove();
    }

    public static String getBizCodeLocal() {
        return bizCodeLocal.get();
    }

    public static Long getBizIdLocal() {
        return bizIdLocal.get();
    }

    public static Object getContext(String key) {
        if (requestContext.get().containsKey(key)) {
            return requestContext.get().get(key);
        }
        return null;
    }

    public static String getLanguageLocal() {
        if (StringUtils.isEmpty(languageLocal.get())) {
            return "english";
        }
        return languageLocal.get();
    }

    public static String getSellerNameLocal() {
        return sellerNameLocal.get();
    }

    public static String getShortLangLocal() {
        if (StringUtils.isEmpty(shortLangLocal.get())) {
            return "en";
        }
        return shortLangLocal.get();
    }

    public static Long getSiteCategoryIdLocal() {

        return siteCategoryIdLocal.get();
    }

    public static Long getSiteIdLocal() {

        return siteIdLocal.get();
    }

    public static String getUniqueKeyLocal() {
        return uniqueKeyLocal.get();
    }

    public static void putContext(String key, Object val) {
        if (requestContext.get() == null) {
            requestContext.set(new HashMap<String, Object>());
        }
        requestContext.get().put(key, val);
    }

    public static void setBizCodeLocal(String bizCode) {
        bizCodeLocal.set(bizCode);
    }

    public static void setBizIdLocal(Long bizId) {
        bizIdLocal.set(bizId);
    }

    public static void setLanguageLocal(String language) {
        languageLocal.set(language);
    }

    public static void setSellerNameLocal(String sellerName) {
        sellerNameLocal.set(sellerName);
    }

    public static void setShortLangLocal(String language) {
        shortLangLocal.set(language);
    }

    public static void setSiteCategoryIdLocal(Long siteCategoryId) {
        siteCategoryIdLocal.set(siteCategoryId);
    }

    public static void setSiteIdLocal(Long siteId) {
        siteIdLocal.set(siteId);
    }

    public static void setUniqueKeyLocal(String uniqueKey) {
        uniqueKeyLocal.set(uniqueKey);
    }
}
