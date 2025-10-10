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
             return true; 
         case O_NEG:
             return donneur == GroupeSanguin.O_NEG; 
         case A_POS:
             return donneur == GroupeSanguin.A_POS || donneur == GroupeSanguin.A_NEG || donneur == GroupeSanguin.O_POS || donneur == GroupeSanguin.O_NEG;
         
         default:
             return false;
     }
    }

    public void associerDonneurReceveur(Donneur donneur, Receveur receveur) {
        if (estCompatible(donneur.getGroupeSanguin(), receveur.getGroupeSanguin())
                && donneur.getStatutDisponibilite() == StatutDisponibilite.DISPONIBLE) {

            donneur.setStatutDisponibilite(StatutDisponibilite.NON_DISPONIBLE);
            donneur.setReceveur(receveur);
            donneurDao.update(donneur);

            receveur.getDonneursAssocies().add(donneur);
            receveurDao.update(receveur);
        }
    }
}
