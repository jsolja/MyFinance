package com.foi.transactionservice.service;

import com.foi.transactionservice.entity.TransactionEntity;
import com.foi.transactionservice.entity.UserEntity;
import com.foi.transactionservice.model.TransactionModel;

import java.util.List;

public interface TransactionService
{
    boolean importCsvTransactions(String csvFilePath);

    TransactionEntity makeTransaction(TransactionModel transactionModel);

    List<TransactionEntity> findByUser(UserEntity userEntity);

    List<TransactionEntity> findByUserAndChosenMonth(UserEntity userEntity, String date);

    List<TransactionEntity> findByUserAndChosenYear(UserEntity userEntity, String year);
}
