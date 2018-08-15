package ro.jmind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.jmind.model.Document;
import ro.jmind.model.DocumentNumber;
import ro.jmind.repo.DocumentNumberRepository;
import ro.jmind.repo.DocumentRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(path = "/doc")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentNumberRepository documentNumberRepository;

    @Value("${document.default.series}")
    private String series;


    @GetMapping(path = "/next")
    public @ResponseBody
    DocumentNumber newDocId() {
        DocumentNumber documentNumber = documentNumberRepository.findAllByOrderByNumberDesc()
                .stream()
                .findFirst()
                .orElse(null);
        if (documentNumber == null) {
            documentNumber = new DocumentNumber(1l, series);
        } else {
            documentNumber = new DocumentNumber(documentNumber.getNumber() + 1L, series);
        }

        return documentNumberRepository.save(documentNumber);
    }

    @GetMapping(path = "/newDoc")
    public @ResponseBody
    Document newDoc() {
        Document document = new Document();
        document.setDescription("desc"+LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        return documentRepository.save(document);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<DocumentNumber> getAllDocs() {
        return documentNumberRepository.findAll();
    }

    @GetMapping(path = "/allString")
    public @ResponseBody
    String getAllDocsAsString() {
        return documentNumberRepository.findAll().toString();
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
