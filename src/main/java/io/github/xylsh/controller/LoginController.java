package io.github.xylsh.controller;

import static com.google.common.base.Preconditions.*;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.github.xylsh.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by apple on 15-4-12.
 */
@Controller
public class LoginController {

    @Resource
    private IUserService userService;

    @RequestMapping("/")
    public String homePage() {
        return "redirect:/login.htm";
    }

    @RequestMapping("/login.htm")
    public String loginPage(HttpServletRequest request) {
        if (!userService.isUserLogin(request)) {
            return "login";
        }
        return "redirect:/admin/dashboard.htm";
    }

    @RequestMapping(value = "/login/login.action", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        checkArgument(StringUtils.isNotBlank(username));
        checkArgument(StringUtils.isNotBlank(password));

        if (userService.login(username, password, request)) {
            return "redirect:/admin/dashboard.htm";
        }
        return "redirect:/login.htm";
    }
}
