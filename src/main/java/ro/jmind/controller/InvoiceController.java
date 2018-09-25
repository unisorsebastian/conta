package ro.jmind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.jmind.app.CoreProperties;
import ro.jmind.model.Invoice;
import ro.jmind.repo.InvoiceNumberRepository;
import ro.jmind.repo.InvoiceRepository;
import ro.jmind.repo.InvoiceRepositoryCustom;

@Controller
@RequestMapping(path = "/Invoice")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceRepositoryCustom invoiceRepositoryCustom;
    @Autowired
    private InvoiceNumberRepository invoiceNumberRepository;

    @Autowired
    private CoreProperties coreProperties;


    @GetMapping(path = "")
    public @ResponseBody
    Iterable<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody
    Invoice getInvoice(@PathVariable Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @PostMapping(path = "")
    public @ResponseBody
    Invoice createInvoice(@RequestBody Invoice invoice) {
        Invoice save = invoiceRepositoryCustom.save(invoice);
        return save;
    }
}
