package lu.mvannuff.radnelac.radnelac.domain.repository;

import lu.mvannuff.radnelac.radnelac.domain.entity.Servicer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicerRepository extends JpaRepository<Servicer, Long> {

    Optional<Servicer> findByMail(String mail);

}
