package lu.mvannuff.radnelac.radnelac.controller;

import lombok.RequiredArgsConstructor;
import lu.mvannuff.radnelac.generated.api.PublicRendezVousApi;
import lu.mvannuff.radnelac.generated.model.RendezVousConfirmation;
import lu.mvannuff.radnelac.generated.model.RendezVousConfirmationResponse;
import lu.mvannuff.radnelac.generated.model.RendezVousPublicView;
import lu.mvannuff.radnelac.radnelac.domain.services.RendezVousManagmentService;
import lu.mvannuff.radnelac.radnelac.security.model.ExtendedUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublicRdvController implements PublicRendezVousApi {

    private final RendezVousManagmentService rendezVousManagmentService;

    @Override
    public ResponseEntity<RendezVousConfirmationResponse> confirmRendezVous(RendezVousConfirmation rendezVousConfirmation) {
        return ResponseEntity.ok(rendezVousManagmentService.confirm(rendezVousConfirmation));
    }

    @Override
    public ResponseEntity<RendezVousPublicView> publicRendezVousView(String publicId) {
        return ResponseEntity.ok(rendezVousManagmentService.getPublicRdvView(publicId));
    }
}
