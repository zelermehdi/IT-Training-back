package com.fil.rouge.models;


import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;
    
    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;
    
    private double noteAccueil;
    private double notePédagogie;
    private double noteSupport;
    private double noteDisponibilité;
    private double noteExpertise;

    public Evaluation() {
    }

    public Evaluation(Formateur formateur, Candidat candidat, double noteAccueil, double notePédagogie, double noteSupport, double noteDisponibilité, double noteExpertise) {
        this.formateur = formateur;
        this.candidat = candidat;
        this.noteAccueil = noteAccueil;
        this.notePédagogie = notePédagogie;
        this.noteSupport = noteSupport;
        this.noteDisponibilité = noteDisponibilité;
        this.noteExpertise = noteExpertise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public double getNoteAccueil() {
        return noteAccueil;
    }

    public void setNoteAccueil(double noteAccueil) {
        this.noteAccueil = noteAccueil;
    }

    public double getNotePédagogie() {
        return notePédagogie;
    }

    public void setNotePédagogie(double notePédagogie) {
        this.notePédagogie = notePédagogie;
    }

    public double getNoteSupport() {
        return noteSupport;
    }

    public void setNoteSupport(double noteSupport) {
        this.noteSupport = noteSupport;
    }

    public double getNoteDisponibilité() {
        return noteDisponibilité;
    }

    public void setNoteDisponibilité(double noteDisponibilité) {
        this.noteDisponibilité = noteDisponibilité;
    }

    public double getNoteExpertise() {
        return noteExpertise;
    }

    public void setNoteExpertise(double noteExpertise) {
        this.noteExpertise = noteExpertise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluation evaluation = (Evaluation) o;
        return Double.compare(evaluation.noteAccueil, noteAccueil) == 0 &&
                Double.compare(evaluation.notePédagogie, notePédagogie) == 0 &&
                Double.compare(evaluation.noteSupport, noteSupport) == 0 &&
                Double.compare(evaluation.noteDisponibilité, noteDisponibilité) == 0 &&
                Double.compare(evaluation.noteExpertise, noteExpertise) == 0 &&
                Objects.equals(id, evaluation.id) &&
                Objects.equals(formateur, evaluation.formateur) &&
                Objects.equals(candidat, evaluation.candidat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formateur, candidat, noteAccueil, notePédagogie, noteSupport, noteDisponibilité, noteExpertise);
    }
}
