package ro.jmind.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.jmind.model.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {
}
