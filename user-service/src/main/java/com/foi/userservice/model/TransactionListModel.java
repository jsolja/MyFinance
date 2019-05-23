package com.foi.userservice.model;

import com.foi.userservice.entity.TransactionEntity;

import java.util.List;

public class TransactionListModel
{
    List<TransactionEntity> transactionEntityList;

    public List<TransactionEntity> getTransactionEntityList()
    {
        return transactionEntityList;
    }

    public void setTransactionEntityList(List<TransactionEntity> transactionEntityList)
    {
        this.transactionEntityList = transactionEntityList;
    }
}
