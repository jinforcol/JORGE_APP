package com.inforcol.aseguradora.dto;

public class PremiumResponse {

    private double calculatedPremium;

    public PremiumResponse(double calculatedPremium) {
        this.calculatedPremium = calculatedPremium;
    }

    public double getCalculatedPremium() {
        return calculatedPremium;
    }

    public void setCalculatedPremium(double calculatedPremium) {
        this.calculatedPremium = calculatedPremium;
    }
}