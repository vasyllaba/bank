package com.solvd.bank.services;

import com.solvd.bank.dao.IMortgageTypeDAO;
import com.solvd.bank.dao.mysql.MortgageTypeDAOImpl;
import com.solvd.bank.models.MortgageType;
import org.apache.log4j.Logger;

public class MortgageTypeService {
    private final IMortgageTypeDAO mortgageTypeDAO = new MortgageTypeDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(MortgageTypeService.class);

    public MortgageType getMortgageTypeById(long id) {
        LOGGER.info("Enter into getMortgageTypeById method with id: " + id);

        return mortgageTypeDAO.getById(id);
    }

    public boolean updateMortgageType(MortgageType mortgageType) {
        LOGGER.info("Enter into updateMortgageType method with mortgageType: " + mortgageType);

        return mortgageTypeDAO.update(mortgageType);
    }

    public MortgageType createMortgageType(MortgageType mortgageType) {
        LOGGER.info("Enter into createMortgageType method with mortgageType: " + mortgageType);

        return mortgageTypeDAO.create(mortgageType);
    }

    public boolean removeMortgageType(long id) {
        LOGGER.info("Enter into removeMortgageType method with id: " + id);

        return mortgageTypeDAO.remove(id);
    }
}
