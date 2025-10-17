package service;

import model.*;
import java.util.ArrayList;
import java.util.List;
import impl.*;

public class AssociationService {

    private final DonneurService donneurService = new DonneurService();
    private final ReceveurService receveurService = new ReceveurService();

    public boolean associerDonneurReceveur(Long idDonneur, Long idReceveur) {
        Donneur donneur = donneurService.chercherDonneur(idDonneur);
        Receveur receveur = receveurService.chercherReceveur(idReceveur);

        // Vérification des conditions
        if (donneur == null || receveur == null) return false;
        if (donneur.getStatutDisponibilite() != StatutDisponibilite.DISPONIBLE) return false;
        if (receveur.getStatut() == StatutReceveur.SATISFAIT) return false;
        if (!estCompatible(donneur, receveur)) return false;

        //  Mise à jour de l'association côté Donneur
        donneur.setReceveur(receveur);
        donneur.setStatutDisponibilite(StatutDisponibilite.NON_DISPONIBLE);
        donneurService.majStatut(donneur);

        //  Mise à jour de la vraie liste persistée côté Receveur
        List<Donneur> donneurs = receveur.getDonneurs();
        if (donneurs == null) {
            donneurs = new ArrayList<>();
            receveur.setDonneurs(donneurs);
        }
        donneurs.add(donneur);

        // ✅ Mise à jour du statut du Receveur
        receveur.majStatut();
        receveurService.majStatut(receveur);

        return true;
    }

    private boolean estCompatible(Donneur d, Receveur r) {
        // Exemple simplifié : adapte selon tes règles de compatibilité
        if (r.getGroupeSanguin() == GroupeSanguin.AB_POS) return true;
        if (r.getGroupeSanguin() == GroupeSanguin.O_NEG) return d.getGroupeSanguin() == GroupeSanguin.O_NEG;
        // Ajouter les autres cas selon ton enum GroupeSanguin
        return true;
    }
}
