package lu.mvannuff.radnelac.radnelac.scholar.sin.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy="invoice")
    private List<Record> records;

    private Boolean paid;

    private OffsetDateTime sendDate;

    @Lob @Basic(fetch=LAZY)
    private byte[] file;
    @CreationTimestamp
    private OffsetDateTime registrationDate;
}
