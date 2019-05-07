package com.foi.MyFinance.service.impl;

import com.foi.MyFinance.entity.TransactionEntity;
import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.model.TransactionModel;
import com.foi.MyFinance.repository.TransactionRepository;
import com.foi.MyFinance.repository.UserRepository;
import com.foi.MyFinance.service.TransactionService;
import com.foi.MyFinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService
{
    private static final String INCOME = "income";
    private static final String EXPENSE = "expense";
    private static final String yyyy_MM_dd = "yyyy-MM-dd";
    private static final String FIRST_DAY_OF_MONTH = "-01";

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public TransactionEntity makeTransaction(final TransactionModel transactionModel)
    {
        final TransactionEntity newTransaction = new TransactionEntity();
        newTransaction.setAmount(transactionModel.getAmount());
        newTransaction.setDate(transactionModel.getDate());
        newTransaction.setDetails(transactionModel.getDetails());
        newTransaction.setFromUser(transactionModel.getFromUser());
        newTransaction.setToUser(transactionModel.getToUser());
        newTransaction.setType(transactionModel.getType());
        final Optional<UserEntity> userEntity = userRepository.findByUsername(userService
                .getUserEntity()
                .getUsername());
        userEntity.ifPresent(newTransaction::setUserEntity);
        updateBalance(newTransaction);
        return transactionRepository.save(newTransaction);
    }

    @Override
    public List<TransactionEntity> findByUser(final UserEntity userEntity)
    {
        return transactionRepository.findByUser(userEntity);
    }

    @Override
    public List<TransactionEntity> findByUserAndChosenMonth(final UserEntity userEntity, final String beginDate)
    {
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(yyyy_MM_dd);
        final LocalDate localDate = LocalDate.parse(beginDate + FIRST_DAY_OF_MONTH, dateFormat);
        final LocalDate endDate = localDate.withDayOfMonth(localDate.lengthOfMonth());
        return transactionRepository.findByUserAndDateBetween(
                userEntity,
                java.sql.Date.valueOf(beginDate + FIRST_DAY_OF_MONTH),
                java.sql.Date.valueOf(endDate)
        );
    }

    private void updateBalance(final TransactionEntity transactionEntity)
    {
        final UserEntity userEntity = userRepository
                .findByUsername(userService.getUserEntity().getUsername())
                .get();
        final double currentBalance = userEntity.getBalance();
        if (transactionEntity.getType().equals(INCOME))
        {
            userEntity.setBalance((float) (currentBalance + transactionEntity.getAmount()));
        }
        else
        {
            userEntity.setBalance((float) (currentBalance - transactionEntity.getAmount()));
        }
        userRepository.save(userEntity);
    }
}
