package ro.jmind.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.jmind.model.ExchangeRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long> {
    Iterable<ExchangeRate> findAll();

    Iterable<ExchangeRate> findAllByCurrencyAndLocalCurrencyAndParityAndExchangeDate(Currency currency, Currency localCurrency, BigDecimal parity, LocalDate exchangeDate);

    ExchangeRate findFirstByCurrencyAndLocalCurrencyAndParityAndExchangeDate(Currency currency, Currency localCurrency, BigDecimal parity, LocalDate exchangeDate);

    Iterable<ExchangeRate> findAllByExchangeDate(LocalDate exchangeDate);
}
