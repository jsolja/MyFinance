package com.foi.MyFinance.facade.impl;

import com.foi.MyFinance.entity.TransactionEntity;
import com.foi.MyFinance.facade.TransactionFacade;
import com.foi.MyFinance.model.TransactionModel;
import com.foi.MyFinance.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionFacadeImpl implements TransactionFacade
{
    @Autowired
    private TransactionService transactionService;

    @Override
    public TransactionEntity makeTransaction(final TransactionModel transactionModel)
    {
        return transactionService.makeTransaction(transactionModel);
    }
}
