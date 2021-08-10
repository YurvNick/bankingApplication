package com.haulmont.bankingApplication.service.impl;

import com.haulmont.bankingApplication.models.Credit;
import com.haulmont.bankingApplication.repository.CreditRepository;
import com.haulmont.bankingApplication.service.CrudEntityService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CreditServiceImpl implements CrudEntityService<Credit> {
    private final CreditRepository creditRepository;

    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }
    @Override
    @Transactional
    public Credit create(Credit credit) {
        return creditRepository.save(credit);
    }

    @Override
    public List<Credit> getAll() {
        return creditRepository.findAll();
    }

    @Override
    public Credit getByUuid(UUID uuid) {
        return creditRepository.findByUuid(uuid);
    }

    @Override
    @Transactional
    public void delete(Credit credit) {
        creditRepository.delete(credit);
    }
}
