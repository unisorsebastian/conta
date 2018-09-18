package ro.jmind.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_generator")
    @SequenceGenerator(name = "invoice_generator", sequenceName = "invoice_seq", allocationSize = 50)
    private Long id;
    private String description;
    private LocalDate date;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoiceNoFK")
    @JsonManagedReference
    private InvoiceNumber invoiceNumber;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "amountFK")
    private BillingAmount billingAmount;

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", invoiceNumber=" + invoiceNumber +
                ", billingAmount=" + billingAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
