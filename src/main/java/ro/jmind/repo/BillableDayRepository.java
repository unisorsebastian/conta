package ro.jmind.repo;

import org.springframework.data.repository.CrudRepository;
import ro.jmind.model.BillableDay;
import ro.jmind.model.BillingAmount;

public interface BillableDayRepository extends CrudRepository<BillableDay, Long> {
}
