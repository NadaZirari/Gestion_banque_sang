package model;
import java.util.stream.Collectors;


import jakarta.persistence.*;

import service.MatchingService;

import java.util.List;

@Entity
@Table(name = "receveurs")
public class Receveur {
	public enum Priorite {
	    NORMAL, URGENT, CRITIQUE
	}

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

    @Enumerated(EnumType.STRING)
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    private StatutReceveur statut = StatutReceveur.EN_ATTENTE;

    @OneToMany(mappedBy = "receveur")
    private List<Donneur> donneurs;
    
    public int nombreDePochesNecessaires() {
        if (priorite == null) return 1;
        switch (priorite) {
            case CRITIQUE:
                return 4;
            case URGENT:
                return 3;
            default: // NORMAL
                return 1;
        }
    }
    // Méthode de mise à jour du statut
    public void majStatut() {
        int nbPoches = (donneurs != null) ? donneurs.size() : 0;
        int nbNecessaire;

        if (priorite == null) {
            nbNecessaire = 1; // valeur par défaut
        } else {
            switch (priorite) {
                case CRITIQUE:
                    nbNecessaire = 4;
                    break;
                case URGENT:
                    nbNecessaire = 3;
                    break;
                default: // NORMAL
                    nbNecessaire = 1;
            }
        }

        if (nbPoches >= nbNecessaire) {
            this.statut = StatutReceveur.SATISFAIT;
        } else {
            this.statut = StatutReceveur.EN_ATTENTE;
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

	public Priorite getPriorite() {
	    return priorite;
	}

	public void setPriorite(Priorite priorite) {
	    this.priorite = priorite;
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
    

	private MatchingService matchingService = new MatchingService();

	public List<Donneur> getDonneursAssocies() {
	    if (donneurs == null) return java.util.Collections.emptyList();
	    return donneurs.stream()
	            .filter(d -> !d.isContreIndication())
	            .filter(d -> matchingService.estCompatible(d.getGroupeSanguin(), this.groupeSanguin))
	            .collect(Collectors.toList());
	}



   }

