package com.herotozero.bean;

import com.herotozero.model.CO2Emission;
import com.herotozero.repository.CO2EmissionRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PublicViewBean {

    @Inject
    private CO2EmissionRepository repository;

    private String selectedCountry;
    private Integer selectedYear;
    private CO2Emission emission;

    public String searchCountry() {
        if (selectedCountry != null && !selectedCountry.trim().isEmpty()) {
            if (selectedYear != null) {
                emission = repository.findByCountryAndYear(selectedCountry.trim(), selectedYear);
            } else {
                emission = repository.findLatestByCountry(selectedCountry.trim());
            }
        }
        return null;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public Integer getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(Integer selectedYear) {
        this.selectedYear = selectedYear;
    }

    public CO2Emission getEmission() {
        return emission;
    }

    public void setEmission(CO2Emission emission) {
        this.emission = emission;
    }
}
