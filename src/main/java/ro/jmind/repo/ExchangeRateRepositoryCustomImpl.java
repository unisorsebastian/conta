package ro.jmind.repo;


import org.springframework.stereotype.Repository;
import ro.jmind.model.ExchangeRate;

import javax.transaction.Transactional;
import java.util.List;

@Repository("exchangeRateRepositoryCustomImpl")
public class ExchangeRateRepositoryCustomImpl implements ExchangeRateRepositoryCustom {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateRepositoryCustomImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    @Transactional
    public ExchangeRate save(ExchangeRate exchangeRate) {
        if (exchangeRate == null) {
            throw new RuntimeException("exchangeRate is null");
        }
        List<ExchangeRate> allByExchangeRate = exchangeRateRepository
                .findAllByCurrencyAndLocalCurrencyAndAndParityAndExchangeDate(exchangeRate.getCurrency(),
                        exchangeRate.getLocalCurrency(),
                        exchangeRate.getParity(),
                        exchangeRate.getExchangeDate());

        return null;
    }

//    @Override
//    public Invoice save(Invoice invoice) {
//        if (invoice == null) {
//            throw new RuntimeException("invoice is null");
//        }
//        InvoiceNumber invoiceNumber = invoice.getInvoiceNumber();
//        if (invoice.getInvoiceNumber() == null) {
//            invoiceNumber = getNextInvoiceNumber();
//            invoice.setInvoiceNumber(invoiceNumber);
//        }
//        invoice = invoiceRepository.save(invoice);
//        invoiceNumber.setInvoice(invoice);
//
//        return invoice;
//    }
//
//    private InvoiceNumber getNextInvoiceNumber() {
//        InvoiceNumber invoiceNumber = invoiceNumberRepository.findAllByOrderByNumberDesc()
//                .stream()
//                .findFirst()
//                .orElse(null);
//        if (invoiceNumber == null) {
//            invoiceNumber = new InvoiceNumber(1l, series);
//        } else {
//            invoiceNumber = new InvoiceNumber(invoiceNumber.getNumber() + 1L, series);
//        }
//        invoiceNumber = invoiceNumberRepository.save(invoiceNumber);
//        return invoiceNumber;
//    }


}
