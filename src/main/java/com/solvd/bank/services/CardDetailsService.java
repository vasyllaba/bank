package com.solvd.bank.services;

import com.solvd.bank.dao.ICardDetailsDAO;
import com.solvd.bank.dao.factory.DAOFactory;
import com.solvd.bank.exception.EntityNotFoundException;
import com.solvd.bank.models.CardDetails;
import org.apache.log4j.Logger;

public class CardDetailsService {
    private final ICardDetailsDAO cardDetailsDAO = DAOFactory.getFactory().getCardDetailsDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(CardDetailsService.class);

    public CardDetails getCardDetailsById(long id){
        LOGGER.info("Enter into getCardDetailsById method with id: " + id);
        return cardDetailsDAO.getById(id).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(id))
        );
    }

    public boolean updateCardDetails(CardDetails cardDetails){
        LOGGER.info("Enter into updateCardDetails method with cardDetails: " + cardDetails);
        return cardDetailsDAO.update(cardDetails);
    }

    public CardDetails createCardDetails(CardDetails cardDetails){
        LOGGER.info("Enter into createCardDetails method with cardDetails: " + cardDetails);
        return cardDetailsDAO.create(cardDetails).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "cardDetails", cardDetails.toString())
        );
    }

    public boolean removeCardDetails(long id){
        LOGGER.info("Enter into removeCardDetails method with id: " + id);
        return cardDetailsDAO.remove(id);
    }
}
