package com.inforcol.aseguradora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/calculadora")
public class CalculadoraController {

    @GetMapping("/{operacion}/{a}/{b}")
    public double calcular(
            @PathVariable String operacion,
            @PathVariable double a,
            @PathVariable double b) {

        return switch (operacion.toLowerCase()) {
            case "sumar" -> a + b;
            case "restar" -> a - b;
            case "multiplicar" -> a * b;
            case "dividir" -> {
                if (b == 0) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "No se puede dividir entre cero"
                    );
                }

                yield a / b;
            }
            default ->
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Operación no válida: " + operacion
            );
        };
    }
}
