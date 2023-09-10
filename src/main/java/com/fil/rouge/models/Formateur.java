package com.fil.rouge.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "formateur")
public class Formateur extends User {
    private String siret;

    @ManyToMany
    @JoinTable(
        name = "formateur_centre",
        joinColumns = @JoinColumn(name = "formateur_id"),
        inverseJoinColumns = @JoinColumn(name = "centre_id")
    )
    private List<Centre> centres;

    public Formateur() {
    }

    public Formateur(String siret, List<Centre> centres) {
        this.siret = siret;
        this.centres = centres;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public List<Centre> getCentres() {
        return centres;
    }

    public void setCentres(List<Centre> centres) {
        this.centres = centres;
    }
}
