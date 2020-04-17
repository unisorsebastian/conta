package ro.jmind.repo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ro.jmind.model.ExcelRecord;

import java.io.*;

@Repository("excelRecordRepositoryExcelDBImpl")
public class ExcelRecordRepositoryExcelDBImpl implements ExcelRecordRepositoryExcelDB {

    @Value("${core.store.database.file}")
    private String dbURI;

    @Override
    public ExcelRecord save(ExcelRecord record) {
        FileInputStream stream = null;
        XSSFWorkbook workbook = null;
        try {
            stream = new FileInputStream(dbURI);
            workbook = new XSSFWorkbook(stream);
            XSSFSheet dataSheet = workbook.getSheet("data");

            Cell billSeriesCell = null;
            billSeriesCell = dataSheet.getRow(2).getCell(3);
            billSeriesCell.setCellValue("data");

            stream.close();

//            FileOutputStream outFile = new FileOutputStream(new File(finalFile));
//            workbook.write(outFile);
//            outFile.close();



        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
