package ro.jmind.app;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ro.jmind.repo.ExcelRecordRepositoryExcelDB;
import ro.jmind.repo.ExcelRecordRepositoryExcelDBImpl;

import static org.junit.Assert.assertTrue;

//@RunWith(SpringRunner.class)
//@SpringBootTest

@TestPropertySource(locations = "classpath:test.properties")
public class ExcelRecordRepositoryExcelDBTest {
    @Autowired
    private ExcelRecordRepositoryExcelDB excelRecordRepositoryExcelDB;

    @Before
    public void setup(){
        System.out.println("setup done");
//        excelRecordRepositoryExcelDB = new ExcelRecordRepositoryExcelDBImpl();
    }

    @Test
    public void accessFileTest(){
        assertTrue(true);

    }

}
