package com.foi.userservice.controller;

import com.foi.userservice.entity.TransactionEntity;
import com.foi.userservice.facade.UserFacade;
import com.foi.userservice.model.TransactionModel;
import com.foi.userservice.validation.TransactionFieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

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
    private static final String URL_IMPORT_TRANSACTION = "/user/import-transaction";
    private static final String VIEW_IMPORT_TRANSACTION = "import-transaction";
    private static final String MODEL_ATTRIBUTE_ERROR = "error";
    private static final String TRANSACTION_SERVICE_URL = "http://transaction-service/";
    private static final String MAKE_TRANSACTION = "/make-transaction";
    private static final String MODEL_ATTRIBUTE_ERROR_MESSAGE = "Something went wrong.";

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private TransactionFieldsValidator transactionFieldsValidator;

    @Autowired
    private RestTemplate restTemplate;

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
            transactionModel.setUserEntity(userFacade.getUserEntity());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity(transactionModel, headers);
            ResponseEntity<TransactionEntity> result = restTemplate.exchange(
                    TRANSACTION_SERVICE_URL + MAKE_TRANSACTION,
                    HttpMethod.POST,
                    httpEntity,
                    TransactionEntity.class
            );
            if (ObjectUtils.isEmpty(result.getBody()))
            {
                model.addAttribute(MODEL_ATTRIBUTE_ERROR, MODEL_ATTRIBUTE_ERROR_MESSAGE);
            }
            model.addAttribute(MODEL_ATTRIBUTE_SUCCESS, MODEL_ATTRIBUTE_SUCCESS_MESSAGE);
        }
        model.addAttribute(MODEL_ATTRIBUTE_USER_BALANCE, userFacade.getUserModel().getBalance());
        return VIEW_TRANSACTION;
    }

    @RequestMapping(value = URL_IMPORT_TRANSACTION, method = RequestMethod.GET)
    public String getViewImportTransaction(final Model model)
    {
        return VIEW_IMPORT_TRANSACTION;
    }
}
