package ro.jmind.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

@Entity
public class BillingAmount {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal amount;
    private BigDecimal localAmount;
    private Currency currency;
    private Currency localCurrency;
    private BigDecimal parity;
    private LocalDate exchangeDate;

    public BillingAmount() {
    }

    public BillingAmount(String amount, String parity, String exchangeDate) {
        this.amount = new BigDecimal(amount);
        this.parity = new BigDecimal(parity);
        this.exchangeDate = LocalDate.parse(exchangeDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.localAmount = this.amount.multiply(this.parity);
        this.localCurrency = Currency.getInstance("RON");
        this.currency = Currency.getInstance("EUR");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(BigDecimal localAmount) {
        this.localAmount = localAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getLocalCurrency() {
        return localCurrency;
    }

    public void setLocalCurrency(Currency localCurrency) {
        this.localCurrency = localCurrency;
    }

    public BigDecimal getParity() {
        return parity;
    }

    public void setParity(BigDecimal parity) {
        this.parity = parity;
    }

    public LocalDate getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(LocalDate exchangeDate) {
        this.exchangeDate = exchangeDate;
    }
}
