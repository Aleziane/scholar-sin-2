package lu.mvannuff.radnelac.radnelac.domain.services;

import lombok.RequiredArgsConstructor;
import lu.mvannuff.radnelac.radnelac.domain.entity.Servicer;
import lu.mvannuff.radnelac.radnelac.domain.repository.ServicerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicerManagmentService {

    private final ServicerRepository servicerRepository;

    public Servicer findOrCreate(String servicerName){
       return servicerRepository.findByMail(servicerName)
                .orElseGet(() -> servicerRepository.save(Servicer.builder()
                                .mail(servicerName)
                        .build()));
    }



}
