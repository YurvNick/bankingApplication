package com.haulmont.bankingApplication.repository;

import com.haulmont.bankingApplication.models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditRepository extends JpaRepository<Credit, UUID> {
    Credit findByUuid(UUID uuid);
}