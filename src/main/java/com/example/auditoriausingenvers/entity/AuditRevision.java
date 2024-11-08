package com.example.auditoriausingenvers.entity;

import com.example.auditoriausingenvers.outros.AuditRevisionListener;
import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionTimestamp;

@Data
@Entity
@Table(name="revision_info")
@RevisionEntity(AuditRevisionListener.class)
@AttributeOverrides({
    @AttributeOverride(name = "timestamp", column = @Column(name = "rev_timestamp")),
    @AttributeOverride(name = "id", column = @Column(name = "id"))
})
public class AuditRevision extends DefaultRevisionEntity {
    
    @Column(name="usuario")
    private String usuario;
    
}
