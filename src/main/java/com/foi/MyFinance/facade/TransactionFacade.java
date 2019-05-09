package com.foi.MyFinance.facade;

import com.foi.MyFinance.entity.TransactionEntity;
import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.model.TransactionModel;

import java.util.List;

public interface TransactionFacade
{
    boolean importCsvTransactions(String csvFilePath);

    TransactionEntity makeTransaction(TransactionModel transactionModel);

    List<TransactionEntity> findByUser(UserEntity userEntity);

    List<TransactionEntity> findByUserAndChosenMonth(UserEntity userEntity, String date);

    List<TransactionEntity> findByUserAndChosenYear(UserEntity userEntity, String year);
}
