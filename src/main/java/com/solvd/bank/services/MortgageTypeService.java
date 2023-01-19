package com.solvd.bank.services;

import com.solvd.bank.dao.IMortgageTypeDAO;
import com.solvd.bank.dao.mysql.MortgageTypeDAOImpl;
import com.solvd.bank.models.MortgageType;

public class MortgageTypeService {
    private final IMortgageTypeDAO mortgageTypeDAO = new MortgageTypeDAOImpl();

    public MortgageType getMortgageTypeById(long id){
        return mortgageTypeDAO.getById(id);
    }

    public boolean updateMortgageType(MortgageType mortgageType){
        return mortgageTypeDAO.update(mortgageType);
    }

    public MortgageType createMortgageType(MortgageType mortgageType){
        return mortgageTypeDAO.create(mortgageType);
    }

    public boolean removeMortgageType(long id){
        return mortgageTypeDAO.remove(id);
    }
}
