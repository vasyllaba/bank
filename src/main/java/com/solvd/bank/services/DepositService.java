package com.solvd.bank.services;

import com.solvd.bank.dao.ICardDAO;
import com.solvd.bank.dao.IClientDAO;
import com.solvd.bank.dao.IDepositDAO;
import com.solvd.bank.dao.IDepositTypeDAO;
import com.solvd.bank.dao.factory.DAOFactory;
import com.solvd.bank.exception.EntityNotFoundException;
import com.solvd.bank.models.CardDetails;
import com.solvd.bank.models.Deposit;
import org.apache.log4j.Logger;

public class DepositService {
    private final IDepositDAO depositDAO = DAOFactory.getFactory().getDepositDAOImpl();
    private final IClientDAO clientDAO = DAOFactory.getFactory().getClientDAOImpl();
    private final IDepositTypeDAO depositTypeDAO = DAOFactory.getFactory().getDepositTypeDAOImpl();
    private final ICardDAO cardDAO = DAOFactory.getFactory().getCardDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(DepositService.class);

    public Deposit getDepositById(long id){
                LOGGER.info("Enter into getDepositById method with id: " + id);

        return depositDAO.getById(id).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(id))
        );
    }

    public boolean updateDeposit(Deposit deposit){
                LOGGER.info("Enter into updateDeposit method with deposit: " + deposit);

        return depositDAO.update(deposit);
    }

    public Deposit createDeposit(Deposit deposit){
                LOGGER.info("Enter into createDeposit method with deposit: " + deposit);

        return depositDAO.create(deposit).orElseThrow(
                () -> new EntityNotFoundException("Failed entity creat")
        );
    }

    public boolean removeDeposit(long id){
                LOGGER.info("Enter into removeDeposit method with id: " + id);

        return depositDAO.remove(id);
    }

    public Deposit getFullDepositById(long id){
                LOGGER.info("Enter into getFullDepositById method with id: " + id);

        Deposit deposit = depositDAO.getById(id).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(id))
        );
        deposit.setOwner(clientDAO.getById(deposit.getClientId()).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(deposit.getClientId())))
        );
        deposit.setDepositType(depositTypeDAO.getById(deposit.getDepositTypeId()).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(deposit.getDepositTypeId())))
        );
        deposit.setCard(cardDAO.getById(deposit.getRegularReplacementCardId()).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(deposit.getRegularReplacementCardId())))
        );
        return deposit;
    }
}
