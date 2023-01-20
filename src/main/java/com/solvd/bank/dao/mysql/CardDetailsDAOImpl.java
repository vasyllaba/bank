package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.ICardDetailsDAO;
import com.solvd.bank.models.CardDetails;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Optional;

public class CardDetailsDAOImpl extends AbstractMySQLRepo implements ICardDetailsDAO {
    private static final CardDetailsDAOImpl INSTANCE = new CardDetailsDAOImpl();
    private static final String GET_CARD_DETAILS_BY_ID = "SELECT * FROM card_details WHERE id = ?";
    private static final String UPDATE_CARD_DETAILS = "UPDATE card_details SET iban = ? WHERE id = ?";
    private static final String CREATE_CARD_DETAILS = "INSERT INTO card_details (iban) VALUES (?)";
    private static final String REMOVE_CARD_DETAILS = "DELETE FROM card_details WHERE Id=?";

    private static final Logger LOGGER = Logger.getLogger(CardDetailsDAOImpl.class);

    public static CardDetailsDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<CardDetails> getById(long id) {
        LOGGER.info("Enter into getById method with id: " + id);

        final Connection connection = ConnectionPool.getConnection();
        CardDetails cardDetails = new CardDetails();

        try (PreparedStatement ps = connection.prepareStatement(GET_CARD_DETAILS_BY_ID);) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cardDetails.setId(rs.getLong("id"));
                cardDetails.setIban(rs.getString("iban"));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            return Optional.empty();
        }
        if (cardDetails.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(cardDetails);
    }

    @Override
    public boolean update(CardDetails cardDetails) {
        LOGGER.info("Enter into update method with cardDetails: " + cardDetails);

        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CARD_DETAILS)) {
            ps.setString(1, cardDetails.getIban());
            ps.setLong(2, cardDetails.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Optional<CardDetails> create(CardDetails cardDetails) {
        LOGGER.info("Enter into create method with cardDetails: " + cardDetails);

        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(CREATE_CARD_DETAILS, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, cardDetails.getId());
            ps.setString(2, cardDetails.getIban());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                cardDetails.setId(rs.getLong(1));
            }
            rs.close();

        } catch (SQLException e) {
            LOGGER.error(e);
            return Optional.empty();
        }
        if (cardDetails.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(cardDetails);
    }

    @Override
    public boolean remove(long id) {
        LOGGER.info("Enter into remove method with id: " + id);
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_CARD_DETAILS)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }
}
