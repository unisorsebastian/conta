package ro.jmind.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.jmind.model.BillableDay;

import java.time.LocalDate;

@Repository
public interface BillableDayRepository extends CrudRepository<BillableDay, Long> {
    BillableDay findFirstBillableDayByDateOrderByVersionDesc(LocalDate date);

    @Query(value = "select B_DAY.id, B_DAY.date, B_DAY.description, B_DAY.hours, B_DAY.rate, B_DAY.version " +
            "from billable_day B_DAY " +
            "join " +
            "(SELECT max(version) version, date FROM BILLABLE_DAY group by date) LATEST_VERSION " +
            "on B_DAY.date=LATEST_VERSION.date and B_DAY.version=LATEST_VERSION.version", nativeQuery = true)
    Iterable<BillableDay> findAllLastVersion();
}
