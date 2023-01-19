package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.ICardDetailsDAO;
import com.solvd.bank.models.CardDetails;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class CardDetailsDAOImpl extends AbstractMySQLRepo implements ICardDetailsDAO {
    private static final String GET_CARD_DETAILS_BY_ID = "SELECT * FROM card_details WHERE id = ?";
    private static final String UPDATE_CARD_DETAILS = "UPDATE card_details SET iban = ? WHERE id = ?";
    private static final String CREATE_CARD_DETAILS = "INSERT INTO card_details (iban) VALUES (?)";
    private static final String REMOVE_CARD_DETAILS = "DELETE FROM card_details WHERE Id=?";

    private static final Logger LOGGER = Logger.getLogger(CardDetailsDAOImpl.class);

    @Override
    public CardDetails getById(long id) {
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
        }
        return cardDetails;
    }

    @Override
    public boolean update(CardDetails cardDetails) {
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
    String s =
    """
                INSERT INTO deposits_types (id, name, is_replenishment, max_replenishment, payment_per_time, min_term,
                    max_term, no_early_terminate_percent_rate, early_terminate_percent_rate, currency)
                VALUES (?,?,?,?,?,?,?,?,?,?)
            """;

    @Override
    public CardDetails create(CardDetails cardDetails) {
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
        }
        return cardDetails;
    }

    @Override
    public boolean remove(long id) {
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
