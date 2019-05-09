package com.foi.MyFinance.facade.impl;

import com.foi.MyFinance.entity.TransactionEntity;
import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.facade.TransactionFacade;
import com.foi.MyFinance.model.TransactionModel;
import com.foi.MyFinance.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionFacadeImpl implements TransactionFacade
{
    @Autowired
    private TransactionService transactionService;

    @Override
    public boolean importCsvTransactions(final String csvFilePath)
    {
        return transactionService.importCsvTransactions(csvFilePath);
    }

    @Override
    public TransactionEntity makeTransaction(final TransactionModel transactionModel)
    {
        return transactionService.makeTransaction(transactionModel);
    }

    @Override
    public List<TransactionEntity> findByUser(final UserEntity userEntity)
    {
        return transactionService.findByUser(userEntity);
    }

    @Override
    public List<TransactionEntity> findByUserAndChosenMonth(final UserEntity userEntity, final String date)
    {
        return transactionService.findByUserAndChosenMonth(userEntity, date);
    }

    @Override
    public List<TransactionEntity> findByUserAndChosenYear(final UserEntity userEntity, final String year)
    {
        return transactionService.findByUserAndChosenYear(userEntity, year);
    }

}
