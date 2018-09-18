package ro.jmind.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.jmind.model.ExchangeRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long> {
//    List<ExchangeRate> findAllByExchangeDateDesc();

    List<ExchangeRate> findAll();

    List<ExchangeRate> findAllByCurrencyAndLocalCurrencyAndAndParityAndExchangeDate(Currency currency, Currency localCurrency, BigDecimal parity, LocalDate exchangeDate);
}
