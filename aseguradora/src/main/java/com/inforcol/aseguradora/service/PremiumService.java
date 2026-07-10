package com.inforcol.aseguradora.service;

import org.springframework.stereotype.Service;

import com.inforcol.aseguradora.dto.PremiumRequest;
import com.inforcol.aseguradora.dto.PremiumResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PremiumService {

    public PremiumResponse calculatePremium(PremiumRequest request) {
        if (request.getInsuredAmount() <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El monto asegurado debe ser mayor que cero"
            );
        }
        
        if (request.getAge() <= 18) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La edad debe ser mayor que Dieciocho anios"
            );
        }
        double coveragePercentage;

        switch (request.getCoverageType().toLowerCase()) {
            case "basic" -> coveragePercentage = 0.01;

            case "standard" -> coveragePercentage = 0.015;

            case "premium" -> coveragePercentage = 0.02;

            default -> throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Tipo de cobertura no valido"
            );
        }

        double calculatedPremium =
            request.getInsuredAmount() * coveragePercentage;

        return new PremiumResponse(calculatedPremium);
    }
}