package lu.mvannuff.radnelac.radnelac.domain.repository;

import lu.mvannuff.radnelac.radnelac.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "Select * from client where number = :number  and servicer_id = :servicerId", nativeQuery = true)
    Optional<Client> findByNumberAndServicer(@Param("number") String number, @Param("servicerId") Long servicerId);

}
