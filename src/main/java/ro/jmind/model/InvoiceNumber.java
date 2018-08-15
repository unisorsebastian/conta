package ro.jmind.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class InvoiceNumber implements Serializable{
    @Id
    private Long number;

    @NotNull
    private String series;

    @OneToOne
    private Document document;

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

    public Long getNumber() {
        return number;
    }

    public String getSeries() {
        return series;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
