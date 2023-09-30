package lu.mvannuff.radnelac.radnelac.domain.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lu.mvannuff.radnelac.generated.model.*;
import lu.mvannuff.radnelac.radnelac.domain.entity.Client;
import lu.mvannuff.radnelac.radnelac.domain.entity.RdvConfirmationStatus;
import lu.mvannuff.radnelac.radnelac.domain.entity.RdvStatus;
import lu.mvannuff.radnelac.radnelac.domain.entity.RendezVous;
import lu.mvannuff.radnelac.radnelac.domain.events.RdvCreationEvent;
import lu.mvannuff.radnelac.radnelac.domain.repository.RendezVousRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RendezVousManagmentService {

    private final RendezVousRepository rendezVousRepository;
    private final ClientManagmentService clientManagmentService;
    private final ApplicationEventPublisher applicationEventPublisher;

    private final PublicIdGenerator publicIdGenerator;

    public RendezVousListItem createRendezVous(RendezVousForm rendezVousForm, Long servicerId) {

        Client client = clientManagmentService.createOrFind(rendezVousForm.getPhone(), rendezVousForm.getClientName(), servicerId);

        var now = OffsetDateTime.now();
        RendezVous rendezVous = RendezVous.builder()
                .dueDate(rendezVousForm.getDate())
                .status(RdvStatus.PENDING)
                .confirmationStatus(RdvConfirmationStatus.PENDING_CONFIRMATION)
                .registrationDate(now)
                .prepayment(rendezVousForm.getPrepayment())
                .note(rendezVousForm.getNote())
                .publicId(publicIdGenerator.generatePublicId())
                .build();
        applicationEventPublisher.publishEvent(
                RdvCreationEvent.builder()
                        .rendezVous(rendezVous)
                        .servicerId(servicerId)
                        .clientId(client.getId())
                        .build()
        );
        return fromRendezVous(rendezVous);
    }

    //todo: set up domain exception
    public RendezVousListItem getFullRdvView(Long rdvid, Long servicerId) {
        var rdv = rendezVousRepository.findByIdAndServicerId(rdvid, servicerId).orElseThrow(() -> new RuntimeException("not found"));
        return fromRendezVous(rdv);
    }

    public RendezVousPublicView getPublicRdvView(String publicId) {

        var rdv = findRdvWithPubId(publicId);

        return new RendezVousPublicView()
                .dueDate(rdv.getDueDate())
                .registrationDate(rdv.getRegistrationDate())
                .prepayment(rdv.getPrepayment())
                .servicerName(rdv.getServicer().getFullName())
                .publicId(rdv.getPublicId());
    }


    public RendezVousConfirmationResponse confirm(RendezVousConfirmation rendezVousConfirmation){
        var rdv = findRdvWithPubId(rendezVousConfirmation.getPublicId());
        var confirmation = rendezVousConfirmation.getAccept() ? RdvConfirmationStatus.CONFIRMED : RdvConfirmationStatus.REJECTED;
        rdv.setConfirmationStatus(confirmation);
        return new RendezVousConfirmationResponse()
                .accept(rendezVousConfirmation.getAccept())
                .publicId(rdv.getPublicId())
                .paymentUrl("Not yet implemented");
    }

    private RendezVous findRdvWithPubId(String pubId){
        var rdvList = rendezVousRepository.findByPublicIdAndStatusOrderByRegistrationDateDesc(pubId, RdvStatus.PENDING);

        if (rdvList.size() == 0) {
            throw new RuntimeException("Not found");
        }
        if (rdvList.size() > 1) {
            log.error("CRITICAL - PUBLIC ID collision {}, returning most recent one", pubId);
        }

        return rdvList.get(0);
    }

    private RendezVousListItem fromRendezVous(RendezVous rdv){
        return new RendezVousListItem()
                .status(rdv.getStatus().name())
                .confirmationLink(rdv.getPublicId())
                .id(rdv.getId())
                .dueDate(rdv.getDueDate())
                .registrationDate(rdv.getRegistrationDate())
                .prepayment(rdv.getPrepayment())
                .phone(rdv.getClient().getNumber())
                .clientName(rdv.getClient().getName())
                .note(rdv.getNote());
    }

}
