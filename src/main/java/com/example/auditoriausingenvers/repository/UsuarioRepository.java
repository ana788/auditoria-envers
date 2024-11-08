
package com.example.auditoriausingenvers.repository;

import com.example.auditoriausingenvers.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    Optional<Usuario> findUserByEmail(String emailAddress);
}
