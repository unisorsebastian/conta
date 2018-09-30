package ro.jmind.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.jmind.model.Timesheet;
import ro.jmind.repo.TimesheetRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(path = "/Timesheet")
public class TimesheetController {
    private TimesheetRepository timesheetRepository;

    public TimesheetController(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    @GetMapping(path = "/date/{dateAsString}")
    public @ResponseBody
    Timesheet getTimesheet(@PathVariable String dateAsString) {
        LocalDate initial = LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate firstDay = initial.withDayOfMonth(1);
        LocalDate lastDay = initial.withDayOfMonth(initial.lengthOfMonth());

        Timesheet timesheet = timesheetRepository.findById(firstDay).orElse(new Timesheet());

//        Comparator<BillableDay> bdc = (o1, o2) -> o1.getDate().compareTo(o2.getDate());
//        Set<BillableDay> worked = new TreeSet<>(bdc);
//
//
//        timesheet.setBillableDays(worked);
//        timesheet.setDate(firstDay);
//
//        LocalDate tmp = firstDay;
//        BillableDay billableDay;
//        while (true) {
//            DayOfWeek dayOfWeek = tmp.getDayOfWeek();
//            if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
//                billableDay = new BillableDay(tmp, "0", "0", "weekend");
//            } else {
//                billableDay = new BillableDay(tmp, "8", "25", "full day");
//            }
//            worked.add(billableDay);
//            if (tmp.isEqual(lastDay)) {
//                break;
//            }
//            tmp = tmp.plusDays(1);
//        }
        return timesheetRepository.save(timesheet);
    }


}
