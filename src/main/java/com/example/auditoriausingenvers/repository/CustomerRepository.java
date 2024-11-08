package com.example.auditoriausingenvers.repository;

import com.example.auditoriausingenvers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
}
