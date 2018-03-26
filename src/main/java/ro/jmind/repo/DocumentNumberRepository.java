package ro.jmind.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.jmind.model.DocumentNumber;

import java.util.List;

import static javafx.scene.input.KeyCode.T;

@Repository
public interface DocumentNumberRepository extends CrudRepository<DocumentNumber, Long> {
    List<DocumentNumber> findAllByOrderByNumberDesc();
}
