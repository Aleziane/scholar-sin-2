package lu.mvannuff.radnelac.radnelac.security;

import lombok.RequiredArgsConstructor;
import lu.mvannuff.radnelac.radnelac.domain.entity.Servicer;
import lu.mvannuff.radnelac.radnelac.domain.services.ServicerManagmentService;
import lu.mvannuff.radnelac.radnelac.security.model.ExtendedUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final ServicerManagmentService servicerManagmentService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Servicer s =  servicerManagmentService.findOrCreate(username);
            return new ExtendedUserDetails(s.getMail(),
                    s.getId(),
                    new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_SERVICER"))));
    }
}