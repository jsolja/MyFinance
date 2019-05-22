package com.foi.transactionservice.controller;

import com.foi.transactionservice.entity.TransactionEntity;
import com.foi.transactionservice.entity.UserEntity;
import com.foi.transactionservice.facade.TransactionFacade;
import com.foi.transactionservice.model.TransactionListModel;
import com.foi.transactionservice.model.TransactionModel;
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
    @Autowired
    private TransactionFacade transactionFacade;

    @RequestMapping(value = "/make-transaction")
    public TransactionEntity makeTransaction(
            @RequestBody
                    TransactionModel transactionModel)
    {
        return transactionFacade.makeTransaction(transactionModel);
    }

    @RequestMapping(value = "/find-by-user")
    public TransactionListModel findByUser(
            @RequestBody
                    UserEntity userEntity)
    {
        TransactionListModel transactionListModel = new TransactionListModel();
        transactionListModel.setTransactionEntityList(transactionFacade.findByUser(userEntity));
        return transactionListModel;
    }

    @RequestMapping(value = "/find-by-user-and-chosen-month")
    public TransactionListModel findByUserAndChosenMonth(
            @RequestBody
                    UserEntity userEntity, String date)
    {
        TransactionListModel transactionListModel = new TransactionListModel();
        transactionListModel.setTransactionEntityList(transactionFacade.findByUserAndChosenMonth(userEntity, date));
        return transactionListModel;
    }

    @RequestMapping(value = "/find-by-user-and-chosen-year")
    public TransactionListModel findByUserAndChosenYear(
            @RequestBody
                    UserEntity userEntity, String year)
    {
        TransactionListModel transactionListModel = new TransactionListModel();
        transactionListModel.setTransactionEntityList(transactionFacade.findByUserAndChosenYear(userEntity, year));
        return transactionListModel;
    }

    @RequestMapping(value = "/import-transactions")
    public boolean importCsvTransactions(
            @RequestBody
                    String csvFilePath)
    {
        return transactionFacade.importCsvTransactions(csvFilePath);
    }
}
