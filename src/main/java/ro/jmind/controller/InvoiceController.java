package ro.jmind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.jmind.app.CoreProperties;
import ro.jmind.model.BillingAmount;
import ro.jmind.model.ExchangeRate;
import ro.jmind.model.Invoice;
import ro.jmind.model.InvoiceNumber;
import ro.jmind.repo.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    private ExchangeRateRepositoryCustom exchangeRateRepositoryCustom;
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    @Autowired
    private CoreProperties coreProperties;


    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Invoice createInvoice(@RequestBody Invoice invoice) {
        Invoice save = invoiceRepositoryCustom.save(invoice);
        return save;
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public @ResponseBody
//    Invoice createInvoice(@RequestBody BillingAmount amount) {
//        ExchangeRate rate = amount.getExchangeRate();
//        List<ExchangeRate> allByExchangeRate = exchangeRateRepository
//                .findAllByCurrencyAndLocalCurrencyAndAndParityAndExchangeDate(rate.getCurrency(),
//                        rate.getLocalCurrency(),
//                        rate.getParity(),
//                        rate.getExchangeDate());
//
//        ExchangeRate exchangeRate = allByExchangeRate.stream().findFirst().orElse(rate);
//
//        Invoice invoice = new Invoice();
//
//        invoice.setDate(rate.getExchangeDate());
//        invoice.setAmount(amount);
//
//        Invoice save = invoiceRepositoryCustom.save(invoice);
//
//        return save;
//    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Invoice getInvoice(@PathVariable Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping(path = "/allInvoiceNumber")
    public @ResponseBody
    Iterable<InvoiceNumber> getAllInvoiceNumber() {
        Iterable<InvoiceNumber> all = invoiceNumberRepository.findAll();
        System.out.println(all);
        return all;
    }


}
