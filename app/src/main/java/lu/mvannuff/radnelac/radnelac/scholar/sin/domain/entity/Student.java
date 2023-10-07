package lu.mvannuff.radnelac.radnelac.scholar.sin.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private OffsetDateTime birthDate;
    private String lastName;
    private String matricule;
    private String freeText;
    @Embedded
    private ContactDetails contactDetails;
    @CreationTimestamp
    private OffsetDateTime registrationDate;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id")
    private List<Record> records;


}
