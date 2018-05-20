package pl.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.model.User;
import pl.model.UserRoles;
import pl.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

public class UserImpelment implements UserDetailsService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       User user=userRepository.findFirstByEmail(s);
        org.springframework.security.core.userdetails.User u=new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                passwordEncoder.encode(user.getHaslo()),
                convert(user.getUserRolesSet())

        );
        return u;
    }
    private Set<GrantedAuthority> convert(Set<UserRoles> userRolesSet) {
        Set<GrantedAuthority> all=new HashSet<>();
        for(UserRoles u:userRolesSet)
        {
            all.add(new SimpleGrantedAuthority(u.getRole()));
        }
        return all;
    }


}
