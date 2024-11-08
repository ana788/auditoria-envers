package com.example.auditoriausingenvers.repository;

import com.example.auditoriausingenvers.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    
}
