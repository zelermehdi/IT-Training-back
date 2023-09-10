package com.fil.rouge.models;


import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private String raisonSociale;
    
    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;
    
    private String telephone;
    private String responsable;
    private int effectif;

    public Entreprise() {
    }

    public Entreprise(String nom, String raisonSociale, Adresse adresse, String telephone, String responsable, int effectif) {
        this.nom = nom;
        this.raisonSociale = raisonSociale;
        this.adresse = adresse;
        this.telephone = telephone;
        this.responsable = responsable;
        this.effectif = effectif;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entreprise entreprise = (Entreprise) o;
        return effectif == entreprise.effectif &&
                Objects.equals(id, entreprise.id) &&
                Objects.equals(nom, entreprise.nom) &&
                Objects.equals(raisonSociale, entreprise.raisonSociale) &&
                Objects.equals(adresse, entreprise.adresse) &&
                Objects.equals(telephone, entreprise.telephone) &&
                Objects.equals(responsable, entreprise.responsable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, raisonSociale, adresse, telephone, responsable, effectif);
    }
}
