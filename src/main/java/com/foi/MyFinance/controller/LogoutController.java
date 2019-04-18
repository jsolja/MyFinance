package com.foi.MyFinance.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
public class LogoutController
{
    private static final String REDIRECT = "redirect";
    private static final String COLON = ":";
    private static final String LOGOUT_PAGE = "/logout";
    private static final String LOGOUT_SUCCESS_PAGE = "/login?logout";

    @RequestMapping(value = LOGOUT_PAGE, method = RequestMethod.GET)
    public String logout(final HttpServletRequest request, final HttpServletResponse response)
    {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!Objects.isNull(authentication))
        {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return REDIRECT + COLON + LOGOUT_SUCCESS_PAGE;
    }
}
