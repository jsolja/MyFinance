package com.foi.transactionservice.service.impl;

import com.foi.transactionservice.entity.TransactionEntity;
import com.foi.transactionservice.entity.UserEntity;
import com.foi.transactionservice.model.TransactionListModel;
import com.foi.transactionservice.model.TransactionModel;
import com.foi.transactionservice.repository.TransactionRepository;
import com.foi.transactionservice.repository.UserRepository;
import com.foi.transactionservice.service.TransactionService;
import com.opencsv.CSVReader;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private static final String UPDATE_BALANCE_URL = "/update-balance";
    private static final String USER_SERVICE = "http://user-service";

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public boolean importCsvTransactions(final String csvFilePath)
    {
        List<TransactionEntity> transactionEntityList = new ArrayList<>();
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
                transactionEntityList.add(newTransaction);
            }
        }
        catch (final Exception ex)
        {
            Logger.getLogger(TransactionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateBalanceList(transactionEntityList);
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
        newTransaction.setUserEntity(userRepository
                .findByUsername(transactionModel.getUserEntity().getUsername())
                .get());
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

    private boolean updateBalanceList(List<TransactionEntity> transactionEntityList)
    {
        TransactionListModel transactionListModel = new TransactionListModel();
        transactionListModel.setTransactionEntityList(transactionEntityList);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(transactionListModel, headers);
        ResponseEntity<Boolean> result = restTemplate.exchange(
                USER_SERVICE + UPDATE_BALANCE_URL,
                HttpMethod.POST,
                entity,
                Boolean.class
        );
        return result.getBody();
    }

    private void updateBalance(TransactionEntity transactionEntity)
    {
        UserEntity userEntity = transactionEntity.getUserEntity();
        if (StringUtils.equals(transactionEntity.getType(), INCOME))
        {
            userEntity.setBalance((float) (userEntity.getBalance() + transactionEntity.getAmount()));
        }
        if (StringUtils.equals(transactionEntity.getType(), EXPENSE))
        {
            userEntity.setBalance((float) (userEntity.getBalance() - transactionEntity.getAmount()));
        }
        userRepository.save(userEntity);
    }
}
