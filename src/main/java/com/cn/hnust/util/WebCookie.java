package com.cn.hnust.util;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class WebCookie {
	public static String cookie(HttpServletRequest request, String cookiev) {
		Cookie[] cookie = request.getCookies();
		if (cookie != null) {
			for (Cookie cookie2 : cookie) {
				if (cookie2.getName().equals(cookiev)) {
					return cookie2.getValue();
				}
			}
		}
		return null;
	}

	public static String getSessionValue(HttpServletRequest request,
			String cookiev) {
		HttpSession session = request.getSession();
		Object val = session.getAttribute(cookiev);
		if (val == null || "".equals(val)) {
			return null;
		}
		return val.toString();
	}

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 *  * 将cookie封装到Map里面  * @param request  * @return  
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	
	
	// 从cookies中获取username
	public static String getUserName(HttpServletRequest request) {		
		String userName  = WebCookie.cookie(request, "name");		
		return userName;
	}
	
	
	// 从cookies中获取role
	public static Integer getRoleNo(HttpServletRequest request) {	
		Integer roleNo= null;
		String role  = WebCookie.cookie(request, "role");	
		if(StringUtils.isNotBlank(role)){
			roleNo = Integer.parseInt(role);
		}
		return roleNo;
	}

}
