package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.model.User;
import com.example.paris_janitor_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
*/import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService {/*implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        return (UserDetails) new User(
                //user.getId(),
                user.get().getUsername(),
                user.get().getEmail(),
                user.get().getPassword(),
                user.get().getCreatedAt(),
                getGrantedAuthorities(user.get().getRole()).get(0).toString(),
                user.get().isActive()
        );
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        return authorities;
    }*/
}
