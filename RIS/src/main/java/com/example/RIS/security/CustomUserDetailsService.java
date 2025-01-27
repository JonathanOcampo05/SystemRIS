package com.example.RIS.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        if ("usuario".equals(username)) {
            return User.builder()
                    .username("usuario")
                    .password(new BCryptPasswordEncoder().encode("contraseña"))
                    .roles("USER")
                    .build();
        }

        throw new UsernameNotFoundException("Usuario no encontrado");
    }
}
