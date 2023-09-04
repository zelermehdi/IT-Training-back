package com.fil.rouge.models; 

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "formations")
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formationId;

    @NotBlank
    @Size(max = 255)
    @Column(length = 255)
    private String nom;

    @Size(max = 1000)
    @Column(length = 1000)
    private String description;

    private double prix;

    private int duree;

    @Size(max = 1000)
    @Column(length = 1000)
    private String prerequis;

    @JsonManagedReference 
    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SessionFormation> sessions;

    public Formation() {
    }

    public Formation(String nom, String description, double prix, int duree, String prerequis) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.duree = duree;
        this.prerequis = prerequis;
    }

    public Long getFormationId() {
        return formationId;
    }

    public void setFormationId(Long formationId) {
        this.formationId = formationId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getPrerequis() {
        return prerequis;
    }

    public void setPrerequis(String prerequis) {
        this.prerequis = prerequis;
    }

    public List<SessionFormation> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionFormation> sessions) {
        this.sessions = sessions;
    }
}