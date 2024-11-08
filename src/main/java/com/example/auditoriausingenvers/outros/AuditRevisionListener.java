package com.example.auditoriausingenvers.outros;

import com.example.auditoriausingenvers.entity.AuditRevision;
import com.example.auditoriausingenvers.entity.Usuario;
import java.util.Optional;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuditRevisionListener implements RevisionListener {
    
    @Override
    public void newRevision(Object revisionEntity) {

        String currentUser = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(Usuario.class::cast)
                .map(Usuario::getUsername)
                .orElse("Unknown-User");

        AuditRevision audit = (AuditRevision) revisionEntity;
        audit.setUsuario(currentUser);

    }
}
