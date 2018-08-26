package ro.jmind.repo;


import org.springframework.stereotype.Component;
import ro.jmind.model.Invoice;

@Component
public interface InvoiceRepositoryCustom {
    Invoice save(Invoice invoice);
}
