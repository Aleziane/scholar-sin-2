package lu.mvannuff.radnelac.radnelac.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private RdvStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    private RdvConfirmationStatus confirmationStatus;
    @Column
    private String publicId;
    @Column
    private OffsetDateTime dueDate;
    @Column
    private OffsetDateTime registrationDate;
    @Column
    private Long prepayment;
    @Column
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicer_id")
    private Servicer servicer;
}
