package ro.jmind.repo;

import org.springframework.stereotype.Repository;
import ro.jmind.model.ExchangeRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

@Repository
public interface ExchangeRateRepositoryCustom {
    ExchangeRate save(ExchangeRate exchangeRate);

    List<ExchangeRate> findAllByCurrencyAndLocalCurrencyAndAndParityAndExchangeDate(Currency currency,
                                                                                    Currency localCurrency,
                                                                                    BigDecimal parity,
                                                                                    LocalDate exchangeDate);
}
