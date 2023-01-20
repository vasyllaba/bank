package com.solvd.bank.services;

import com.solvd.bank.dao.IDepositTypeDAO;
import com.solvd.bank.dao.factory.DAOFactory;
import com.solvd.bank.exception.EntityNotFoundException;
import com.solvd.bank.models.CardDetails;
import com.solvd.bank.models.DepositType;
import org.apache.log4j.Logger;

public class DepositTypeService {
    private final IDepositTypeDAO depositTypeDAO = DAOFactory.getFactory().getDepositTypeDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(DepositTypeService.class);

    public DepositType getDepositTypeById(long id) {
        LOGGER.info("Enter into getDepositTypeById method with id: " + id);

        return depositTypeDAO.getById(id).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(id))
        );
    }

    public boolean updateDepositType(DepositType depositType) {
        LOGGER.info("Enter into updateDepositType method with depositType: " + depositType);

        return depositTypeDAO.update(depositType);
    }

    public DepositType createDepositType(DepositType depositType) {
        LOGGER.info("Enter into createDepositType method with depositType: " + depositType);

        return depositTypeDAO.create(depositType).orElseThrow(
                () -> new EntityNotFoundException("Failed entity creat")
        );
    }

    public boolean removeDepositType(long id) {
        LOGGER.info("Enter into removeDepositType method with id: " + id);

        return depositTypeDAO.remove(id);
    }
}
