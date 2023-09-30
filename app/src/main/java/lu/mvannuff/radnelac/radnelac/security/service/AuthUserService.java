package lu.mvannuff.radnelac.radnelac.security.service;

import lu.mvannuff.radnelac.radnelac.security.model.ExtendedUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    public ExtendedUserDetails getAuthenticatedUser(){
       return (ExtendedUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
