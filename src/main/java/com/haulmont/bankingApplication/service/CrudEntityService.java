package com.haulmont.bankingApplication.service;

import java.util.List;
import java.util.UUID;

public interface CrudEntityService<T>
{
    T create(T obj);
    List<T> getAll();
    T getByUuid(UUID uuid);
    void delete(T obj);
}
