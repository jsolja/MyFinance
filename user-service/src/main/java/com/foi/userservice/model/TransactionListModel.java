package com.foi.userservice.model;

import com.foi.userservice.entity.TransactionEntity;

import java.util.List;

/**
 * @author Jura Solja <jura.solja@ecx.io> ON 2019-05-21
 */
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
