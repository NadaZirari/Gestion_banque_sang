package service;



import impl.ReceveurDaoImpl;
import model.Receveur;
import model.StatutReceveur;

import java.util.List;

public class ReceveurService {

    private ReceveurDaoImpl receveurDao = new ReceveurDaoImpl();

    public void ajouterReceveur(Receveur receveur) {
        receveurDao.save(receveur);
    }

    public List<Receveur> listerReceveurs() {
        return receveurDao.findByPriorite();
    }

    public void supprimerReceveur(Long id) {
        receveurDao.delete(id);
    }

    public Receveur chercherReceveur(Long id) {
        return receveurDao.findById(id);
    }

    public void modifierReceveur(Receveur receveur) {
        receveurDao.update(receveur);
    }
    
    public List<Receveur> listerNonSatisfaits() {
        return receveurDao.findNonSatisfaits();
    }

    
    public void majStatut(Receveur receveur) {
        int nbPoches = (receveur.getDonneurs() != null) ? receveur.getDonneurs().size() : 0;
        int nbNecessaire;

        switch (receveur.getPriorite()) {
            case CRITIQUE: nbNecessaire = 4; break;
            case URGENT: nbNecessaire = 3; break;
            default: nbNecessaire = 1;
        }

        if (nbPoches >= nbNecessaire) {
            receveur.setStatut(StatutReceveur.SATISFAIT);
        } else {
            receveur.setStatut(StatutReceveur.EN_ATTENTE);
        }

        // Mise à jour en base
        receveurDao.update(receveur);
    }

}
