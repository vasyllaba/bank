package com.solvd.bank.services;

import com.solvd.bank.dao.ICardDAO;
import com.solvd.bank.dao.IClientDAO;
import com.solvd.bank.dao.IDepositDAO;
import com.solvd.bank.dao.IDepositTypeDAO;
import com.solvd.bank.dao.mysql.CardDAOImpl;
import com.solvd.bank.dao.mysql.ClientDAOImpl;
import com.solvd.bank.dao.mysql.DepositDAOImpl;
import com.solvd.bank.dao.mysql.DepositTypeDAOImpl;
import com.solvd.bank.models.Deposit;
import org.apache.log4j.Logger;

public class DepositService {
    private final IDepositDAO depositDAO = new DepositDAOImpl();
    private final IClientDAO clientDAO = new ClientDAOImpl();
    private final IDepositTypeDAO depositTypeDAO = new DepositTypeDAOImpl();
    private final ICardDAO cardDAO = new CardDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(ClientService.class);

    public Deposit getDepositById(long id){
                LOGGER.info("Enter into getDepositById method with id: " + id);

        return depositDAO.getById(id);
    }

    public boolean updateDeposit(Deposit deposit){
                LOGGER.info("Enter into updateDeposit method with deposit: " + deposit);

        return depositDAO.update(deposit);
    }

    public Deposit createDeposit(Deposit deposit){
                LOGGER.info("Enter into createDeposit method with deposit: " + deposit);

        return depositDAO.create(deposit);
    }

    public boolean removeDeposit(long id){
                LOGGER.info("Enter into removeDeposit method with id: " + id);

        return depositDAO.remove(id);
    }

    public Deposit getFullDepositById(long id){
                LOGGER.info("Enter into getFullDepositById method with id: " + id);

        Deposit deposit = depositDAO.getById(id);
        deposit.setOwner(clientDAO.getById(deposit.getClientId()));
        deposit.setDepositType(depositTypeDAO.getById(deposit.getDepositTypeId()));
        deposit.setCard(cardDAO.getById(deposit.getRegularReplacementCardId()));
        return deposit;
    }
}
