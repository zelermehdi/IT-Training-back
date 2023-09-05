package com.fil.rouge.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Importez cette annotation

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sousthemes")
public class SousTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sousThemeID;

    @NotBlank
    @Size(max = 255)
    private String nom;

    @Size(max = 1000)
    private String description;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "themeID")
    @JsonIgnoreProperties("sousThemes") 
    private Theme theme;

    public SousTheme() {
    }

    public SousTheme(String nom, String description, Theme theme) {
        this.nom = nom;
        this.description = description;
        this.theme = theme;
    }

    public Long getSousThemeID() {
        return sousThemeID;
    }

    public void setSousThemeID(Long sousThemeID) {
        this.sousThemeID = sousThemeID;
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

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
