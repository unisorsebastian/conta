package ro.jmind.repo;

import org.springframework.data.repository.CrudRepository;
import ro.jmind.model.Timesheet;

import java.time.LocalDate;

public interface TimesheetRepository extends CrudRepository<Timesheet, LocalDate> {
}
