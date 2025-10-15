package service;
import impl.DonneurDaoImpl;
import impl.ReceveurDaoImpl;
import model.*;

import java.util.List;
import java.util.stream.Collectors;

public class MatchingService {

    private DonneurDaoImpl donneurDao = new DonneurDaoImpl();
    private ReceveurDaoImpl receveurDao = new ReceveurDaoImpl();

	
    public List<Donneur> trouverDonneursCompatibles(Receveur receveur) {
        return donneurDao.findAll().stream()
                .filter(d -> d.getStatutDisponibilite() == StatutDisponibilite.DISPONIBLE)
                .filter(d -> estCompatible(d.getGroupeSanguin(), receveur.getGroupeSanguin()))
                .collect(Collectors.toList());
    }

    public boolean estCompatible(GroupeSanguin donneur, GroupeSanguin receveur) {
        switch (receveur) {
            case AB_POS:
                return true; // receveur universel
            case AB_NEG:
                return donneur == GroupeSanguin.AB_NEG || donneur == GroupeSanguin.A_NEG
                        || donneur == GroupeSanguin.B_NEG || donneur == GroupeSanguin.O_NEG;
            case A_POS:
                return donneur == GroupeSanguin.A_POS || donneur == GroupeSanguin.A_NEG
                        || donneur == GroupeSanguin.O_POS || donneur == GroupeSanguin.O_NEG;
            case A_NEG:
                return donneur == GroupeSanguin.A_NEG || donneur == GroupeSanguin.O_NEG;
            case B_POS:
                return donneur == GroupeSanguin.B_POS || donneur == GroupeSanguin.B_NEG
                        || donneur == GroupeSanguin.O_POS || donneur == GroupeSanguin.O_NEG;
            case B_NEG:
                return donneur == GroupeSanguin.B_NEG || donneur == GroupeSanguin.O_NEG;
            case O_POS:
                return donneur == GroupeSanguin.O_POS || donneur == GroupeSanguin.O_NEG;
            case O_NEG:
                return donneur == GroupeSanguin.O_NEG;
            default:
                return false;
        }
    }
    /*
	private MatchingService matchingService = new MatchingService();

	public List<Donneur> getDonneursAssocies() {
	    if (donneurs == null) return java.util.Collections.emptyList();
	    return donneurs.stream()
	            .filter(d -> !d.isContreIndication())
	            .filter(d -> matchingService.estCompatible(d.getGroupeSanguin(), this.groupeSanguin))
	            .collect(Collectors.toList());
	}
*/
    
    
    
    public void associerDonneurReceveur(Donneur donneur, Receveur receveur) {
        if (estCompatible(donneur.getGroupeSanguin(), receveur.getGroupeSanguin())
                && donneur.getStatutDisponibilite() == StatutDisponibilite.DISPONIBLE) {

            // Mettre à jour le statut du donneur
            donneur.setStatutDisponibilite(StatutDisponibilite.NON_DISPONIBLE);
            donneur.setReceveur(receveur);
            donneurDao.update(donneur);

            // Ajouter le donneur au receveur
            List<Donneur> donneursReceveur = receveur.getDonneurs();
            donneursReceveur.add(donneur);
            receveur.setDonneurs(donneursReceveur);

            receveurDao.update(receveur);
        }
    }
    
}
