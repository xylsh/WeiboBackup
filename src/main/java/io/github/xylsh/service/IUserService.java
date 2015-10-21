package io.github.xylsh.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by apple on 15-4-12.
 */
public interface IUserService {

    boolean isUserLogin(HttpServletRequest request);

    boolean login(String username, String password, HttpServletRequest request);

    void markUserLogin(HttpServletRequest request);

    void markUserLogOut(HttpServletRequest request);
}
