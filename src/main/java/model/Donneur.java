package model;

import java.time.LocalDate;

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

    @Enumerated(EnumType.STRING)
    private GroupeSanguin groupeSanguin;

    @Enumerated(EnumType.STRING)
    private StatutDisponibilite statut;

    @ManyToOne
    @JoinColumn(name = "receveur_id")
    private Receveur receveur;

    public Donneur() {}

    public void determinerEligibilite() {
        int age = LocalDate.now().getYear() - dateNaissance.getYear();
        if (age < 18 || age > 65 || poids < 50) {
            this.statut = StatutDisponibilite.NON_ELIGIBLE;
        } else {
            this.statut = StatutDisponibilite.DISPONIBLE;
        }
    }

    // apres j vais remplir g +S
    
}
