package com.foi.userservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController
{
    private static final String VIEW_LOGIN = "login";

    @RequestMapping(value = VIEW_LOGIN, method = RequestMethod.GET)
    public String getViewLogin()
    {
        return VIEW_LOGIN;
    }
}
