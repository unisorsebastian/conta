package ro.jmind.repo;

import org.springframework.stereotype.Component;
import ro.jmind.model.ExcelRecord;

@Component
public interface ExcelRecordRepositoryExcelDB {
    ExcelRecord save(ExcelRecord record);
}
