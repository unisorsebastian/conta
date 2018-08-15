package ro.jmind.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.jmind.model.Document;
import ro.jmind.model.InvoiceNumber;

import javax.transaction.Transactional;

@Repository("documentRepositoryCustomImpl")
public class DocumentRepositoryCustomImpl implements DocumentRepositoryCustom {
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    InvoiceNumberRepository invoiceNumberRepository;

    @Override
    @Transactional
    public Document save(Document document) {
        Document savedDocument = documentRepository.save(document);

        String series = "JMD";

        InvoiceNumber invoiceNumber = invoiceNumberRepository.findAllByOrderByNumberDesc()
                .stream()
                .findFirst()
                .orElse(null);
        if (invoiceNumber == null) {
            invoiceNumber = new InvoiceNumber(1l, series);
        } else {
            invoiceNumber = new InvoiceNumber(invoiceNumber.getNumber() + 1L, series);
        }
        invoiceNumber.setDocument(savedDocument);
        invoiceNumberRepository.save(invoiceNumber);

        return savedDocument;
    }
}
