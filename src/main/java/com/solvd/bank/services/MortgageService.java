package com.solvd.bank.services;

import com.solvd.bank.dao.IMortgageDAO;
import com.solvd.bank.dao.factory.DAOFactory;
import com.solvd.bank.exception.EntityNotFoundException;
import com.solvd.bank.models.CardDetails;
import com.solvd.bank.models.Mortgage;
import org.apache.log4j.Logger;

public class MortgageService {
    private final IMortgageDAO mortgageDAO = DAOFactory.getFactory().getMortgageDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(MortgageService.class);

    public Mortgage getMortgageById(long id) {
        LOGGER.info("Enter into getMortgageById method with id: " + id);

        return mortgageDAO.getById(id).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(id))
        );
    }

    public boolean updateMortgage(Mortgage mortgage) {
        LOGGER.info("Enter into updateMortgage method with mortgage: " + mortgage);

        return mortgageDAO.update(mortgage);
    }

    public Mortgage createMortgage(Mortgage mortgage) {
        LOGGER.info("Enter into createMortgage method with mortgage: " + mortgage);

        return mortgageDAO.create(mortgage).orElseThrow(
                () -> new EntityNotFoundException("Failed entity creat")
        );
    }

    public boolean removeMortgage(long id) {
        LOGGER.info("Enter into removeMortgage method with id: " + id);

        return mortgageDAO.remove(id);
    }
}
