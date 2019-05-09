package com.foi.MyFinance.service.impl;

import com.foi.MyFinance.entity.TransactionEntity;
import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.model.TransactionModel;
import com.foi.MyFinance.repository.TransactionRepository;
import com.foi.MyFinance.repository.UserRepository;
import com.foi.MyFinance.service.TransactionService;
import com.foi.MyFinance.service.UserService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TransactionServiceImpl implements TransactionService
{
    private static final String INCOME = "income";
    private static final String EXPENSE = "expense";
    private static final String yyyy_MM_dd = "yyyy-MM-dd";
    private static final String FIRST_DAY_OF_MONTH = "-01";
    private static final String FIRST_DAY_OF_YEAR = "-01-01";
    private static final String LAST_DAY_OF_YEAR = "-12-31";

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public boolean importCsvTransactions(final String csvFilePath)
    {
        final String filePath = new File(csvFilePath).getAbsolutePath();
        try (
                final CSVReader csvReader = new CSVReader(new FileReader(filePath))
        )
        {
            String[] data;
            while ((data = csvReader.readNext()) != null)
            {
                final TransactionEntity newTransaction = new TransactionEntity();
                newTransaction.setFromUser(data[0]);
                newTransaction.setToUser(data[1]);
                newTransaction.setDetails(data[2]);
                newTransaction.setAmount(Integer.parseInt(data[3]));
                newTransaction.setType(data[4]);
                newTransaction.setDate(java.sql.Date.valueOf(data[5]));
                final Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(data[6]);
                optionalUserEntity.ifPresent(newTransaction::setUserEntity);
                if (!ObjectUtils.isEmpty(transactionRepository.save(newTransaction)))
                {
                    return false;
                }
            }
        }
        catch (final Exception ex)
        {
            Logger.getLogger(TransactionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

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

    @Override
    public List<TransactionEntity> findByUserAndChosenYear(final UserEntity userEntity, final String year)
    {
        return transactionRepository.findByUserAndDateBetween(
                userEntity,
                java.sql.Date.valueOf(year + FIRST_DAY_OF_YEAR),
                java.sql.Date.valueOf(year + LAST_DAY_OF_YEAR)
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
