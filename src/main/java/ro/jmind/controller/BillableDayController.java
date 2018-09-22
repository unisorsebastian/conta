package ro.jmind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.jmind.model.BillableDay;
import ro.jmind.repo.BillableDayRepository;
import ro.jmind.repo.BillableDayRepositoryCustom;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(path = "/BillableDay")
public class BillableDayController {
    private BillableDayRepository billableDayRepository;
    private BillableDayRepositoryCustom<BillableDay> billableDayRepositoryCustom;

    public BillableDayController(BillableDayRepository billableDayRepository, BillableDayRepositoryCustom<BillableDay> billableDayRepositoryCustom) {
        this.billableDayRepository = billableDayRepository;
        this.billableDayRepositoryCustom = billableDayRepositoryCustom;
    }

    @PostMapping("")
    public @ResponseBody
    BillableDay addBillableDay(@RequestBody BillableDay billableDay) {
        BillableDay save = billableDayRepositoryCustom.save(billableDay);
        return save;
    }

    @GetMapping(path = "")
    public @ResponseBody
    Iterable<BillableDay> findAllBillableDayLatestVersion() {
        return billableDayRepositoryCustom.findAll();
    }

    @GetMapping(path = "/date/{dateAsString}")
    public @ResponseBody
    BillableDay getBillableDay(@PathVariable String dateAsString) {
        LocalDate date = LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return billableDayRepository.findFirstBillableDayByDateOrderByVersionDesc(date);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody
    BillableDay getBillableDay(@PathVariable Long id) {
        return billableDayRepository.findById(id).orElse(null);
    }


}
