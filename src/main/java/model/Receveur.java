package model;

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
            this.statut = StatutReceveur.SATISFAIT;
        }
    }

   }

