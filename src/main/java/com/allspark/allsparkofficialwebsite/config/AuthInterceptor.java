package com.allspark.allsparkofficialwebsite.config;

import com.allspark.allsparkofficialwebsite.common.ErrorCode;
import com.allspark.allsparkofficialwebsite.exception.BusinessException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器：/admin/** 接口需要登录后才可访问
 */
public class AuthInterceptor implements HandlerInterceptor {

    public static final String LOGIN_ADMIN = "loginAdmin";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(LOGIN_ADMIN) != null) {
            return true;
        }
        throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
    }
}