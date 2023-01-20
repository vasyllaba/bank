package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.ICreditDAO;
import com.solvd.bank.models.Credit;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CreditDAOImpl extends AbstractMySQLRepo implements ICreditDAO {
    private static final CreditDAOImpl INSTANCE = new CreditDAOImpl();
    private static final String GET_CREDIT_BY_ID =
            "SELECT id, client_id, card_id, amount, term, interest_rate, register_date, end_date FROM credits WHERE id = ?";
    private static final String GET_CREDIT_BY_CARD_ID =
            "SELECT id, client_id, card_id, amount, term, interest_rate, register_date, end_date FROM credits WHERE card_id = ?";
    private static final String GET_CREDIT_BY_CLIENT_ID =
            "SELECT id, client_id, card_id, amount, term, interest_rate, register_date, end_date FROM credits WHERE client_id = ?";
    private static final String UPDATE_CREDIT =
            "UPDATE credits SET client_id = ?, card_id =?, amount=?, term=?, interest_rate=?, register_date=?, end_date=? WHERE id = ?";
    private static final String CRATE_CREDIT =
            "INSERT INTO credits (client_id, card_id, amount, term, interest_rate, register_date, end_date) VALUES (?,?,?,?,?,?,?)";
    private static final String REMOVE_CREDIT = "DELETE FROM credits WHERE Id=?";

    private static final Logger LOGGER = Logger.getLogger(CreditDAOImpl.class);

    public static CreditDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Credit> getById(long id) {
        LOGGER.info("Enter into getById method with id: " + id);

        final Connection connection = ConnectionPool.getConnection();
        Credit credit = new Credit();

        try (PreparedStatement ps = connection.prepareStatement(GET_CREDIT_BY_ID);) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                credit.setId(rs.getLong("id"));
                credit.setClientId(rs.getLong("client_id"));
                credit.setCardId(rs.getLong("card_id"));
                credit.setAmount(rs.getBigDecimal("amount"));
                credit.setTerm(rs.getInt("term"));
                credit.setInterestRate(rs.getDouble("interest_rate"));
                credit.setRegisterDate(rs.getDate("register_date"));
                credit.setEndDate(rs.getDate("end_date"));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            return Optional.empty();
        }
        if (credit.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(credit);
    }


    @Override
    public List<Credit> getCreditsByCardId(long id) {
        LOGGER.info("Enter into getCreditsByCardId method with id: " + id);
        return getByAnyId(id, GET_CREDIT_BY_CLIENT_ID);
    }

    @Override
    public List<Credit> getCreditsByClientId(long id) {
        LOGGER.info("Enter into getCreditsByClientId method with id: " + id);
        return getByAnyId(id, GET_CREDIT_BY_CARD_ID);
    }

    private List<Credit> getByAnyId(long id, String query){
        LOGGER.info("Enter into getByAnyId method with id: " + id);

        final Connection connection = ConnectionPool.getConnection();
        List<Credit> credits = new LinkedList<>();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Credit credit = new Credit();
                credit.setId(rs.getLong("id"));
                credit.setClientId(rs.getLong("client_id"));
                credit.setCardId(rs.getLong("card_id"));
                credit.setAmount(rs.getBigDecimal("amount"));
                credit.setTerm(rs.getInt("term"));
                credit.setInterestRate(rs.getDouble("interest_rate"));
                credit.setRegisterDate(rs.getDate("register_date"));
                credit.setEndDate(rs.getDate("end_date"));
                credits.add(credit);
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return credits;
    }


    @Override
    public boolean update(Credit credit) {
        LOGGER.info("Enter into update method with credit: " + credit);
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CREDIT)) {
            ps.setLong(1, credit.getClientId());
            ps.setLong(2, credit.getCardId());
            ps.setBigDecimal(3, credit.getAmount());
            ps.setInt(4, credit.getTerm());
            ps.setDouble(5, credit.getInterestRate());
            ps.setDate(6, Date.valueOf(credit.getRegisterDate().toString()));
            ps.setDate(7, Date.valueOf(credit.getEndDate().toString()));
            ps.setLong(8, credit.getId());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Optional<Credit> create(Credit credit) {
        LOGGER.info("Enter into create method with credit: " + credit);

        final Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CRATE_CREDIT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, credit.getClientId());
            ps.setLong(2, credit.getCardId());
            ps.setBigDecimal(3, credit.getAmount());
            ps.setInt(4, credit.getTerm());
            ps.setDouble(5, credit.getInterestRate());
            ps.setDate(6, Date.valueOf(credit.getRegisterDate().toString()));
            ps.setDate(7, Date.valueOf(credit.getEndDate().toString()));
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                credit.setId(rs.getLong(1));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            return Optional.empty();
        }
        if (credit.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(credit);
    }

    @Override
    public boolean remove(long id) {
        LOGGER.info("Enter into remove method with id: " + id);
        final Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_CREDIT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }
}

