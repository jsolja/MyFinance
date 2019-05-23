package com.foi.transactionservice.controller;

import com.foi.transactionservice.entity.TransactionEntity;
import com.foi.transactionservice.entity.UserEntity;
import com.foi.transactionservice.facade.TransactionFacade;
import com.foi.transactionservice.model.TransactionListModel;
import com.foi.transactionservice.model.TransactionModel;
import com.foi.transactionservice.model.UserWithDateModel;
import com.foi.transactionservice.model.UserWithYearModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jura Solja <jura.solja@ecx.io> ON 2019-05-21
 */
@RestController
public class TransactionController
{
    private static final String MAKE_TRANSACTION = "/make-transaction";
    private static final String FIND_BY_USER = "/find-by-user";
    private static final String FIND_BY_USER_AND_CHOSEN_MONTH = "/find-by-user-and-chosen-month";
    private static final String FIND_BY_USER_AND_CHOSEN_YEAR = "/find-by-user-and-chosen-year";
    private static final String IMPORT_TRANSACTION = "/import-transactions";

    @Autowired
    private TransactionFacade transactionFacade;

    @RequestMapping(value = MAKE_TRANSACTION)
    public TransactionEntity makeTransaction(
            @RequestBody
                    TransactionModel transactionModel)
    {
        return transactionFacade.makeTransaction(transactionModel);
    }

    @RequestMapping(value = FIND_BY_USER)
    public TransactionListModel findByUser(
            @RequestBody
                    UserEntity userEntity)
    {
        TransactionListModel transactionListModel = new TransactionListModel();
        transactionListModel.setTransactionEntityList(transactionFacade.findByUser(userEntity));
        return transactionListModel;
    }

    @RequestMapping(value = FIND_BY_USER_AND_CHOSEN_MONTH)
    public TransactionListModel findByUserAndChosenMonth(
            @RequestBody
                    String request)
    {
        Gson gson = new GsonBuilder().create();
        UserWithDateModel userWithDateModel = gson.fromJson(request, UserWithDateModel.class);
        TransactionListModel transactionListModel = new TransactionListModel();
        transactionListModel.setTransactionEntityList(transactionFacade.findByUserAndChosenMonth(
                userWithDateModel.getUserEntity(),
                userWithDateModel.getDate()
        ));
        return transactionListModel;
    }

    @RequestMapping(value = FIND_BY_USER_AND_CHOSEN_YEAR)
    public TransactionListModel findByUserAndChosenYear(
            @RequestBody
                    String request)
    {
        Gson gson = new GsonBuilder().create();
        UserWithYearModel userWithDateModel = gson.fromJson(request, UserWithYearModel.class);
        TransactionListModel transactionListModel = new TransactionListModel();
        transactionListModel.setTransactionEntityList(transactionFacade.findByUserAndChosenYear(
                userWithDateModel.getUserEntity(),
                userWithDateModel.getYear()
        ));
        return transactionListModel;
    }

    @RequestMapping(value = IMPORT_TRANSACTION)
    public boolean importCsvTransactions(
            @RequestBody
                    String csvFilePath)
    {
        return transactionFacade.importCsvTransactions(csvFilePath);
    }
}
