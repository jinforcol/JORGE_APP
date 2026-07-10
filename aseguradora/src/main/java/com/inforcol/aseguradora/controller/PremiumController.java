package com.inforcol.aseguradora.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.aseguradora.dto.PremiumRequest;
import com.inforcol.aseguradora.dto.PremiumResponse;
import com.inforcol.aseguradora.service.PremiumService;

@RestController
@RequestMapping("/premium")
@CrossOrigin(origins = "http://localhost:4200")
public class PremiumController {

    private final PremiumService premiumService;

    public PremiumController(PremiumService premiumService) {
        this.premiumService = premiumService;
    }

    @PostMapping("/calculate")
    public PremiumResponse calculatePremium(
            @RequestBody PremiumRequest request) {

        return premiumService.calculatePremium(request);
    }
}
