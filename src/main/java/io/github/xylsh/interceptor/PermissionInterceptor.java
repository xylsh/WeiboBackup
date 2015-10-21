package io.github.xylsh.interceptor;

import io.github.xylsh.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于权限管理的拦截器。访问/admin/...需要登陆。
 * Created by apple on 15-4-13.
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionInterceptor.class);

    @Resource
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if (!userService.isUserLogin(request)) {
            response.sendRedirect(getRedirectURI(request, "login.htm"));
            return false;
        }

        return true;
    }

    private String getRedirectURI(HttpServletRequest request, String resourceName) {
        String uri = request.getRequestURI();

        int slashCount = 0;
        for (int i = 0; i < uri.length(); i++) {
            if (uri.charAt(i) == '/') {
                slashCount++;
            }
        }

        StringBuilder redirectURI = new StringBuilder();
        for (int i = 1; i < slashCount; i++) {
            redirectURI.append("../");
        }

        return redirectURI.append(resourceName).toString();
    }
}
