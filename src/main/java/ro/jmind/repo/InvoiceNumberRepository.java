package ro.jmind.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.jmind.model.InvoiceNumber;

import java.util.List;

@Repository
public interface InvoiceNumberRepository extends CrudRepository<InvoiceNumber, Long> {
    List<InvoiceNumber> findAllByOrderByNumberDesc();

    List<InvoiceNumber> findAllByOrderByNumberAsc();
}
