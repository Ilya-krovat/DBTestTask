package DB;

import model.CurrencyPairModel;
import model.ExchangeRateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DBQuery {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBQuery(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getExchangeRate(int currencyPairId, String rateDate) {
        ExchangeRateModel response = jdbcTemplate.query(
                "SELECT * FROM currency_pair " +
                    "WHERE currency_pair_id=? " +
                    "AND rate_date=?",
                new Object[]{currencyPairId, rateDate},
                new BeanPropertyRowMapper<>(ExchangeRateModel.class)).stream().findFirst().orElse(null);
        return response == null ? null : response.getRate_value();
    }

    public String getCurrentExchangeRate(int currencyPairId) {
        ExchangeRateModel response = jdbcTemplate.query(
                "SELECT * FROM currency_pair " +
                    "WHERE currency_pair_id=? " +
                    "order by rate_date DESC",
                new Object[]{currencyPairId},
                new BeanPropertyRowMapper<>(ExchangeRateModel.class)).stream().findFirst().orElse(null);
        return response == null ? null : response.getRate_value();
    }

    public List<CurrencyPairModel> getCurrencyPairs() {
        return jdbcTemplate.query(
                "SELECT * FROM currency_pair",
                new BeanPropertyRowMapper<>(CurrencyPairModel.class));
    }

    public void addCurrencyPair(String firstCur, String secondCur) {
        jdbcTemplate.update(
                "INSERT INTO currency_pair(base_charcode,quoted_charcode,description) VALUES (?, ?, ?)",
                firstCur, secondCur,
                "Обмен " + firstCur + " на " + secondCur);
    }

    public void insertExchangeRate(String rateDate,String rateValue, int currencyPairId){
        jdbcTemplate.update(
                "INSERT INTO exchange_rate VALUES (1,?,?,?)",
                rateDate, rateValue, currencyPairId);
    }
}
