package com.haulmont.bankingApplication.repository;

import com.haulmont.bankingApplication.models.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditOfferRepository extends JpaRepository<CreditOffer, UUID> {
    CreditOffer findByUuid(UUID uuid);
}
