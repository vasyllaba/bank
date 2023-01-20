package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.IMortgageTypeDAO;
import com.solvd.bank.enums.Currency;
import com.solvd.bank.models.MortgageType;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Optional;

public class MortgageTypeDAOImpl implements IMortgageTypeDAO {
    private static final MortgageTypeDAOImpl INSTANCE = new MortgageTypeDAOImpl();
    private static final String GET_MORTGAGE_TYPE_BY_ID =
            """
                SELECT id, max_amount, max_term, min_term, rate, currency
                FROM mortgage_types WHERE id = ?
            """;
    private static final String UPDATE_MORTGAGE_TYPE =
            "UPDATE mortgage_types SET max_amount = ? WHERE id = ?";
    private static final String CREATE_MORTGAGE_TYPE =
            """
                INSERT INTO mortgage_types (max_amount, max_term, min_term, rate, currency)
                VALUES (?,?,?,?,?,?)
            """;
    private static final String REMOVE_MORTGAGE_TYPE = "DELETE FROM mortgage_types WHERE Id=?";

    private static final Logger LOGGER = Logger.getLogger(MortgageTypeDAOImpl.class);

    public static MortgageTypeDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<MortgageType> getById(long id) {
        LOGGER.info("Enter into getById method with id: " + id);

        final Connection connection = ConnectionPool.getConnection();
        MortgageType mortgageType = new MortgageType();

        try (PreparedStatement ps = connection.prepareStatement(GET_MORTGAGE_TYPE_BY_ID);) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mortgageType.setId(rs.getLong("id"));
                mortgageType.setMaxAmount(rs.getBigDecimal("max_amount"));
                mortgageType.setMaxTerm(rs.getInt("max_term"));
                mortgageType.setMinTerm(rs.getInt("min_term"));
                mortgageType.setRate(rs.getDouble("rate"));
                mortgageType.setCurrency(Currency.valueOf(rs.getString("currency")));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (mortgageType.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(mortgageType);
    }

    @Override
    public boolean update(MortgageType mortgageType) {
        LOGGER.info("Enter into update method with mortgageType: " + mortgageType);
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_MORTGAGE_TYPE)) {
            ps.setBigDecimal(1, mortgageType.getMaxAmount());
            ps.setLong(2, mortgageType.getId());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Optional<MortgageType> create(MortgageType mortgageType) {
        LOGGER.info("Enter into create method with mortgageType: " + mortgageType);
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(CREATE_MORTGAGE_TYPE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setBigDecimal(1, mortgageType.getMaxAmount());
            ps.setInt(2, mortgageType.getMaxTerm());
            ps.setInt(3, mortgageType.getMinTerm());
            ps.setDouble(4, mortgageType.getRate());
            ps.setString(5, mortgageType.getCurrency().getCurrency());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                mortgageType.setId(rs.getLong(1));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (mortgageType.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(mortgageType);
    }

    @Override
    public boolean remove(long id) {
        LOGGER.info("Enter into remove method with id: " + id);
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_MORTGAGE_TYPE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }
}