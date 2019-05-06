package com.foi.MyFinance.service;

import com.foi.MyFinance.entity.TransactionEntity;
import com.foi.MyFinance.model.TransactionModel;

public interface TransactionService
{
    TransactionEntity makeTransaction(TransactionModel transactionModel);
}
