package com.foi.MyFinance.repository;

import com.foi.MyFinance.entity.TransactionEntity;
import com.foi.MyFinance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>
{
    List<TransactionEntity> findByUser(UserEntity userEntity);
}
