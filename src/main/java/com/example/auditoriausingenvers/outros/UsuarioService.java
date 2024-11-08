
package com.example.auditoriausingenvers.outros;

import com.example.auditoriausingenvers.entity.Usuario;
import com.example.auditoriausingenvers.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> userByEmail = userRepository.findUserByEmail(username);
        return userByEmail.orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
