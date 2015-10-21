package io.github.xylsh.service.impl;

import io.github.xylsh.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by apple on 15-4-12.
 */
@Service
public class SessionUserService implements IUserService {

    //todo:移到配置文件中
//    private static final String USER_NAME = "admin";
//    private static final String USER_PASS = "admin";
    private static final String LOGIN_ATTRIBUTE_NAME = "isLogin";

    @Value("#{settings['system.user']}")
    private String USER_NAME;
    @Value("#{settings['system.password']}")
    private String USER_PASS;

    public boolean isUserLogin(HttpServletRequest request) {
        Boolean isLogin = (Boolean) request.getSession().getAttribute(LOGIN_ATTRIBUTE_NAME);
        if (isLogin == null || isLogin == false) {
            return false;
        }
        return true;
    }

    public boolean login(String username, String password, HttpServletRequest request) {
        if (USER_NAME.equals(username) && USER_PASS.equals(password)) {
            markUserLogin(request);
            return true;
        }
        return false;
    }

    public void markUserLogin(HttpServletRequest request) {
        request.getSession().setAttribute(LOGIN_ATTRIBUTE_NAME, Boolean.TRUE);
    }

    public void markUserLogOut(HttpServletRequest request) {
        request.getSession().removeAttribute(LOGIN_ATTRIBUTE_NAME);
    }

}
