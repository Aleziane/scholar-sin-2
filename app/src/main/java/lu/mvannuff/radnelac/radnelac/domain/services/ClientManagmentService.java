package lu.mvannuff.radnelac.radnelac.domain.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lu.mvannuff.radnelac.radnelac.domain.entity.Client;
import lu.mvannuff.radnelac.radnelac.domain.entity.Servicer;
import lu.mvannuff.radnelac.radnelac.domain.repository.ClientRepository;
import lu.mvannuff.radnelac.radnelac.domain.repository.ServicerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientManagmentService {
    private final NumberFormater numberFormater;
    private final ClientRepository clientRepository;
    private final ServicerRepository servicerRepository;

    public Client createOrFind(String phoneNumber, String name, Long servicerId) {
        String formatted = numberFormater.formatNumber(phoneNumber);
        var clientO = clientRepository.findByNumberAndServicer(formatted, servicerId);
        Servicer servicer = servicerRepository.findById(servicerId).orElseThrow();

        return clientO.orElseGet(() -> clientRepository.save(Client.builder()
                .number(formatted)
                .name(name)
                .servicer(servicer)
                .build()));

    }

}
