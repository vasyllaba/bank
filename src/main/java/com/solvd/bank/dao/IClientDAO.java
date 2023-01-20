package com.solvd.bank.dao;

import com.solvd.bank.models.Client;

import java.util.Optional;

public interface IClientDAO extends IBaseDAO<Client> {
    public Optional<Client> findByEmail(String email);
}
