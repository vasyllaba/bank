package com.solvd.bank.services;

import com.solvd.bank.dao.IDepositDAO;
import com.solvd.bank.dao.mysql.DepositDAOImpl;
import com.solvd.bank.models.Deposit;

public class DepositService {
    private final IDepositDAO depositDAO = new DepositDAOImpl();

    public Deposit getDepositById(long id){
        return depositDAO.getById(id);
    }

    public boolean updateDeposit(Deposit deposit){
        return depositDAO.update(deposit);
    }

    public Deposit createDeposit(Deposit deposit){
        return depositDAO.create(deposit);
    }

    public boolean removeDeposit(long id){
        return depositDAO.remove(id);
    }
}
