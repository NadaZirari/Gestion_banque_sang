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

    public boolean estCompatible(String groupeDonneur, String groupeReceveur) {
        switch (groupeReceveur) {
            case "AB+":
                return true; // Receveur universel
            case "O-":
                return groupeDonneur.equals("O-"); // Donneur universel
            case "A+":
                return groupeDonneur.equals("A+") || groupeDonneur.equals("A-") || groupeDonneur.equals("O+") || groupeDonneur.equals("O-");
            case "B+":
                return groupeDonneur.equals("B+") || groupeDonneur.equals("B-") || groupeDonneur.equals("O+") || groupeDonneur.equals("O-");
            case "A-":
                return groupeDonneur.equals("A-") || groupeDonneur.equals("O-");
            case "B-":
                return groupeDonneur.equals("B-") || groupeDonneur.equals("O-");
            case "AB-":
                return groupeDonneur.equals("A-") || groupeDonneur.equals("B-") || groupeDonneur.equals("AB-") || groupeDonneur.equals("O-");
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
