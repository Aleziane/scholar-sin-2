package lu.mvannuff.radnelac.radnelac.scholar.sin.domain.entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class ContactDetails {


    private String motherFirstName;
    private String motherLastName;

    private String fatherFirstName;
    private String fatherLastName;

    private String contactFirstName;
    private String contactLastName;

    private String primaryContactPhone;
    private String secondaryContactPhone;

    private String mail;

    private String fullAddress;

}
