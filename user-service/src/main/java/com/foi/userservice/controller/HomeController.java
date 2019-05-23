package com.foi.userservice.controller;

import com.foi.userservice.facade.UserFacade;
import com.foi.userservice.model.TransactionListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController
{
    private static final String URL_HOME = "/user/home";
    private static final String VIEW_HOME = "home";
    private static final String MODEL_ATTRIBUTE_TRANSACTIONS = "transactions";
    private static final String URL_IMPORT_HOME = "/user/import";
    private static final String MODEL_ATTRIBUTE_ERROR = "error";
    private static final String PARAMETER_FILE = "file";
    private static final String TRANSACTION_SERVICE_URL = "http://transaction-service/";
    private static final String FIND_BY_USER = "/find-by-user";
    private static final String IMPORT_TRANSACTIONS = "/import-transactions";

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = URL_HOME, method = RequestMethod.GET)
    public String home(final Model model)
    {
        ResponseEntity<TransactionListModel> result = findTransactionsByUser();
        model.addAttribute(MODEL_ATTRIBUTE_TRANSACTIONS, result.getBody().getTransactionEntityList());
        return VIEW_HOME;
    }

    @RequestMapping(value = URL_IMPORT_HOME, method = RequestMethod.POST)
    public String postImportHome(
            @RequestParam(PARAMETER_FILE)
            final
            String file, final Model model)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(file, headers);
        ResponseEntity<Boolean> resultImport = restTemplate.exchange(
                TRANSACTION_SERVICE_URL + IMPORT_TRANSACTIONS,
                HttpMethod.POST,
                entity,
                Boolean.class
        );
        model.addAttribute(MODEL_ATTRIBUTE_ERROR, resultImport.getBody());
        ResponseEntity<TransactionListModel> result = findTransactionsByUser();
        model.addAttribute(MODEL_ATTRIBUTE_TRANSACTIONS, result.getBody().getTransactionEntityList());
        return VIEW_HOME;
    }

    private ResponseEntity<TransactionListModel> findTransactionsByUser()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(userFacade.getUserEntity(), headers);
        return restTemplate.exchange(
                TRANSACTION_SERVICE_URL + FIND_BY_USER,
                HttpMethod.POST,
                entity,
                TransactionListModel.class
        );
    }
}
