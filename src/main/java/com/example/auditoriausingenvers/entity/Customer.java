package com.example.auditoriausingenvers.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Data
@Entity
@Audited
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    private String emailAddress;
    private String firstName;
    private String lastName;
    
    @NotAudited
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    private List<Address> addresses;
    
}
