package com.solvd.bank.services;

import com.solvd.bank.dao.IMortgageDAO;
import com.solvd.bank.dao.mysql.MortgageDAOImpl;
import com.solvd.bank.models.Mortgage;

public class MortgageService {
    private final IMortgageDAO mortgageDAO = new MortgageDAOImpl();

    public Mortgage getMortgageById(long id){
        return mortgageDAO.getById(id);
    }

    public boolean updateMortgage(Mortgage mortgage){
        return mortgageDAO.update(mortgage);
    }

    public Mortgage createMortgage(Mortgage mortgage){
        return mortgageDAO.create(mortgage);
    }

    public boolean removeMortgage(long id){
        return mortgageDAO.remove(id);
    }
}
