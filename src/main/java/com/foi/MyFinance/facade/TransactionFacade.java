package com.foi.MyFinance.facade;

import com.foi.MyFinance.entity.TransactionEntity;
import com.foi.MyFinance.model.TransactionModel;

public interface TransactionFacade
{
    TransactionEntity makeTransaction(TransactionModel transactionModel);
}
