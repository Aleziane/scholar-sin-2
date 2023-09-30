package lu.mvannuff.radnelac.radnelac.domain.services;

import org.springframework.stereotype.Service;

@Service
public class NumberFormater {

    public String formatNumber(String number){
        return number.replaceAll("\\.", "")
                .replaceAll("\\+", "")
                .replaceAll(" ", "")
                .replaceAll("/", "");
    }
}
