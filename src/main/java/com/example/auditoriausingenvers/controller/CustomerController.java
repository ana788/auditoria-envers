package com.example.auditoriausingenvers.controller;

import com.example.auditoriausingenvers.entity.Customer;
import com.example.auditoriausingenvers.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/customer")
public class CustomerController {
    
    @Autowired
    CustomerRepository customerRepository;
    
    @GetMapping(value="/listar")
    public List<Customer> list(){
        return customerRepository.findAll();
    }
    
    @GetMapping()
    public Optional<Customer> mostrar(@RequestBody Customer customer){
        return customerRepository.findById(customer.getId());
    }
    
    @PostMapping()
    public void salvar(@RequestBody Customer customer){
        System.out.println("Salvando customer:" + customer.getFirstName() + " " + customer.getLastName());
        customerRepository.save(customer);
    }
    
    @PutMapping
    public void atualizar(@RequestBody Customer customer){
        System.out.println("Atualizando customer:" + customer.getFirstName() + " " + customer.getLastName());
        customerRepository.save(customer);
    }
    
    @DeleteMapping
    public void deletar(@RequestBody Customer customer){
        System.out.println("Deletando customer:" + customer.getFirstName() + " " + customer.getLastName());
        customerRepository.delete(customer);
    }
}
