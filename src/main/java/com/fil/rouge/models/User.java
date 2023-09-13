package com.fil.rouge.models;



import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User extends AppUser implements Serializable {
    protected String nom;
    protected String prenom;
    protected String telephone;

    
    public User(String nom, String prenom, String telephone, int adresseId, String securiteSociale) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.adresseId = adresseId;
		this.securiteSociale = securiteSociale;
	}

	@Column(name = "adresse_id")
    private int adresseId;

    @Column(name = "securite_sociale")
    private String securiteSociale;

    public User() {
        super();
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(int adresseId) {
        this.adresseId = adresseId;
    }

    public String getSecuriteSociale() {
        return securiteSociale;
    }

    public void setSecuriteSociale(String securiteSociale) {
        this.securiteSociale = securiteSociale;
    }
}
