package com.herotozero.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "co2_emissions")
public class CO2Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String country;

    @NotNull
    @Column(nullable = false)
    private Integer year;

    @NotNull
    @Column(name = "emissions_kt", nullable = false)
    private Double emissionsKt;

    @Column(name = "approved")
    private Boolean approved = false;

    public CO2Emission() {
    }

    public CO2Emission(String country, Integer year, Double emissionsKt) {
        this.country = country;
        this.year = year;
        this.emissionsKt = emissionsKt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getEmissionsKt() {
        return emissionsKt;
    }

    public void setEmissionsKt(Double emissionsKt) {
        this.emissionsKt = emissionsKt;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
