package ro.jmind.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
public class InvoiceNumber implements Serializable {
    @Id
    private Long number;
    @NotNull
    private String series;
    @OneToOne
    @JoinColumn(name = "invoiceFK")
    @JsonBackReference
    private Invoice invoice;


    public InvoiceNumber() {
    }

    public InvoiceNumber(Long number, @NotNull String series) {
        this.number = number;
        this.series = series;
    }

    @Override
    public String toString() {
        return "InvoiceNumber{" +
                "number=" + number +
                ", series='" + series + '\'' +
                ", invoice=" + invoice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceNumber that = (InvoiceNumber) o;
        return Objects.equals(number, that.number) &&
                Objects.equals(series, that.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, series);
    }

}
