package ro.jmind.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Timesheet {
    @Id
    private LocalDate date;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BillableDay> billableDays;

    public Timesheet() {
    }

    public Timesheet(LocalDate date, Set<BillableDay> billableDays) {
        this.date = date;
        this.billableDays = billableDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timesheet timesheet = (Timesheet) o;
        return Objects.equals(date, timesheet.date) &&
                Objects.equals(billableDays, timesheet.billableDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, billableDays);
    }
}
