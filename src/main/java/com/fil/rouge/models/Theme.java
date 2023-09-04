package com.fil.rouge.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "themes")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long themeID;

    @NotBlank
    @Size(max = 255)
    private String nom;

    @Size(max = 1000)
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    private List<SousTheme> sousThemes;

    public Theme() {
    }

    public Theme(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Long getThemeID() {
        return themeID;
    }

    public void setThemeID(Long themeID) {
        this.themeID = themeID;
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

    public List<SousTheme> getSousThemes() {
        return sousThemes;
    }

    public void setSousThemes(List<SousTheme> sousThemes) {
        this.sousThemes = sousThemes;
    }
}
