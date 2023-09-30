package lu.mvannuff.radnelac.radnelac.controller;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lu.mvannuff.radnelac.generated.api.RendezVousManagementApi;
import lu.mvannuff.radnelac.generated.model.Page;
import lu.mvannuff.radnelac.generated.model.RendezVousForm;
import lu.mvannuff.radnelac.generated.model.RendezVousList;
import lu.mvannuff.radnelac.generated.model.RendezVousListItem;
import lu.mvannuff.radnelac.radnelac.domain.services.RendezVousManagmentService;
import lu.mvannuff.radnelac.radnelac.security.service.AuthUserService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
@RolesAllowed("SERVICER")
public class RendezVousController implements RendezVousManagementApi {

    private final RendezVousManagmentService rendezVousManagmentService;
    private final AuthUserService authUserService;

    @Override
    public ResponseEntity<RendezVousListItem> createRendezVous(RendezVousForm rendezVousForm) {
        var userDetails = authUserService.getAuthenticatedUser();
        return ResponseEntity.ok(rendezVousManagmentService.createRendezVous(rendezVousForm, userDetails.getUserId()));
    }

    @Override
    public ResponseEntity<RendezVousList> listRendezVous(OffsetDateTime dateFrom, OffsetDateTime dateTo, Page page) {
        throw new NotYetImplementedException();
    }

    @Override
    public ResponseEntity<RendezVousListItem> privateRendezVousView(Long rdvId) {
        var userDetails = authUserService.getAuthenticatedUser();
        return ResponseEntity.ok(rendezVousManagmentService.getFullRdvView(rdvId, userDetails.getUserId()));
    }
}
