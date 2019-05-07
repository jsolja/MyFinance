package com.foi.MyFinance.service;

import com.foi.MyFinance.entity.TransactionEntity;
import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.model.TransactionModel;

import java.util.List;

public interface TransactionService
{
    TransactionEntity makeTransaction(TransactionModel transactionModel);

    List<TransactionEntity> findByUser(UserEntity userEntity);
}
