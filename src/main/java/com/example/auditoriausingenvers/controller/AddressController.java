package com.example.auditoriausingenvers.controller;

import com.example.auditoriausingenvers.entity.Address;
import com.example.auditoriausingenvers.repository.AddressRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/address")
public class AddressController {
    
    @Autowired
    AddressRepository addressRepository;
    
    @GetMapping(value="/listar")
    public List<Address> list(){
        return addressRepository.findAll();
    }
    
    @GetMapping()
    public Optional<Address> mostrar(@RequestBody Address address){
        return addressRepository.findById(address.getId());
    }
    
    @PostMapping()
    public void salvar(@RequestBody Address address){
        System.out.println("Salvando address");
        addressRepository.save(address);
    }
    
    @PutMapping
    public void atualizar(@RequestBody Address address){
        System.out.println("Atualizando address");
        addressRepository.save(address);
    }
    
    @DeleteMapping
    public void deletar(@RequestBody Address address){
        System.out.println("Deletando address");
        addressRepository.delete(address);
    }
}
