package com.haulmont.bankingApplication.service.impl;

import com.haulmont.bankingApplication.models.Client;
import com.haulmont.bankingApplication.repository.ClientRepository;
import com.haulmont.bankingApplication.service.CrudEntityService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements CrudEntityService<Client> {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getByUuid(UUID uuid) {
        return clientRepository.findByUuid(uuid);
    }

    @Override
    @Transactional
    public void delete(Client client) {
        clientRepository.delete(client);
    }
}
