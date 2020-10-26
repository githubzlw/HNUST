package com.cn.hnust.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.cn.hnust.util
 * @date:2020-10-26
 */
public class SessionIdUtil {

    private static volatile Map<String, String> sessionMap = new HashMap<>();
    private static volatile Map<String, Long> timeMap = new HashMap<>();
    private static volatile long count = 0;


    public synchronized static void setUserName(String sessionId, String userName) {
        if (StringUtils.isNotBlank(sessionId)) {
            count++;
            sessionMap.put(sessionId, userName);
            timeMap.put(sessionId, System.currentTimeMillis());
        }
        if (count % 100 == 0) {
            dealSessionId();
        }
    }

    public static String getUserName(String sessionId) {
        return sessionMap.get(sessionId);
    }

    private static void dealSessionId() {
        Map<String, String> tempSessionMap = new HashMap<>();
        Map<String, Long> tempTimeMap = new HashMap<>();
        timeMap.forEach((k, v) -> {
            if (1000 * 60 * 60 * 24 * 7 > System.currentTimeMillis() - v) {
                tempSessionMap.put(k, sessionMap.get(k));
                tempTimeMap.put(k, v);
            }
        });
        timeMap.clear();
        sessionMap.clear();
        timeMap = tempTimeMap;
        sessionMap = tempSessionMap;
    }

}
