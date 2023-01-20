package com.solvd.bank.services;

import com.solvd.bank.dao.ICreditDAO;
import com.solvd.bank.dao.factory.DAOFactory;
import com.solvd.bank.exception.EntityNotFoundException;
import com.solvd.bank.models.CardDetails;
import com.solvd.bank.models.Credit;
import org.apache.log4j.Logger;

import java.util.List;

public class CreditService {
    private final ICreditDAO creditDAO = DAOFactory.getFactory().getCreditDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(CreditService.class);

    public List<Credit> getCreditsByClientId(long id){
        LOGGER.info("Enter into getCreditsByClientId method with id: " + id);

        return creditDAO.getCreditsByClientId(id);
    }

    public Credit getCreditById(long id){
        LOGGER.info("Enter into getCreditById method with id: " + id);

        return creditDAO.getById(id).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(id))
        );
    }

    public boolean updateCredit(Credit credit){
        LOGGER.info("Enter into updateCredit method with credit: " + credit);

        return creditDAO.update(credit);
    }

    public Credit createCredit(Credit credit){
        LOGGER.info("Enter into createCredit method with credit: " + credit);

        return creditDAO.create(credit).orElseThrow(
                () -> new EntityNotFoundException("Failed entity creat")
        );
    }

    public boolean removeCredit(long id){
        LOGGER.info("Enter into removeCredit method with id: " + id);

        return creditDAO.remove(id);
    }
}
