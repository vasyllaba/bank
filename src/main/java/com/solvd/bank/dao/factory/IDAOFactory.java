package com.solvd.bank.dao.factory;

import com.solvd.bank.dao.*;

public interface IDAOFactory {
    ICardDAO getCardDAOImpl();
    ICardDetailsDAO getCardDetailsDAOImpl();
    IClientDAO getClientDAOImpl();
    ICreditDAO getCreditDAOImpl();
    IDepartmentDAO getDepartmentDAOImpl();
    IDepositDAO getDepositDAOImpl();
    IDepositTypeDAO getDepositTypeDAOImpl();
    IEmployeeDAO getEmployeeDAOImpl();
    IMortgageDAO getMortgageDAOImpl();
    IMortgageTypeDAO getMortgageTypeDAOImpl();
    IPassportDAO getPassportDAOImpl();
}
