package com.policy.models;

public class Plan {
    private String name;
    private String tagline;
    private int priceInRupees;
    private String medicalCover;
    private String provider = "ICICI Lombard";

    public Plan(String name, String tagline, int priceInRupees, String medicalCover) {
        this.name = name;
        this.tagline = tagline;
        this.priceInRupees = priceInRupees;
        this.medicalCover = medicalCover;
    }

    public String getName()          { return name; }
    public String getTagline()       { return tagline; }
    public int    getPriceInRupees() { return priceInRupees; }
    public String getMedicalCover()  { return medicalCover; }
    public String getProvider()      { return provider; }

    @Override
    public String toString() {
        return String.format("[%s | %s | ₹%d | Cover: %s | Provider: %s]",
                name, tagline, priceInRupees, medicalCover, provider);
    }
}
