package com.fil.rouge.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "session_formations")
public class SessionFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionFormationID;

    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name = "formationID")
    private Formation formation;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @NotBlank
    @Size(max = 255)
    private String lieu;

    private int nombreParticipants;

    @NotBlank
    @Size(max = 255)
    private String statut;

    @NotBlank
    @Size(max = 255)
    private String type;

    public SessionFormation() {
    }

    public SessionFormation(Formation formation, Date dateDebut, Date dateFin, String lieu, int nombreParticipants, String statut, String type) {
        this.formation = formation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.nombreParticipants = nombreParticipants;
        this.statut = statut;
        this.type = type;
    }

    public Long getSessionFormationID() {
        return sessionFormationID;
    }

    public void setSessionFormationID(Long sessionFormationID) {
        this.sessionFormationID = sessionFormationID;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getNombreParticipants() {
        return nombreParticipants;
    }

    public void setNombreParticipants(int nombreParticipants) {
        this.nombreParticipants = nombreParticipants;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
