package lu.mvannuff.radnelac.radnelac.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class ExtendedUserDetails extends User {

    private final Long userId;

    public ExtendedUserDetails(String username,  Long userId, Collection<? extends GrantedAuthority> authorities) {
        super(username, "", authorities);
        this.userId = userId;
    }


}
