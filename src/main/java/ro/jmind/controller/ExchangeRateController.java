package ro.jmind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.jmind.model.ExchangeRate;
import ro.jmind.repo.ExchangeRateRepository;
import ro.jmind.repo.ExchangeRateRepositoryCustom;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

@Controller
@RequestMapping(path = "/ExchangeRate")
public class ExchangeRateController {
    private ExchangeRateRepository exchangeRateRepository;
    private ExchangeRateRepositoryCustom exchangeRateRepositoryCustom;

    public ExchangeRateController(ExchangeRateRepository exchangeRateRepository, ExchangeRateRepositoryCustom exchangeRateRepositoryCustom) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateRepositoryCustom = exchangeRateRepositoryCustom;
    }

    @GetMapping(path = "/new")
    public @ResponseBody
    ExchangeRate newExchangeRate() {
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ExchangeRate exchangeRate = new ExchangeRate(Currency.getInstance("EUR"), Currency.getInstance("RON"), "4.6543", LocalDate.now());
        exchangeRateRepository.save(exchangeRate);
        exchangeRateRepositoryCustom.save(exchangeRate);
        return exchangeRate;
    }


//    @GetMapping(path = "/all")
//    public @ResponseBody
//    Iterable<ExchangeRate> getAllInvoice() {
//        return exchangeRateRepository.findAllByExchangeDateDesc();
//    }


}
