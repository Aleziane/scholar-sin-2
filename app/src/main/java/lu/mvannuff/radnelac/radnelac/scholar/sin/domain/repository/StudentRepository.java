package lu.mvannuff.radnelac.radnelac.scholar.sin.domain.repository;

import lu.mvannuff.radnelac.radnelac.scholar.sin.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
