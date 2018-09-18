package ro.jmind.repo;

import org.springframework.data.repository.CrudRepository;
import ro.jmind.model.BillingAmount;

public interface BillingAmountRepository extends CrudRepository<BillingAmount, Long> {
}
