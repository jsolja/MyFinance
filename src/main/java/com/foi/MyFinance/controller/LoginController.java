package com.foi.MyFinance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController
{
    private static final String LOGIN_PAGE = "login";

    @RequestMapping(value = LOGIN_PAGE, method = RequestMethod.GET)
    public String login()
    {
        return LOGIN_PAGE;
    }
}
