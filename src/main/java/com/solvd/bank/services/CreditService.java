package com.solvd.bank.services;

import com.solvd.bank.dao.ICreditDAO;
import com.solvd.bank.dao.mysql.CreditDAOImpl;
import com.solvd.bank.models.Credit;

public class CreditService {
    private final ICreditDAO creditDAO = new CreditDAOImpl();

    public Credit getCreditById(long id){
        return creditDAO.getById(id);
    }

    public boolean updateCredit(Credit credit){
        return creditDAO.update(credit);
    }

    public Credit createCredit(Credit credit){
        return creditDAO.create(credit);
    }

    public boolean removeCredit(long id){
        return creditDAO.remove(id);
    }
}
