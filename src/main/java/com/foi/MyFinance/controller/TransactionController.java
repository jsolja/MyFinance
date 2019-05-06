package com.foi.MyFinance.controller;

import com.foi.MyFinance.facade.TransactionFacade;
import com.foi.MyFinance.facade.UserFacade;
import com.foi.MyFinance.model.TransactionModel;
import com.foi.MyFinance.validation.TransactionFieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class TransactionController
{
    private static final String URL_TRANSACTION = "/user/transaction";
    private static final String VIEW_TRANSACTION = "transaction";
    private static final String MODEL_ATTRIBUTE_TRANSACTION_MODEL = "transactionModel";
    private static final String MODEL_ATTRIBUTE_USER_BALANCE = "balance";
    private static final String MODEL_ATTRIBUTE_SUCCESS = "successMessage";
    private static final String MODEL_ATTRIBUTE_SUCCESS_MESSAGE = "You have successfully created transaction.";

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private TransactionFacade transactionFacade;

    @Autowired
    private TransactionFieldsValidator transactionFieldsValidator;

    @RequestMapping(value = URL_TRANSACTION, method = RequestMethod.GET)
    public String getViewTransaction(final Model model)
    {
        model.addAttribute(MODEL_ATTRIBUTE_USER_BALANCE, userFacade.getUserModel().getBalance());
        model.addAttribute(MODEL_ATTRIBUTE_TRANSACTION_MODEL, new TransactionModel());
        return VIEW_TRANSACTION;
    }

    @RequestMapping(value = URL_TRANSACTION, method = RequestMethod.POST)
    public String postViewTransaction(
            @ModelAttribute(MODEL_ATTRIBUTE_TRANSACTION_MODEL)
            @Valid
            final TransactionModel transactionModel, final Model model, final BindingResult bindingResult)
    {
        ValidationUtils.invokeValidator(transactionFieldsValidator, transactionModel, bindingResult);
        if (!bindingResult.hasErrors())
        {
            transactionFacade.makeTransaction(transactionModel);
            model.addAttribute(MODEL_ATTRIBUTE_SUCCESS, MODEL_ATTRIBUTE_SUCCESS_MESSAGE);
        }
        model.addAttribute(MODEL_ATTRIBUTE_USER_BALANCE, userFacade.getUserModel().getBalance());
        return VIEW_TRANSACTION;
    }
}
