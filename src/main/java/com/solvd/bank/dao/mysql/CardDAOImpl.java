package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.ICardDAO;
import com.solvd.bank.enums.CardType;
import com.solvd.bank.enums.Currency;
import com.solvd.bank.models.Card;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Optional;

public class CardDAOImpl extends AbstractMySQLRepo implements ICardDAO {

    private static final CardDAOImpl INSTANCE = new CardDAOImpl();

    private static final String GET_CARD_BY_ID = "SELECT * FROM cards WHERE id = ?";
    private static final String UPDATE_CARD = "UPDATE cards SET count = ?, credit_limit = ?, pin = ? WHERE id = ?";
    private static final String CRATE_CARD = """
            INSERT INTO cards  (client_id, card_details_id, card_type, card_currency ,card_number, expire_date, 
            cvv, count, credit_limit, pin) 
            VALUES (?,?,?,?,?,?,?,?,?,?)
            """;
    private static final String REMOVE_CARD = "DELETE FROM cards WHERE Id=?";

    private static final Logger LOGGER = Logger.getLogger(CardDAOImpl.class);

    public static CardDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Card> getById(long id) {
        LOGGER.info("Enter into getById method with id: " + id);

        final Connection connection = ConnectionPool.getConnection();
        Card card = new Card();

        try (PreparedStatement ps = connection.prepareStatement(GET_CARD_BY_ID);) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                card.setId(rs.getLong("id"));
                card.setClientId(rs.getLong("client_id"));
                card.setCardDetailsId(rs.getLong("card_details_id"));
                card.setCardType(CardType.valueOf(rs.getString("card_type")));
                card.setCardCurrency(Currency.valueOf(rs.getString("card_currency")));
                card.setCardNumber(rs.getString("card_number"));
                card.setExpireDate(rs.getDate("expire_date"));
                card.setCvv(rs.getString("cvv"));
                card.setMoneyCount(rs.getBigDecimal("count"));
                card.setCreditLimit(rs.getBigDecimal("credit_limit"));
                card.setPin(rs.getInt("pin"));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            return Optional.empty();
        }
        if (card.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(card);
    }

    @Override
    public boolean update(Card card) {
        LOGGER.info("Enter into update method with card: " + card);

        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CARD)) {
            ps.setBigDecimal(1, card.getMoneyCount());
            ps.setBigDecimal(2, card.getCreditLimit());
            ps.setInt(3, card.getPin());
            ps.setLong(4, card.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);

            return false;
        }
        return true;
    }

    @Override
    public Optional<Card> create(Card card) {
        LOGGER.info("Enter into create method with card: " + card);

        final Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CRATE_CARD, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, card.getClientId());
            ps.setLong(2, card.getCardDetailsId());
            ps.setString(3, card.getCardType().getCardType());
            ps.setString(4, card.getCardCurrency().getCurrency());
            ps.setString(5, card.getCardNumber());
            ps.setDate(6, Date.valueOf(card.getExpireDate().toString()));
            ps.setString(7, card.getCvv());
            ps.setBigDecimal(8, card.getMoneyCount());
            ps.setBigDecimal(9, card.getCreditLimit());
            ps.setInt(10, card.getPin());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                card.setId(rs.getLong(1));
            }
            rs.close();

        } catch (SQLException e) {
            LOGGER.error(e);
            return Optional.empty();

        }
        if (card.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(card);

    }

    @Override
    public boolean remove(long id) {
        LOGGER.info("Enter into remove method with id: " + id);

        final Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_CARD)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }
}
