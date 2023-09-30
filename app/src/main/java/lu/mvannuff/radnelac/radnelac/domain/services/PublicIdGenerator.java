package lu.mvannuff.radnelac.radnelac.domain.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class PublicIdGenerator {

    public String generatePublicId(){
       return "RDV-" + RandomStringUtils.randomAlphanumeric(12);
    }
}
