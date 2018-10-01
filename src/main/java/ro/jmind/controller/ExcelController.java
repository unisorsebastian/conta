package ro.jmind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.jmind.model.Invoice;
import ro.jmind.service.ExcelService;

import java.io.IOException;


@Controller
@RequestMapping(path = "api/excel")
public class ExcelController {
    private ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/excel/invoice/{id}")
    public @ResponseBody
    Invoice generateInvoice(@PathVariable Long id) throws IOException {
        Invoice invoice = excelService.getInvoice(id);
        excelService.generateInvoice(invoice);
        return invoice;
    }

    @GetMapping("/excel/template/{id}")
    public @ResponseBody
    Invoice generateTemplate(@PathVariable Long id) throws IOException {
        Invoice invoice = excelService.getInvoice(id);
        excelService.generateInvoice(invoice);
        return invoice;
    }
}
