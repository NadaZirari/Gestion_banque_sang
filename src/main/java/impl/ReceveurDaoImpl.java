package impl;

//package dao.impl;

import dao.ReceveurDao;

import model.Receveur;
import jakarta.persistence.*;
import java.util.List;

public class ReceveurDaoImpl implements ReceveurDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("BloodbankPU");
    private EntityManager em = emf.createEntityManager();

    @Override
    public void save(Receveur receveur) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(receveur);
        tx.commit();
    }

    @Override
    public Receveur findById(Long id) {
        return em.find(Receveur.class, id);
    }

    @Override
    public List<Receveur> findAll() {
        return em.createQuery("SELECT r FROM Receveur r", Receveur.class).getResultList();
    }

    @Override
    public void update (Receveur receveur) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(receveur);
        tx.commit();
    }

    @Override
    public void delete(Long id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Receveur receveur = em.find(Receveur.class, id);
        if (receveur != null) {
            em.remove(receveur);
        }
        tx.commit();
    }

    @Override
    public List<Receveur> findByPriorite() {
        return em.createQuery(
                "SELECT r FROM Receveur r ORDER BY r.situationMedicale DESC",
                Receveur.class).getResultList();
    }
}
