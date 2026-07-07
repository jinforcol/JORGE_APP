package com.inforcol.aseguradora.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HolaController {

    //hola  metodo
    @GetMapping("/saludo")    
    public String hola() {
        return "Hola inforcol";
    }
}
