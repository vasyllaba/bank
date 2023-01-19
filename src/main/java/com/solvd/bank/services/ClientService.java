package com.solvd.bank.services;

import com.solvd.bank.dao.IClientDAO;
import com.solvd.bank.dao.mysql.ClientDAOImpl;
import com.solvd.bank.models.Client;

public class ClientService {
    private IClientDAO clientDAO = new ClientDAOImpl();

    public Client getClientById(long id){
        return clientDAO.getById(id);
    }

    public boolean updateClient(Client client){
        return clientDAO.update(client);
    }

    public Client createClient(Client client){
        return clientDAO.create(client);
    }

    public boolean removeClient(long id){
        return clientDAO.remove(id);
    }
}
