package com.foi.MyFinance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController
{
    private static final String URL_LOGIN = "/login";
    private static final String VIEW_LOGIN = "login";

    @RequestMapping(value = URL_LOGIN, method = RequestMethod.GET)
    public String login()
    {
        return VIEW_LOGIN;
    }
}
