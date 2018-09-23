package ro.jmind.repo;

import org.springframework.stereotype.Repository;
import ro.jmind.model.ExchangeRate;

@Repository
public interface ExchangeRateRepositoryCustom {
    ExchangeRate save(ExchangeRate exchangeRate);

}