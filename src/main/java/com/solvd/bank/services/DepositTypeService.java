package com.solvd.bank.services;

import com.solvd.bank.dao.IDepositTypeDAO;
import com.solvd.bank.dao.mysql.DepositTypeDAOImpl;
import com.solvd.bank.models.DepositType;

public class DepositTypeService {
    private final IDepositTypeDAO depositTypeDAO = new DepositTypeDAOImpl();

    public DepositType getDepositTypeById(long id){
        return depositTypeDAO.getById(id);
    }

    public boolean updateDepositType(DepositType depositType){
        return depositTypeDAO.update(depositType);
    }

    public DepositType createDepositType(DepositType depositType){
        return depositTypeDAO.create(depositType);
    }

    public boolean removeDepositType(long id){
        return depositTypeDAO.remove(id);
    }
}
