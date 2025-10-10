package service;



import impl.ReceveurDaoImpl;
import model.Receveur;

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
}
