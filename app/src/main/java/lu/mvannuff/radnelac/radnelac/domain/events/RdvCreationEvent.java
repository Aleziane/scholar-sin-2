package lu.mvannuff.radnelac.radnelac.domain.events;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lu.mvannuff.radnelac.radnelac.domain.entity.RendezVous;

@RequiredArgsConstructor
@Builder
@Getter
public class RdvCreationEvent {

    private final RendezVous rendezVous;
    private final Long servicerId;
    private final Long clientId;
}
