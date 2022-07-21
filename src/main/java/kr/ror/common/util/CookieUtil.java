package kr.ror.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
    public CookieUtil() {}
    
    // cookie 가져오기
    public static String getCookie(HttpServletRequest request, String key) 
    {
        if(key == null || key.equals("") || key.equals("null")) {
            return null;
        }
        
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if(key.equals(cookie.getName())) {
                try {
                    return URLDecoder.decode(cookie.getValue(), "utf-8");
                } catch (Exception e) {
                    return null;
                }
            }
        }
        
        return null;
    }
    
    // Set Cookie
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String key, String value)
    {
        setCookie(request, response, key, value, 30 * 24 * 3600);
	}

    // Set Cookie
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String key, String value, int expiry)
    {
        boolean existKey = false;
        if(key == null || key.equals("") || key.equals("null")) {
            return;
        }
        
        try {
            value = URLEncoder.encode(value, "utf-8");
        } catch (Exception e) {
            value = null;
        }
        
        
        
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if(key.equals(cookie.getName())) {
                    existKey = true;
                    cookie.setPath("/");
                    cookie.setMaxAge(expiry);
                    cookie.setValue(value);
                    response.addCookie(cookie);
                }
            }
        }
        
        if(!existKey) {
            Cookie cookie = new Cookie(key, value);
            cookie.setPath("/");
            cookie.setMaxAge(expiry);
            response.addCookie(cookie);
        }
    }


    // Remove Cookie
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String key)
    {
        if(key == null || key.equals("") || key.equals("null")) {
            return;
        }
        
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if(key.equals(cookie.getName())) {
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }
}
