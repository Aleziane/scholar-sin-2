package lu.mvannuff.radnelac.radnelac.domain.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lu.mvannuff.radnelac.generated.model.NumberValidationResult;
import lu.mvannuff.radnelac.radnelac.domain.entity.RdvStatus;
import lu.mvannuff.radnelac.radnelac.domain.repository.ClientRepository;
import lu.mvannuff.radnelac.radnelac.domain.repository.RendezVousRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class NumberValidationService {

    private final ClientRepository clientRepository;
    private final NumberFormater numberFormater;
    private final RendezVousRepository rendezVousRepository;

    public NumberValidationResult validate(String phoneNumber, Long servicerId) {
        String formatted = numberFormater.formatNumber(phoneNumber);
        var clientO = clientRepository.findByNumberAndServicer(formatted, servicerId);

        if (clientO.isEmpty()) {
            return new NumberValidationResult().number(phoneNumber).result(RdvStatus.HONOURED.name());
        }
        var missedRdv = rendezVousRepository.findByServicerIdAndClientIdAndBlacklisted(clientO.get().getId(), servicerId);

        if (missedRdv.isEmpty()) {
            return new NumberValidationResult().number(phoneNumber).result(RdvStatus.HONOURED.name());
        }
        return new NumberValidationResult().number(phoneNumber).result(RdvStatus.BLACKLIST.name());

    }
}
