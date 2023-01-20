package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.IMortgageDAO;
import com.solvd.bank.models.Mortgage;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MortgageDAOImpl extends AbstractMySQLRepo implements IMortgageDAO {

    private static final String GET_MORTGAGE_BY_ID =
            """
                SELECT morgage_type_id, client_id, card_id, amount, term, rate, register_date, end_date
                FROM mortgages WHERE id = ?
            """;
    private static final String UPDATE_MORTGAGE =
            "UPDATE mortgages SET term=?, amount=?, card_id=?, end_date=? WHERE id = ?";
    private static final String CREATE_MORTGAGE =
            """
                INSERT INTO mortgages (morgage_type_id, client_id, card_id, amount, term, rate, register_date, end_date)
                VALUES (?,?,?,?,?,?,?,?)
            """;
    private static final String REMOVE_MORTGAGE = "DELETE FROM mortgages WHERE Id=?";

    private static final Logger LOGGER = Logger.getLogger(MortgageDAOImpl.class);

    @Override
    public Optional<Mortgage> getById(long id) {
        LOGGER.info("Enter into getById method with id: " + id);

        final Connection connection = ConnectionPool.getConnection();
        Mortgage mortgage = new Mortgage();

        try (PreparedStatement ps = connection.prepareStatement(GET_MORTGAGE_BY_ID);) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mortgage.setId(id);
                mortgage.setMortgageTypeId(rs.getLong("morgage_type_id"));
                mortgage.setClientId(rs.getLong("client_id"));
                mortgage.setCardId(rs.getLong("card_id"));
                mortgage.setAmount(rs.getBigDecimal("amount"));
                mortgage.setTerm(rs.getInt("term"));
                mortgage.setRate(rs.getDouble("rate"));
                mortgage.setRegisterDate(rs.getDate("register_date"));
                mortgage.setEndDate(rs.getDate("end_date"));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (mortgage.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(mortgage);
    }


    @Override
    public List<Mortgage> getByCardId(long id) {
        LOGGER.info("Enter into getByCardId method with id: " + id);

        final Connection connection = ConnectionPool.getConnection();
        List<Mortgage> mortgages = new LinkedList<>();

        try (PreparedStatement ps = connection.prepareStatement(GET_MORTGAGE_BY_ID);) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mortgage mortgage = new Mortgage();
                mortgage.setId(id);
                mortgage.setMortgageTypeId(rs.getLong("morgage_type_id"));
                mortgage.setClientId(rs.getLong("client_id"));
                mortgage.setCardId(rs.getLong("card_id"));
                mortgage.setAmount(rs.getBigDecimal("amount"));
                mortgage.setTerm(rs.getInt("term"));
                mortgage.setRate(rs.getDouble("rate"));
                mortgage.setRegisterDate(rs.getDate("register_date"));
                mortgage.setEndDate(rs.getDate("end_date"));
                mortgages.add(mortgage);
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return mortgages;
    }

    @Override
    public boolean update(Mortgage mortgage) {
        LOGGER.info("Enter into update method with mortgage: " + mortgage);
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_MORTGAGE)) {
            ps.setInt(1, mortgage.getTerm());
            ps.setBigDecimal(2, mortgage.getAmount());
            ps.setLong(3, mortgage.getCardId());
            ps.setDate(4, Date.valueOf(mortgage.getEndDate().toString()));
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Optional<Mortgage> create(Mortgage mortgage) {
        LOGGER.info("Enter into create method with mortgage: " + mortgage);
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(CREATE_MORTGAGE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, mortgage.getMortgageTypeId());
            ps.setLong(2, mortgage.getClientId());
            ps.setLong(3, mortgage.getCardId());
            ps.setBigDecimal(4, mortgage.getAmount());
            ps.setInt(5, mortgage.getTerm());
            ps.setDouble(6, mortgage.getRate());
            ps.setDate(7, Date.valueOf(mortgage.getRegisterDate().toString()));
            ps.setDate(8, Date.valueOf(mortgage.getEndDate().toString()));
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                mortgage.setId(rs.getLong(1));
            }
            rs.close();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (mortgage.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(mortgage);
    }

    @Override
    public boolean remove(long id) {
        LOGGER.info("Enter into remove method with id: " + id);
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_MORTGAGE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }
}
