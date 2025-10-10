package service;



import impl.DonneurDaoImpl;
import model.Donneur;
import model.StatutDisponibilite;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class DonneurService {

    private DonneurDaoImpl donneurDao = new DonneurDaoImpl();

    public void ajouterDonneur(Donneur donneur) {
        if (estEligible(donneur)) {
            donneur.setStatutDisponibilite(StatutDisponibilite.DISPONIBLE);
        } else {
            donneur.setStatutDisponibilite(StatutDisponibilite.NON_ELIGIBLE);
        }
        donneurDao.save(donneur);
    }

    public List<Donneur> listerDonneurs() {
        return donneurDao.findAll();
    }

    public void supprimerDonneur(Long id) {
        donneurDao.delete(id);
    }

    public Donneur chercherDonneur(Long id) {
        return donneurDao.findById(id);
    }

    public void modifierDonneur(Donneur donneur) {
        donneurDao.update(donneur);
    }

    public boolean estEligible(Donneur donneur) {
        int age = Period.between(donneur.getDateNaissance(), LocalDate.now()).getYears();
        return age >= 18 && age <= 65 && donneur.getPoids() >= 50 && !donneur.isContreIndication();
    }

    public List<Donneur> donneursDisponiblesParGroupe(String groupe) {
        return donneurDao.findAvailableByBloodGroup(groupe);
    }
}
