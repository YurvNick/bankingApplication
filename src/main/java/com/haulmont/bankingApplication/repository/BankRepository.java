package com.haulmont.bankingApplication.repository;

import com.haulmont.bankingApplication.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {
    Bank findByUuid(UUID uuid);
}
