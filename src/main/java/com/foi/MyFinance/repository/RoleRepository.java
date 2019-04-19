package com.foi.MyFinance.repository;

import com.foi.MyFinance.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>
{
    Optional<RoleEntity> findByRole(String role);
}
