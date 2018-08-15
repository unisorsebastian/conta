package ro.jmind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.jmind.model.Document;
import ro.jmind.model.InvoiceNumber;
import ro.jmind.repo.DocumentRepositoryCustom;
import ro.jmind.repo.InvoiceNumberRepository;
import ro.jmind.repo.DocumentRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(path = "/doc")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentRepositoryCustom documentRepositoryCustom;
    @Autowired
    private InvoiceNumberRepository docIdRepo;

    @Value("${document.default.series}")
    private String series;


    @GetMapping(path = "/next")
    public @ResponseBody
    InvoiceNumber newDocId() {
        InvoiceNumber newDoc = docIdRepo.findAllByOrderByNumberDesc()
                .stream()
                .findFirst()
                .orElse(null);
        if (newDoc == null) {
            newDoc = new InvoiceNumber(1l, series);
        } else {
            newDoc = new InvoiceNumber(newDoc.getNumber() + 1L, series);
        }

        return docIdRepo.save(newDoc);
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

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<InvoiceNumber> getAllDocs() {
        return docIdRepo.findAll();
    }

    @GetMapping(path = "/allString")
    public @ResponseBody
    String getAllDocsAsString() {
        return docIdRepo.findAll().toString();
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
