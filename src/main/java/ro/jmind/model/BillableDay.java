package ro.jmind.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

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

    public BillableDay() {
    }

    @Override
    public String toString() {
        return "BillableDay{" +
                "date=" + date +
                ", hours=" + hours +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillableDay that = (BillableDay) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(hours, that.hours) &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, hours, rate, description);
    }
}
