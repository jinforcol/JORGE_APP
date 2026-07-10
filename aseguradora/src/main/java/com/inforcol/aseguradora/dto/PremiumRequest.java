package com.inforcol.aseguradora.dto;

public class PremiumRequest {
    
    private double insuredAmount;
    private int age;
    private String coverageType;

    public double getInsuredAmount() {
        return insuredAmount;
    }

    public void setInsuredAmount(double insuredAmount) {
        this.insuredAmount = insuredAmount;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }
}
