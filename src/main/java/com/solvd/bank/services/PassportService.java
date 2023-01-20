package com.solvd.bank.services;

import com.solvd.bank.dao.IPassportDAO;
import com.solvd.bank.dao.mysql.PassportDAOImpl;
import com.solvd.bank.models.Passport;
import org.apache.log4j.Logger;

public class PassportService {
    private final IPassportDAO passportDAO = new PassportDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(ClientService.class);


    public Passport getPassportById(long id) {
        LOGGER.info("Enter into getPassportById method with id: " + id);
        return passportDAO.getById(id);
    }

    public boolean updatePassport(Passport passport) {
        LOGGER.info("Enter into updatePassport method with passport: " + passport);
        return passportDAO.update(passport);
    }

    public Passport createPassport(Passport passport) {
        LOGGER.info("Enter into createPassport method with passport: " + passport);
        return passportDAO.create(passport);
    }

    public boolean removePassport(long id) {
        LOGGER.info("Enter into removePassport method with id: " + id);
        return passportDAO.remove(id);
    }
}
