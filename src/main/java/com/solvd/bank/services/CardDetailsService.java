package com.solvd.bank.services;

import com.solvd.bank.dao.ICardDetailsDAO;
import com.solvd.bank.dao.mysql.CardDetailsDAOImpl;
import com.solvd.bank.models.CardDetails;
import org.apache.log4j.Logger;

public class CardDetailsService {
    private final ICardDetailsDAO cardDetailsDAO = new CardDetailsDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(CardDetailsService.class);

    public CardDetails getCardDetailsById(long id){
        LOGGER.info("Enter into getCardDetailsById method with id: " + id);
        return cardDetailsDAO.getById(id);
    }

    public boolean updateCardDetails(CardDetails cardDetails){
        LOGGER.info("Enter into updateCardDetails method with cardDetails: " + cardDetails);
        return cardDetailsDAO.update(cardDetails);
    }

    public CardDetails createCardDetails(CardDetails cardDetails){
        LOGGER.info("Enter into createCardDetails method with cardDetails: " + cardDetails);
        return cardDetailsDAO.create(cardDetails);
    }

    public boolean removeCardDetails(long id){
        LOGGER.info("Enter into removeCardDetails method with id: " + id);
        return cardDetailsDAO.remove(id);
    }
}
