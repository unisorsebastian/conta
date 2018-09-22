package ro.jmind.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Entity
@Getter
@Setter
public class BillingAmount implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(precision = 19, scale = 4, columnDefinition = "DECIMAL(19,4)")
    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.ALL)
    private ExchangeRate exchangeRate;

    public BillingAmount() {
    }

    public BillingAmount(Long id, String amount, ExchangeRate exchangeRate) {
        this.id = id;
        this.amount = new BigDecimal(amount).setScale(4, RoundingMode.HALF_EVEN);
        this.exchangeRate = exchangeRate;
    }

    public BillingAmount(String amount, ExchangeRate exchangeRate) {
        this.amount = new BigDecimal(amount).setScale(4, RoundingMode.HALF_EVEN);
        this.exchangeRate = exchangeRate;
    }


    @Override
    public String toString() {
        return "BillingAmount{" +
                "id=" + id +
                ", billingAmount=" + amount +
                ", exchangeRate=" + exchangeRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingAmount that = (BillingAmount) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(exchangeRate, that.exchangeRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, exchangeRate);
    }
}
