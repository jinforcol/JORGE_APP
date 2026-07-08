package com.inforcol.aseguradora.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.aseguradora.entity.Policy;
import com.inforcol.aseguradora.repository.PolicyRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PolicyController  {

    //primera forma de inyectar dependencias
    private final PolicyRepository repo;
    
    public PolicyController(PolicyRepository repo) {
        this.repo = repo;
    }

    //segunda forma de inyectar dependencias
    // @Autowired
    // private PolicyRepository repo;

    @PostMapping
    public Policy createPolicy( @RequestBody Policy policy) {
        return repo.save(policy);
    }
    //CREAR METODOS CRUD
    @GetMapping("/policies")
    public Iterable<Policy> getAllPolicies() {
        return repo.findAll();
    }

    @GetMapping("/policies/{id}")
    public Policy getPolicyById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    
}
