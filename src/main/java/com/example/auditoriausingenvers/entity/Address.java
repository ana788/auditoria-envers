package com.example.auditoriausingenvers.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Data
@Entity
@Audited
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    private String city;
    private String country;
    private String stateCode;
    private String streetAddress;
    private String zipCode;
    
    /*@ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;*/
    @ManyToOne
    @JsonBackReference
    private Customer customer;

    
}
