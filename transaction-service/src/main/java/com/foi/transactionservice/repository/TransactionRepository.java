package com.foi.transactionservice.repository;

import com.foi.transactionservice.entity.TransactionEntity;
import com.foi.transactionservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>
{
    List<TransactionEntity> findByUser(UserEntity userEntity);

    List<TransactionEntity> findByUserAndDateBetween(UserEntity userEntity, Date beginDate, Date endDate);
}
