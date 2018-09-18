package ro.jmind.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.jmind.model.BillingAmount;
import ro.jmind.model.ExchangeRate;
import ro.jmind.model.Invoice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

@Service
@Getter
@Setter
public class ExcelService {
    @Value("${core.document.excel.bill.template}")
    private String billTemplateLocation;
    @Value("${core.document.excel.timesheet.template}")
    private String timesheetTemplateLocation;
    @Value("${core.document.excel.timesheet.sheet}")
    private String timesheetSheet;
    @Value("${core.document.excel.bill.sheet}")
    private String billSheet;
    @Value("${core.document.excel.out.location}")
    private String outLocation;


    public void generateInvoice(Invoice invoice) throws IOException {
        String finalFile = outLocation + "invoiceVauban.xlsx";
        FileInputStream stream = new FileInputStream(billTemplateLocation);
        XSSFWorkbook workbook = new XSSFWorkbook(stream);
        XSSFSheet billTemplateSheet = workbook.getSheet(billSheet);

        String invoiceNumberValue = invoice.getInvoiceNumber().getNumber().toString();
        String invoiceNumberSeriesValue = invoice.getInvoiceNumber().getSeries();
        String invoiceDateValue = invoice.getDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        String billIntervalValue = invoice.getDate().format(DateTimeFormatter.ofPattern("MMM-yyyy"));

        BillingAmount billingAmount = invoice.getBillingAmount();
        ExchangeRate exchangeRate = billingAmount.getExchangeRate();
        Currency localCurrency = exchangeRate.getLocalCurrency();
        BigDecimal parity = exchangeRate.getParity();
        BigDecimal amount = billingAmount.getAmount();
        BigDecimal multiply = amount.multiply(parity).setScale(0, BigDecimal.ROUND_HALF_UP);
        String totalAmountValue = multiply.toString();

        Cell invoiceNumber = null;
        invoiceNumber = billTemplateSheet.getRow(2).getCell(3);
        invoiceNumber.setCellValue(invoiceNumberValue);

        Cell invoiceSeries = null;
        invoiceSeries = billTemplateSheet.getRow(2).getCell(1);
        invoiceSeries.setCellValue(invoiceNumberSeriesValue);

        Cell invoiceDate = null;
        invoiceDate = billTemplateSheet.getRow(4).getCell(1);
        invoiceDate.setCellValue(invoiceDateValue);

        Cell pricePerUnit = null;
        pricePerUnit = billTemplateSheet.getRow(22).getCell(5);
        pricePerUnit.setCellValue(totalAmountValue);

        Cell totalPricePerUnit = null;
        totalPricePerUnit = billTemplateSheet.getRow(22).getCell(6);
        totalPricePerUnit.setCellValue(totalAmountValue);

        Cell billInterval = null;
        billInterval = billTemplateSheet.getRow(27).getCell(2);
        billInterval.setCellValue(billIntervalValue);

        Cell totalWithoutTax = null;
        totalWithoutTax = billTemplateSheet.getRow(29).getCell(6);
        totalWithoutTax.setCellValue(totalAmountValue);

        Cell totalAmount = null;
        totalAmount = billTemplateSheet.getRow(31).getCell(6);
        totalAmount.setCellValue(totalAmountValue);


        stream.close();

        FileOutputStream outFile = new FileOutputStream(new File(finalFile));
        workbook.write(outFile);
        outFile.close();
    }
}
