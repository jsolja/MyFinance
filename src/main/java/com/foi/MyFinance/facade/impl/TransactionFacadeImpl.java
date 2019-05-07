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
    public TransactionEntity makeTransaction(final TransactionModel transactionModel)
    {
        return transactionService.makeTransaction(transactionModel);
    }

    @Override
    public List<TransactionEntity> findByUser(final UserEntity userEntity)
    {
        return transactionService.findByUser(userEntity);
    }
}
