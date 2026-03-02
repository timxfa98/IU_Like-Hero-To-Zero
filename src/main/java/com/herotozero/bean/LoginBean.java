package com.herotozero.bean;

import com.herotozero.model.Scientist;
import com.herotozero.repository.ScientistRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private ScientistRepository scientistRepository;

    private String username;
    private String password;
    private boolean loggedIn = false;

    public String login() {
        Scientist scientist = scientistRepository.findByUsername(username);
        
        if (scientist != null && scientist.getPassword().equals(password)) {
            loggedIn = true;
            return "admin?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login fehlgeschlagen", "Ungültige Anmeldedaten"));
            return null;
        }
    }

    public String logout() {
        loggedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
