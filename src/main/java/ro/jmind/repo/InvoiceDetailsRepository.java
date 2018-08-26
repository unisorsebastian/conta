package ro.jmind.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.jmind.model.InvoiceDetails;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface InvoiceDetailsRepository extends CrudRepository<InvoiceDetails, Long> {
    List<InvoiceDetails> findAllByOrderByNumberDesc();

//    @Query("SELECT  u.userName FROM  User u INNER JOIN Area a ON a.idUser = u.idUser WHERE a.idArea = :idArea")
//    List<User> findAll(@Param("idArea") Long idArea);

    @Query("SELECT ino.document_id FROM InvoiceNumber ino INNER JOIN Document doc ON doc.id = ino.id")
    List<InvoiceDetails> findAll();
}
