package com.inforcol.aseguradora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inforcol.aseguradora.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    
}
