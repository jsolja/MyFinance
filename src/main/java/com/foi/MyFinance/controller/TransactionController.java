package com.foi.MyFinance.controller;

import com.foi.MyFinance.model.TransactionModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransactionController
{
    private static final String URL_TRANSACTION = "/user/transaction";
    private static final String VIEW_TRANSACTION = "transaction";
    private static final String MODEL_ATTRIBUTE_TRANSACTION_MODEL = "transactionModel";

    @RequestMapping(value = URL_TRANSACTION, method = RequestMethod.GET)
    public String getViewTransaction(final Model model)
    {
        model.addAttribute(MODEL_ATTRIBUTE_TRANSACTION_MODEL, new TransactionModel());
        return VIEW_TRANSACTION;
    }
}
