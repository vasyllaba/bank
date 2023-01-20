package com.solvd.bank.dao.factory;

import com.solvd.bank.dao.*;
import com.solvd.bank.dao.mysql.*;

public class MySqlDAOFactory implements IDAOFactory {
    @Override
    public ICardDAO getCardDAOImpl() {
        return CardDAOImpl.getInstance();
    }

    @Override
    public ICardDetailsDAO getCardDetailsDAOImpl() {
        return CardDetailsDAOImpl.getInstance();
    }

    @Override
    public IClientDAO getClientDAOImpl() {
        return ClientDAOImpl.getInstance();
    }

    @Override
    public ICreditDAO getCreditDAOImpl() {
        return CreditDAOImpl.getInstance();
    }

    @Override
    public IDepartmentDAO getDepartmentDAOImpl() {
        return DepartmentDAOImpl.getInstance();
    }

    @Override
    public IDepositDAO getDepositDAOImpl() {
        return DepositDAOImpl.getInstance();
    }

    @Override
    public IDepositTypeDAO getDepositTypeDAOImpl() {
        return DepositTypeDAOImpl.getInstance();
    }

    @Override
    public IEmployeeDAO getEmployeeDAOImpl() {
        return EmployeeDAOImpl.getInstance();
    }

    @Override
    public IMortgageDAO getMortgageDAOImpl() {
        return MortgageDAOImpl.getInstance();
    }

    @Override
    public IMortgageTypeDAO getMortgageTypeDAOImpl() {
        return MortgageTypeDAOImpl.getInstance();
    }

    @Override
    public IPassportDAO getPassportDAOImpl() {
        return PassportDAOImpl.getInstance();
    }
}
