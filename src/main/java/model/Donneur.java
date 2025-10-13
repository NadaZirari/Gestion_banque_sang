package model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.*;

@Entity
@Table(name = "donneurs")
public class Donneur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private LocalDate dateNaissance;
    private double poids;
    private String sexe;

    
    
    // Contre-indications médicales
    private boolean hepatiteB;
    private boolean hepatiteC;
    private boolean vih;
    private boolean diabeteInsulinoDependant;
    private boolean grossesse;
    private boolean allaitement;
    
    @Enumerated(EnumType.STRING)
    private GroupeSanguin groupeSanguin;

    @Enumerated(EnumType.STRING)
    private StatutDisponibilite statut;

    @ManyToOne
    @JoinColumn(name = "receveur_id")
    private Receveur receveur;

    public Donneur() {}

    public void determinerEligibilite() {
        if (isContreIndication()) {
            this.statut = StatutDisponibilite.NON_ELIGIBLE;
        } else if (receveur != null) {
            this.statut = StatutDisponibilite.NON_DISPONIBLE;
        } else {
            this.statut = StatutDisponibilite.DISPONIBLE;
        }
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
		determinerEligibilite();
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
		determinerEligibilite();
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
		determinerEligibilite();
	}

	public GroupeSanguin getGroupeSanguin() {
		return groupeSanguin;
	}

	public void setGroupeSanguin(GroupeSanguin groupeSanguin) {
		this.groupeSanguin = groupeSanguin;
	}

	public StatutDisponibilite getStatutDisponibilite() {
		return statut;
	}

	public void setStatutDisponibilite(StatutDisponibilite statut) {
		this.statut = statut;
	}

	public Receveur getReceveur() {
		return receveur;
	}

	public void setReceveur(Receveur receveur) {
		this.receveur = receveur;
	}

	 public boolean isContreIndication() {
	        
	        if (dateNaissance == null) return true; 
	        int age = Period.between(dateNaissance, LocalDate.now()).getYears();
	        if (age < 18 || age > 65) return true; 

	        
	        if (poids < 50) return true;

	        
	        if (hepatiteB || hepatiteC || vih || diabeteInsulinoDependant) return true;

	       
	        if ("F".equalsIgnoreCase(sexe) && (grossesse || allaitement)) return true;

	        
	        return false;
	    }

}
    

