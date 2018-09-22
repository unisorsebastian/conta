package ro.jmind.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class BillableDay {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private BigDecimal hours;
    private BigDecimal rate;
    private String description;
    @Version
    private Long version;

    private BillableDay() {
    }

    public BillableDay(LocalDate date, String hours, String rate, String description) {
        this.date = date;
        this.hours = new BigDecimal(hours).setScale(4, RoundingMode.HALF_EVEN);
        this.rate = new BigDecimal(rate).setScale(4, RoundingMode.HALF_EVEN);
        this.description = description;
    }
}
