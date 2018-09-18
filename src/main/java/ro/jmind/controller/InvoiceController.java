package ro.jmind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.jmind.model.BillingAmount;
import ro.jmind.model.Invoice;
import ro.jmind.model.InvoiceNumber;
import ro.jmind.repo.InvoiceNumberRepository;
import ro.jmind.repo.InvoiceRepository;
import ro.jmind.repo.InvoiceRepositoryCustom;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(path = "/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceRepositoryCustom invoiceRepositoryCustom;
    @Autowired
    private InvoiceNumberRepository invoiceNumberRepository;



    @GetMapping(path = "/new")
    public @ResponseBody
    Invoice newDocument() {
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BillingAmount amount = new BillingAmount("22.3","4.6543","20180831");

        Invoice invoice = new Invoice();
        invoice.setDescription(formattedDate);
        invoice.setAmount(amount);


        Invoice save = invoiceRepositoryCustom.save(invoice);
        System.out.println(save);
        invoice = new Invoice();
        invoice.setDescription(formattedDate);
        invoice.setAmount(amount);
        save = invoiceRepositoryCustom.save(invoice);

        return save;
    }


    @GetMapping(path = "/allInvoice")
    public @ResponseBody
    Iterable<Invoice> getAllInvoice() {
        return invoiceRepository.findAllByOrderByIdDesc();
    }

    @GetMapping(path = "/allInvoiceNumber")
    public @ResponseBody
    Iterable<InvoiceNumber> getAllInvoiceNumber() {
        Iterable<InvoiceNumber> all = invoiceNumberRepository.findAll();
        System.out.println(all);
        return all;
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
