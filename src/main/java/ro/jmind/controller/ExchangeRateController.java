package ro.jmind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.jmind.model.ExchangeRate;
import ro.jmind.repo.ExchangeRateRepository;
import ro.jmind.repo.ExchangeRateRepositoryCustom;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(path = "/ExchangeRate")
public class ExchangeRateController {
    private ExchangeRateRepository exchangeRateRepository;
    private ExchangeRateRepositoryCustom exchangeRateRepositoryCustom;

    public ExchangeRateController(ExchangeRateRepository exchangeRateRepository, ExchangeRateRepositoryCustom exchangeRateRepositoryCustom) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateRepositoryCustom = exchangeRateRepositoryCustom;
    }

    @GetMapping(path = "")
    public @ResponseBody
    Iterable<ExchangeRate> getExchangeRate() {
        return exchangeRateRepository.findAll();
    }

    @GetMapping(path = "/exchangeDate/{exchangeDateAsString}")
    public @ResponseBody
    Iterable<ExchangeRate> getExchangeRate(@PathVariable String exchangeDateAsString) {
        LocalDate exchangeDate = LocalDate.parse(exchangeDateAsString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return exchangeRateRepository.findAllByExchangeDate(exchangeDate);
    }


    @PostMapping(path = "")
    public @ResponseBody
    ExchangeRate addExchangeRate(@RequestBody ExchangeRate exchangeRate) {
        return exchangeRateRepositoryCustom.save(exchangeRate);
    }

}
