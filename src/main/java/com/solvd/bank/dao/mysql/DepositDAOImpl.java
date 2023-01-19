package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.IDepositDAO;
import com.solvd.bank.enums.PercentDestination;
import com.solvd.bank.models.Deposit;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class DepositDAOImpl extends AbstractMySQLRepo implements IDepositDAO {
    private static final String GET_DEPOSIT_BY_ID = """
            SELECT (id, client_id, deposit_type_id, name, amount, auto_extension, percent_destention,
             regular_replacement, regular_replacement_card_id, term, rate, register_date, end_date)  
             FROM deposits WHERE id = ?
            """;
    private static final String UPDATE_DEPOSIT =
            "UPDATE deposits SET name = ?, amount = ?, auto_extension = ?, end_date = ? WHERE id = ?";
    private static final String CREATE_DEPOSIT = """
            INSERT INTO deposits (client_id, deposit_type_id, name, amount, auto_extension, percent_destention,
             regular_replacement, regular_replacement_card_id, term, rate, register_date, end_date)
             VALUES (?,?,?,?,?,?,?,?,?,?,?,?)
            """;
    private static final String REMOVE_DEPOSIT = "DELETE FROM deposits WHERE Id=?";

    private static final Logger LOGGER = Logger.getLogger(DepositDAOImpl.class);

    @Override
    public Deposit getById(long id) {
        Connection connection = ConnectionPool.getConnection();
        Deposit deposit = new Deposit();

        try(PreparedStatement ps = connection.prepareStatement(GET_DEPOSIT_BY_ID)){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                deposit.setId(rs.getLong(1));
                deposit.setClientId(rs.getLong(2));
                deposit.setDepositTypeId(rs.getLong((3)));
                deposit.setName(rs.getString(4));
                deposit.setAmount(rs.getBigDecimal(5));
                deposit.setAutoExtension(rs.getBoolean(6));
                deposit.setPercentDestination(PercentDestination.valueOf(rs.getString(7)));
                deposit.setRegularReplacement(rs.getBigDecimal(8));
                deposit.setRegularReplacementCardId(rs.getLong(9));
                deposit.setTerm(rs.getInt(10));
                deposit.setRate(rs.getDouble(11));
                deposit.setRegisterDate(rs.getDate(12));
                deposit.setEndDate(rs.getDate(13));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return deposit;
    }

    @Override
    public boolean update(Deposit deposit) {
        final Connection connection = ConnectionPool.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(UPDATE_DEPOSIT)){
            ps.setString(1, deposit.getName());
            ps.setBigDecimal(2, deposit.getAmount());
            ps.setBoolean(3, deposit.getAutoExtension());
            ps.setDate(4, Date.valueOf(deposit.getEndDate().toString()));
            ps.setLong(5, deposit.getId());
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;

    }

    @Override
    public Deposit create(Deposit deposit) {
        Connection connection = ConnectionPool.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(CREATE_DEPOSIT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, deposit.getClientId());
            ps.setLong(2, deposit.getDepositTypeId());
            ps.setString(3, deposit.getName());
            ps.setBigDecimal(4, deposit.getAmount());
            ps.setBoolean(5, deposit.getAutoExtension());
            ps.setString(6, deposit.getPercentDestination().getDestination());
            ps.setBigDecimal(7, deposit.getRegularReplacement());
            ps.setLong(8, deposit.getRegularReplacementCardId());
            ps.setInt(9, deposit.getTerm());
            ps.setDouble(10, deposit.getRate());
            ps.setDate(11, Date.valueOf(deposit.getRegisterDate().toString()));
            ps.setDate(12, Date.valueOf(deposit.getEndDate().toString()));
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) deposit.setId(rs.getLong(1));
            rs.close();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return deposit;
    }

    @Override
    public boolean remove(long id) {
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_DEPOSIT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }
}
