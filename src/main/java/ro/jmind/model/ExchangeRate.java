package ro.jmind.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Objects;


@Entity
@Getter
@Setter
public class ExchangeRate implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Currency currency;
    private Currency localCurrency;
    @Column(precision = 19, scale = 4, columnDefinition = "DECIMAL(19,4)")
    private BigDecimal parity;
    private LocalDate exchangeDate;

    public ExchangeRate() {
    }

    public ExchangeRate(Long id, Currency currency, Currency localCurrency, BigDecimal parity, LocalDate exchangeDate) {
        this.id = id;
        this.currency = currency;
        this.localCurrency = localCurrency;
        this.parity = parity;
        this.exchangeDate = exchangeDate;
    }

    public ExchangeRate(Currency currency, Currency localCurrency, BigDecimal parity, LocalDate exchangeDate) {
        this.currency = currency;
        this.localCurrency = localCurrency;
        this.parity = parity;
        this.exchangeDate = exchangeDate;
    }

    public ExchangeRate(String currency, String localCurrency, String parity, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        this.currency = Currency.getInstance(currency);
        this.localCurrency = Currency.getInstance(localCurrency);
        this.parity = new BigDecimal(parity);
        this.exchangeDate = localDate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", currency=" + currency +
                ", localCurrency=" + localCurrency +
                ", parity=" + parity +
                ", exchangeDate=" + exchangeDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate that = (ExchangeRate) o;
        return Objects.equals(currency, that.currency) &&
                Objects.equals(localCurrency, that.localCurrency) &&
                Objects.equals(parity, that.parity) &&
                Objects.equals(exchangeDate, that.exchangeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, localCurrency, parity, exchangeDate);
    }
}
