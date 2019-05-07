package com.foi.MyFinance.controller;

import com.foi.MyFinance.facade.TransactionFacade;
import com.foi.MyFinance.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController
{
    private static final String URL_HOME = "/user/home";
    private static final String VIEW_HOME = "home";
    private static final String MODEL_ATTRIBUTE_TRANSACTIONS = "transactions";

    @Autowired
    private TransactionFacade transactionFacade;

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = URL_HOME, method = RequestMethod.GET)
    public String home(final Model model)
    {
        model.addAttribute(MODEL_ATTRIBUTE_TRANSACTIONS, transactionFacade.findByUser(userFacade.getUserEntity()));
        return VIEW_HOME;
    }
}
