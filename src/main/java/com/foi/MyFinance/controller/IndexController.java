package com.foi.MyFinance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController
{
    private static final String REDIRECT = "redirect";
    private static final String COLON = ":";
    private static final String INDEX_PAGE = "/";
    private static final String LOGIN_PAGE = "/login";

    @RequestMapping(value = INDEX_PAGE, method = RequestMethod.GET)
    private String index()
    {
        return REDIRECT + COLON + LOGIN_PAGE;
    }
}
