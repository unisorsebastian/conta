package ro.jmind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.jmind.app.CoreProperties;
import ro.jmind.model.BillableDay;
import ro.jmind.model.Invoice;
import ro.jmind.model.InvoiceNumber;
import ro.jmind.repo.*;
import ro.jmind.service.ExcelService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

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
    private BillableDayRepository billableDayRepository;
    @Autowired
    private CoreProperties coreProperties;

    @Autowired
    private ExcelService excelService;


    @GetMapping("/excel/invoice/{id}")
    public @ResponseBody
    Invoice generateInvoice(@PathVariable Long id) throws IOException {
        Invoice invoice = getInvoice(id);
        excelService.generateInvoice(invoice);
        return invoice;
    }

    @GetMapping("/excel/template/{id}")
    public @ResponseBody
    Invoice generateTemplate(@PathVariable Long id) throws IOException {
        Invoice invoice = getInvoice(id);
        excelService.generateInvoice(invoice);
        return invoice;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Invoice createInvoice(@RequestBody Invoice invoice) {
        Invoice save = invoiceRepositoryCustom.save(invoice);
        return save;
    }

    @GetMapping("/billable")
    public @ResponseBody
    Iterable<BillableDay> addBillableDays() throws IOException {
        BillableDay billableDay;
        List<BillableDay> billableDays = new ArrayList<>();


        LocalDate firstDay = LocalDate.of(2018, Month.AUGUST, 1);
        LocalDate lastDay = firstDay.with(lastDayOfMonth());
        List<LocalDate> allDays = new ArrayList<>();
        while (true) {
            allDays.add(firstDay);
            firstDay = firstDay.plusDays(1);
            if (firstDay.isEqual(lastDay)) {
                allDays.add(firstDay);
                break;
            }
        }
        for (LocalDate day : allDays) {
            if (!(day.getDayOfWeek().equals(DayOfWeek.SATURDAY) || day.getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
                billableDay = new BillableDay();
                billableDay.setDate(day);
                billableDay.setHours(new BigDecimal(8));
                billableDay.setRate(new BigDecimal(25));
                billableDays.add(billableDay);
            }
        }

        Iterable<BillableDay> billableDays1 = billableDayRepository.saveAll(billableDays);
        return billableDays1;
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public @ResponseBody
//    Invoice createInvoice(@RequestBody BillingAmount billingAmount) {
//        ExchangeRate rate = billingAmount.getExchangeRate();
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
//        invoice.setBillingAmount(billingAmount);
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
