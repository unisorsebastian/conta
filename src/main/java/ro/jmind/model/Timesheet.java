package ro.jmind.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<BillableDay> getBillableDays() {
        Comparator<BillableDay> bdc = (o1, o2) -> o1.getDate().compareTo(o2.getDate());
        return new TreeSet<>(billableDays);
    }

    public void setBillableDays(Set<BillableDay> billableDays) {
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
