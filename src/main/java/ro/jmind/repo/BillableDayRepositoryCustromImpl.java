package ro.jmind.repo;

import org.springframework.stereotype.Repository;
import ro.jmind.model.BillableDay;

@Repository
public class BillableDayRepositoryCustromImpl implements BillableDayRepositoryCustom<BillableDay> {
    private BillableDayRepository billableDayRepository;

    public BillableDayRepositoryCustromImpl(BillableDayRepository billableDayRepository) {
        this.billableDayRepository = billableDayRepository;
    }

    @Override
    public BillableDay save(BillableDay billableDay) {
        BillableDay recent = billableDayRepository.findFirstBillableDayByDateOrderByVersionDesc(billableDay.getDate());
        Long version = 0L;
        if (recent != null) {
            billableDay.setVersion(recent.getVersion() + 1);
        }
        return billableDayRepository.save(billableDay);
    }

    @Override
    public Iterable<BillableDay> findAll() {
        return billableDayRepository.findAllLastVersion();
    }
}
