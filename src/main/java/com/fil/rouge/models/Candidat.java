package com.fil.rouge.models;

import jakarta.persistence.*;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "candidat")
public class Candidat extends User {
    private String identifiantPoleEmploi;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "session_id") 
    private Session session;

    @Column(name = "validate")
    private boolean validate = false;
    public Candidat() {
    }

    public Candidat(String identifiantPoleEmploi, Session session) {
        this.identifiantPoleEmploi = identifiantPoleEmploi;
        this.session = session;
    }

    public String getIdentifiantPoleEmploi() {
        return identifiantPoleEmploi;
    }

    public void setIdentifiantPoleEmploi(String identifiantPoleEmploi) {
        this.identifiantPoleEmploi = identifiantPoleEmploi;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean getValidate() {
        return validate;
    }


    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidat candidat = (Candidat) o;
        return Objects.equals(identifiantPoleEmploi, candidat.identifiantPoleEmploi) &&
                Objects.equals(session, candidat.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiantPoleEmploi, session);
    }
}
