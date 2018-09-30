package ro.jmind.controller;

import org.springframework.web.bind.annotation.*;
import ro.jmind.model.BillableDay;
import ro.jmind.repo.BillableDayRepository;
import ro.jmind.repo.BillableDayRepositoryCustom;
import ro.jmind.service.BillableDayService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(path = "/BillableDay")
public class BillableDayController {
    private BillableDayRepository billableDayRepository;
    private BillableDayRepositoryCustom<BillableDay> billableDayRepositoryCustom;
    private BillableDayService billableDayService;

    public BillableDayController(BillableDayRepository billableDayRepository, BillableDayRepositoryCustom<BillableDay> billableDayRepositoryCustom, BillableDayService billableDayService) {
        this.billableDayRepository = billableDayRepository;
        this.billableDayRepositoryCustom = billableDayRepositoryCustom;
        this.billableDayService = billableDayService;
    }

    @PostMapping("")
    public @ResponseBody
    BillableDay addBillableDay(@RequestBody BillableDay billableDay) {
        BillableDay save = billableDayRepositoryCustom.save(billableDay);
        return save;
    }

    @PutMapping("/{id}")
    BillableDay updateBillableDay(@RequestBody BillableDay billableDay, @PathVariable Long id) {
        BillableDay save = billableDayService.update(billableDay);
        return save;
    }
    @GetMapping(path = "")
    public @ResponseBody
    Iterable<BillableDay> findAll() {
        return billableDayService.findAllBillableDayLatestVersion();
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
