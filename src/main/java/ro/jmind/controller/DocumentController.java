package ro.jmind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.jmind.model.DocumentNumber;
import ro.jmind.repo.DocumentNumberRepository;
import ro.jmind.repo.DocumentRepository;

@Controller
@RequestMapping(path = "/doc")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentNumberRepository docIdRepo;

    @Value("${document.default.series}")
    private String series;


    @GetMapping(path = "/next")
    public @ResponseBody
    DocumentNumber newDocId() {
        DocumentNumber newDoc = docIdRepo.findAllByOrderByNumberDesc()
                .stream()
                .findFirst()
                .orElse(null);
        if (newDoc == null) {
            newDoc = new DocumentNumber(1l, series);
        } else {
            newDoc = new DocumentNumber(newDoc.getNumber() + 1L, series);
        }

        return docIdRepo.save(newDoc);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<DocumentNumber> getAllDocs() {
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
