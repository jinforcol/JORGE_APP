package com.inforcol.aseguradora.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/calculadora")
public class CalculadoraController {
    
    @GetMapping("/{a}/{b}")
    public double sumar(@PathVariable double a, @PathVariable double b) {
        return a + b;
    }
}
