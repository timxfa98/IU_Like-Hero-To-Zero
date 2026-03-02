package com.herotozero.bean;

import com.herotozero.model.CO2Emission;
import com.herotozero.repository.CO2EmissionRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AdminBean {

    @Inject
    private CO2EmissionRepository repository;

    private CO2Emission newEmission = new CO2Emission();
    private boolean showSuccessMessage = false;

    public String addEmission() {
        try {
            newEmission.setApproved(false);
            repository.save(newEmission);
            
            showSuccessMessage = true;
            newEmission = new CO2Emission();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Daten konnten nicht gespeichert werden: " + e.getMessage()));
            e.printStackTrace();
        }
        return null;
    }

    public List<CO2Emission> getPendingEmissions() {
        return repository.findPending();
    }

    public String approveEmission(Long id) {
        try {
            CO2Emission emission = repository.findById(id);
            if (emission != null) {
                List<CO2Emission> allExisting = repository.findAllByCountryAndYear(emission.getCountry(), emission.getYear());
                for (CO2Emission existing : allExisting) {
                    if (!existing.getId().equals(id)) {
                        repository.delete(existing);
                    }
                }
                
                emission.setApproved(true);
                repository.save(emission);
                
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Daten wurden freigegeben"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Freigabe fehlgeschlagen: " + e.getMessage()));
        }
        return null;
    }

    public String deleteEmission(Long id) {
        try {
            CO2Emission emission = repository.findById(id);
            if (emission != null) {
                repository.delete(emission);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Daten wurden gelöscht"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Löschen fehlgeschlagen: " + e.getMessage()));
        }
        return null;
    }

    public CO2Emission getNewEmission() {
        return newEmission;
    }

    public void setNewEmission(CO2Emission newEmission) {
        this.newEmission = newEmission;
    }

    public boolean isShowSuccessMessage() {
        return showSuccessMessage;
    }

    public void setShowSuccessMessage(boolean showSuccessMessage) {
        this.showSuccessMessage = showSuccessMessage;
    }
}
