package ro.jmind.model;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class InvoiceDetails {
    private InvoiceNumber invoiceNumber;
    private Document document;

    public InvoiceDetails() {
    }

    public InvoiceNumber getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(InvoiceNumber invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceDetails that = (InvoiceDetails) o;
        return Objects.equals(invoiceNumber, that.invoiceNumber) &&
                Objects.equals(document, that.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceNumber, document);
    }
}
