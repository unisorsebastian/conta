package ro.jmind.model;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class DocumentNumber implements Serializable {
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_number_generator")
//    @SequenceGenerator(name = "document_number_generator", sequenceName = "document_number_seq", allocationSize = 50)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "document_number_generator")
//    @TableGenerator(name="document_number_generator", table="id_generator", schema="jmind_dev")
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
