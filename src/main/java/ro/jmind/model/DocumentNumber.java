package ro.jmind.model;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class DocumentNumber implements Serializable{
//    @SequenceGenerator(name="SEQUENCE_GENERATOR",sequenceName="SEQ_DOC")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long number;

    @NotNull
    private String series;

    @OneToOne
    private Document document;

    public DocumentNumber() {
    }

    public DocumentNumber(Long number, @NotNull String series) {
        this.number = number;
        this.series = series;
    }

    @Override
    public String toString() {
        return "DocumentNumber{" +
                "number=" + number +
                ", series='" + series + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentNumber that = (DocumentNumber) o;
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

}
