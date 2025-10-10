package model;
import java.util.stream.Collectors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "receveurs")
public class Receveur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String sexe;

    @Enumerated(EnumType.STRING)
    private GroupeSanguin groupeSanguin;

    private String situation; 

    @Enumerated(EnumType.STRING)
    private StatutReceveur statut = StatutReceveur.EN_ATTENTE;

    @OneToMany(mappedBy = "receveur")
    private List<Donneur> donneurs;

    // Méthode de mise à jour du statut
    public void majStatut() {
        int nbPoches = donneurs != null ? donneurs.size() : 0;
        if ((situation.equals("NORMAL") && nbPoches >= 1)
         || (situation.equals("URGENT") && nbPoches >= 3)
         || (situation.equals("CRITIQUE") && nbPoches >= 4)) {
            this.statut = StatutReceveur.TRAITE;
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

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public GroupeSanguin getGroupeSanguin() {
		return groupeSanguin;
	}

	public void setGroupeSanguin(GroupeSanguin groupeSanguin) {
		this.groupeSanguin = groupeSanguin;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public StatutReceveur getStatut() {
		return statut;
	}

	public void setStatut(StatutReceveur statut) {
		this.statut = statut;
	}

	public List<Donneur> getDonneurs() {
		return donneurs;
	}

	public void setDonneurs(List<Donneur> donneurs) {
		this.donneurs = donneurs;
	}
    

	public List<Donneur> getDonneursAssocies() {
	    if (donneurs == null) return List.of(); 
	    return donneurs.stream()
	                   .filter(d -> !d.isContreIndication()) 
	                   .filter(d -> d.getGroupeSanguin() == this.groupeSanguin) 
	                   .collect(Collectors.toList());
	}


   }

