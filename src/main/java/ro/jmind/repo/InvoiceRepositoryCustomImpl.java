package ro.jmind.repo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ro.jmind.model.Invoice;
import ro.jmind.model.InvoiceNumber;

import javax.transaction.Transactional;

@Repository("documentRepositoryCustomImpl")
public class InvoiceRepositoryCustomImpl implements InvoiceRepositoryCustom {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceNumberRepository invoiceNumberRepository;

    @Value("${core.document.default.series}")
    private String series;

    public InvoiceRepositoryCustomImpl(InvoiceRepository invoiceRepository, InvoiceNumberRepository invoiceNumberRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceNumberRepository = invoiceNumberRepository;
    }

    @Override
    @Transactional
    public Invoice save(Invoice invoice) {
        if (invoice == null) {
            throw new RuntimeException("invoice is null");
        }
        InvoiceNumber invoiceNumber = invoice.getInvoiceNumber();
        if (invoice.getInvoiceNumber() == null) {
            invoiceNumber = getNextInvoiceNumber();
            invoice.setInvoiceNumber(invoiceNumber);
        }
        invoice = invoiceRepository.save(invoice);
        invoiceNumber.setInvoice(invoice);

        return invoice;
    }

    private InvoiceNumber getNextInvoiceNumber() {
        InvoiceNumber invoiceNumber = invoiceNumberRepository.findAllByOrderByNumberDesc()
                .stream()
                .findFirst()
                .orElse(null);
        if (invoiceNumber == null) {
            invoiceNumber = new InvoiceNumber(1l, series);
        } else {
            invoiceNumber = new InvoiceNumber(invoiceNumber.getNumber() + 1L, series);
        }
        invoiceNumber = invoiceNumberRepository.save(invoiceNumber);
        return invoiceNumber;
    }
}
