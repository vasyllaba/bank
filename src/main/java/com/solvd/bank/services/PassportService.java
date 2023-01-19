package com.solvd.bank.services;

import com.solvd.bank.dao.IPassportDAO;
import com.solvd.bank.dao.mysql.PassportDAOImpl;
import com.solvd.bank.models.Passport;

public class PassportService {
    private final IPassportDAO passportDAO = new PassportDAOImpl();

    public Passport getPassportById(long id){
        return passportDAO.getById(id);
    }

    public boolean updatePassport(Passport passport){
        return passportDAO.update(passport);
    }

    public Passport createPassport(Passport passport){
        return passportDAO.create(passport);
    }

    public boolean removePassport(long id){
        return passportDAO.remove(id);
    }
}
