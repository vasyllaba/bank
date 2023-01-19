package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.ICreditDAO;
import com.solvd.bank.models.Credit;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class CreditDAOImpl extends AbstractMySQLRepo implements ICreditDAO {

    private static final String GET_CREDIT_BY_ID = "SELECT * FROM credits WHERE id = ?";
    private static final String UPDATE_CREDIT =
            "UPDATE credits SET client_id = ?, card_id =?, amount=?, term=?, interest_rate=?, register_date=?, end_date=? WHERE id = ?";
    private static final String CRATE_CREDIT = "INSERT INTO credits (client_id, card_id, amount, term, interest_rate, register_date, end_date) VALUES (?,?,?,?,?,?,?)";
    private static final String REMOVE_CREDIT = "DELETE FROM credits WHERE Id=?";

    private static final Logger LOGGER = Logger.getLogger(CreditDAOImpl.class);

    @Override
    public Credit getById(long id) {
        final Connection connection = ConnectionPool.getConnection();
        Credit cardDetails = new Credit();

        try (PreparedStatement ps = connection.prepareStatement(GET_CREDIT_BY_ID);) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cardDetails.setId(rs.getLong("id"));
                cardDetails.setClientId(rs.getLong("client_id"));
                cardDetails.setCardId(rs.getLong("card_id"));
                cardDetails.setAmount(rs.getBigDecimal("amount"));
                cardDetails.setTerm(rs.getInt("term"));
                cardDetails.setInterestRate(rs.getDouble("interest_rate"));
                cardDetails.setRegisterDate(rs.getDate("register_date"));
                cardDetails.setEndDate(rs.getDate("end_date"));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return cardDetails;
    }

    @Override
    public boolean update(Credit cardDetails) {
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CREDIT)) {
            ps.setLong(1, cardDetails.getClientId());
            ps.setLong(2, cardDetails.getCardId());
            ps.setBigDecimal(3, cardDetails.getAmount());
            ps.setInt(4, cardDetails.getTerm());
            ps.setDouble(5, cardDetails.getInterestRate());
            ps.setDate(6, Date.valueOf(cardDetails.getRegisterDate().toString()));
            ps.setDate(7, Date.valueOf(cardDetails.getEndDate().toString()));
            ps.setLong(8, cardDetails.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Credit create(Credit cardDetails) {
        final Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CRATE_CREDIT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, cardDetails.getClientId());
            ps.setLong(2, cardDetails.getCardId());
            ps.setBigDecimal(3, cardDetails.getAmount());
            ps.setInt(4, cardDetails.getTerm());
            ps.setDouble(5, cardDetails.getInterestRate());
            ps.setDate(6, Date.valueOf(cardDetails.getRegisterDate().toString()));
            ps.setDate(7, Date.valueOf(cardDetails.getEndDate().toString()));
            ps.execute();

            ResultSet key = ps.getGeneratedKeys();
            if (key.next()) {
                cardDetails.setId(key.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return cardDetails;
    }

    @Override
    public boolean remove(long id) {
        final Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_CREDIT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }
}

