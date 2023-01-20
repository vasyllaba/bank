package com.solvd.bank.services;

import com.solvd.bank.dao.IPassportDAO;
import com.solvd.bank.dao.factory.DAOFactory;
import com.solvd.bank.exception.EntityNotFoundException;
import com.solvd.bank.models.CardDetails;
import com.solvd.bank.models.Passport;
import org.apache.log4j.Logger;

public class PassportService {
    private final IPassportDAO passportDAO = DAOFactory.getFactory().getPassportDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(PassportService.class);


    public Passport getPassportById(long id) {
        LOGGER.info("Enter into getPassportById method with id: " + id);
        return passportDAO.getById(id).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(id))
        );
    }

    public boolean updatePassport(Passport passport) {
        LOGGER.info("Enter into updatePassport method with passport: " + passport);
        return passportDAO.update(passport);
    }

    public Passport createPassport(Passport passport) {
        LOGGER.info("Enter into createPassport method with passport: " + passport);
        return passportDAO.create(passport).orElseThrow(
                () -> new EntityNotFoundException("Failed entity creat")
        );
    }

    public boolean removePassport(long id) {
        LOGGER.info("Enter into removePassport method with id: " + id);
        return passportDAO.remove(id);
    }
}
