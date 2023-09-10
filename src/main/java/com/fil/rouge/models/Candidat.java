package com.fil.rouge.models;


import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidat")
public class Candidat extends User {
    private String identifiantPoleEmploi;

    @ManyToOne
    @JoinColumn(name = "centre_id")
    private Centre centre;

    public Candidat() {
    }

    public Candidat(String identifiantPoleEmploi, Centre centre) {
        this.identifiantPoleEmploi = identifiantPoleEmploi;
        this.centre = centre;
    }

    public String getIdentifiantPoleEmploi() {
        return identifiantPoleEmploi;
    }

    public void setIdentifiantPoleEmploi(String identifiantPoleEmploi) {
        this.identifiantPoleEmploi = identifiantPoleEmploi;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidat candidat = (Candidat) o;
        return Objects.equals(identifiantPoleEmploi, candidat.identifiantPoleEmploi) &&
                Objects.equals(centre, candidat.centre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiantPoleEmploi, centre);
    }
}
