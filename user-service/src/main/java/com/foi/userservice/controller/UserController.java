package com.foi.userservice.controller;

import com.foi.userservice.facade.UserFacade;
import com.foi.userservice.model.TransactionListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jura Solja <jura.solja@ecx.io> ON 2019-05-21
 */
@RestController
public class UserController
{
    private static final String URL_UPDATE_BALANCE = "/update-balance";

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = URL_UPDATE_BALANCE)
    public boolean updateBalance(
            @RequestBody
                    TransactionListModel transactionListModel)
    {
        return userFacade.updateBalance(transactionListModel);
    }
}
