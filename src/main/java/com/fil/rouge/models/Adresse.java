package com.fil.rouge.models;

import jakarta.persistence.*;

@Entity
@Table(name = "adresse")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero; 
    private String adresse;

    @ManyToOne
    @JoinColumn(name = "ville_id")
    private Ville ville;

    public Adresse() {
    }

    public Adresse(String numero, String adresse, Ville ville) {
        this.numero = numero;
        this.adresse = adresse;
        this.ville = ville;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
}
