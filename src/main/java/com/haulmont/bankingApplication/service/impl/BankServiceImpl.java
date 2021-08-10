package com.haulmont.bankingApplication.service.impl;

import com.haulmont.bankingApplication.models.Bank;
import com.haulmont.bankingApplication.repository.BankRepository;
import com.haulmont.bankingApplication.service.CrudEntityService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class BankServiceImpl implements CrudEntityService<Bank> {

    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    @Transactional
    public Bank create(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public List<Bank> getAll() {
        return bankRepository.findAll();
    }

    @Override
    public Bank getByUuid(UUID uuid) {
        return bankRepository.findByUuid(uuid);
    }

    @Override
    @Transactional
    public void delete(Bank bank) {
        bankRepository.delete(bank);
    }
}
