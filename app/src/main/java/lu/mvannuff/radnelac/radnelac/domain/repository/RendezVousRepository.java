package lu.mvannuff.radnelac.radnelac.domain.repository;

import lu.mvannuff.radnelac.radnelac.domain.entity.RdvStatus;
import lu.mvannuff.radnelac.radnelac.domain.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    @Query(value = "Select * from rendez_vous where id = :rdvId  and servicer_id = :servicerId", nativeQuery = true)
    Optional<RendezVous> findByIdAndServicerId(@Param("rdvId") Long rdvId, @Param("servicerId") Long servicerId);

    @Query(value = "Select * from rendez_vous where client_id = :clientId  and servicer_id = :servicerId and status = 'BLACKLIST' limit 1", nativeQuery = true)
    Optional<RendezVous> findByServicerIdAndClientIdAndBlacklisted(@Param("clientId") Long clientId, @Param("servicerId") Long servicerId);

    List<RendezVous> findByPublicIdAndStatusOrderByRegistrationDateDesc(String publicId, RdvStatus status);
}
