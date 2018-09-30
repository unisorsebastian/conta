package ro.jmind.service;

import org.springframework.stereotype.Service;
import ro.jmind.model.BillableDay;
import ro.jmind.repo.BillableDayRepository;
import ro.jmind.repo.BillableDayRepositoryCustom;

import javax.transaction.Transactional;

@Service("billableDayService")
@Transactional
public class BillableDayService {
    private BillableDayRepository billableDayRepository;
    private BillableDayRepositoryCustom<BillableDay> billableDayRepositoryCustom;

    public BillableDayService(BillableDayRepository billableDayRepository, BillableDayRepositoryCustom<BillableDay> billableDayRepositoryCustom) {
        this.billableDayRepository = billableDayRepository;
        this.billableDayRepositoryCustom = billableDayRepositoryCustom;
    }

    public Iterable<BillableDay> findAllBillableDayLatestVersion() {
        return billableDayRepositoryCustom.findAll();
    }

    public  BillableDay update(BillableDay billableDay){
        BillableDay update = new BillableDay();
        update.setDate(billableDay.getDate());
        update.setDescription(billableDay.getDescription());
        update.setHours(billableDay.getHours());
        update.setRate(billableDay.getRate());
        update.setVersion(billableDay.getVersion()+1);
        return billableDayRepository.save(update);
    }

}
