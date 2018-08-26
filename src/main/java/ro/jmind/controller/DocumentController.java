package ro.jmind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.jmind.model.Document;
import ro.jmind.model.InvoiceDetails;
import ro.jmind.model.InvoiceNumber;
import ro.jmind.repo.DocumentRepositoryCustom;
import ro.jmind.repo.InvoiceDetailsRepository;
import ro.jmind.repo.InvoiceNumberRepository;
import ro.jmind.repo.DocumentRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(path = "/doc")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentRepositoryCustom documentRepositoryCustom;
    @Autowired
    private InvoiceNumberRepository invoiceNumberRepository;
    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;

    @Value("${document.default.series}")
    private String series;


    @GetMapping(path = "/next")
    public @ResponseBody
    InvoiceNumber newDocId() {
        InvoiceNumber newDoc = invoiceNumberRepository.findAllByOrderByNumberDesc()
                .stream()
                .findFirst()
                .orElse(null);
        if (newDoc == null) {
            newDoc = new InvoiceNumber(1l, series);
        } else {
            newDoc = new InvoiceNumber(newDoc.getNumber() + 1L, series);
        }

        return invoiceNumberRepository.save(newDoc);
    }

    @GetMapping(path = "/newDocument")
    public @ResponseBody
    Document newDocument() {
        Document doc = new Document();
        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        doc.setDescription(formattedDateTime);
        Document save = documentRepositoryCustom.save(doc);

        return save;
    }

    @GetMapping(path = "/allInvoiceDetails")
    public @ResponseBody
    Iterable<InvoiceDetails> getAllInvoiceDetails() {
        Iterable<InvoiceDetails> all = invoiceDetailsRepository.findAll();
        return all;
    }


    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<InvoiceNumber> getAllDocs() {
        return invoiceNumberRepository.findAll();
    }

    @GetMapping(path = "/allString")
    public @ResponseBody
    String getAllDocsAsString() {
        return invoiceNumberRepository.findAll().toString();
    }


//    @GetMapping(path = "/all")
//    public @ResponseBody
//    Iterable<Bill> getAllBills() {
//        return billRepository.findAll();
//    }

    @GetMapping(path = "/test")
    public @ResponseBody
    String test() {
        return "ok";
    }


}
