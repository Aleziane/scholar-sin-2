package lu.mvannuff.radnelac.radnelac.domain.events;

import lombok.RequiredArgsConstructor;
import lu.mvannuff.radnelac.radnelac.domain.entity.Client;
import lu.mvannuff.radnelac.radnelac.domain.entity.Servicer;
import lu.mvannuff.radnelac.radnelac.domain.repository.ClientRepository;
import lu.mvannuff.radnelac.radnelac.domain.repository.RendezVousRepository;
import lu.mvannuff.radnelac.radnelac.domain.repository.ServicerRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RendezVousCreationLister {

    private final ServicerRepository servicerRepository;
    private final ClientRepository clientRepository;
    private final RendezVousRepository rendezVousRepository;

    @EventListener
    public void rdvCreate(RdvCreationEvent rdvCreationEvent){
        Servicer servicer = servicerRepository.getReferenceById(rdvCreationEvent.getServicerId());
        Client client = clientRepository.getReferenceById(rdvCreationEvent.getClientId());
        rdvCreationEvent.getRendezVous().setClient(client);
        rdvCreationEvent.getRendezVous().setServicer(servicer);
        rendezVousRepository.save(rdvCreationEvent.getRendezVous());

    }
}
