package com.unicom.blog.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReqUtil {

    public static String getRequestURL(HttpServletRequest request) {
        if(request == null) {
            return "";
        }
        String url = "";
        url = request.getContextPath();
        url = url + request.getServletPath();
        Enumeration<String> names = request.getParameterNames();
        int i = 0;
        if(!"".equals(request.getQueryString()) || request.getQueryString() != null) {
            // url = url + "?" + request.getQueryString();
        }
        if(names != null) {
            while(names.hasMoreElements()) {
                String name = (String) names.nextElement();
                if(i == 0) {
                    url = url + "?";
                } else {
                    url = url + "&";
                }
                i++;
                String value = request.getParameter(name);
                if(value == null) {
                    value = "";
                }
                url = url + name + "=<" + name + ">";
                try {
                    // java.net.URLEncoder.encode(url, "ISO-8859");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            // String enUrl = java.net.URLEncoder.encode(url, "utf-8");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return url;
    }

    /**
     * 返回String类型的参数值
     * 
     * @param request
     *            HttpServletRequest
     * @param name
     *            String 参数名称
     * @return 字符串
     */
    public static String getString(HttpServletRequest request, String name) {
        return getString(request, name, null);
    }

    /**
     * 返回int类型的参数值
     * 
     * @param request
     *            HttpServletRequest
     * @param name
     *            String 参数名称
     * @return 整型
     */
    public static Integer getInt(HttpServletRequest request, String name) {
        return getInt(request, name, null);
    }

    /**
     * 返回int类型的参数值
     * 
     * @param request
     *            HttpServletRequest
     * @param name
     *            String 参数名称
     * @param defaultValue
     *            String 如果为空，则返回默认值
     * @return 整型
     */
    public static Integer getInt(HttpServletRequest request, String name, Integer defaultValue) {
        String paramValue = request.getParameter(name);
        Integer def = null;
        if(StringUtil.isEmpty(paramValue)) {
            def = defaultValue;
        } else {
            def = Integer.parseInt(paramValue);
        }
        return def;
    }

    /**
     * 返回String类型的参数值
     * 
     * @param request
     *            HttpServletRequest
     * @param name
     *            String 参数名称
     * @param defaultValue
     *            String 如果为空，返回默认值
     * @return 字符串
     */

    public static String getString(HttpServletRequest request, String name, String defaultValue) {
        String paramValue = request.getParameter(name);
        if(StringUtil.isEmpty(paramValue)) {
            paramValue = defaultValue;
        }
        return paramValue;
    }

    /**
     * 设置编码
     * @param request
     * @param resp
     */
    public static void setEncoding(HttpServletRequest request, HttpServletResponse resp) {
        try {
            request.setCharacterEncoding("utf-8");
            resp.setContentType("application/json;charset=utf-8");//指定返回的格式为JSON格式
            resp.setCharacterEncoding("UTF-8");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
