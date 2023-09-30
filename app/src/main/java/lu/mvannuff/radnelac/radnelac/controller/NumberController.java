package lu.mvannuff.radnelac.radnelac.controller;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lu.mvannuff.radnelac.generated.api.ValidateNumberApi;
import lu.mvannuff.radnelac.generated.model.NumberValidationResult;
import lu.mvannuff.radnelac.radnelac.domain.services.NumberValidationService;
import lu.mvannuff.radnelac.radnelac.security.model.ExtendedUserDetails;
import lu.mvannuff.radnelac.radnelac.security.service.AuthUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NumberController implements ValidateNumberApi {

    private final NumberValidationService numberValidationService;
    private final AuthUserService authUserService;

    @Override
    @RolesAllowed("SERVICER")
    public ResponseEntity<NumberValidationResult> validate(String num) {
        var userDetails = authUserService.getAuthenticatedUser();
        return ResponseEntity.ok(numberValidationService.validate(num, userDetails.getUserId()));
    }

}
