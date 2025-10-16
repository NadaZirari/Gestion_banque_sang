package impl;

import dao.DonneurDao;
import model.Donneur;
import jakarta.persistence.*;
import java.util.List;

public class DonneurDaoImpl implements DonneurDao {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BloodbankPU");

    private EntityManager getEm() {
        return emf.createEntityManager();
    }

    @Override
    public void save(Donneur donneur) {
        EntityManager em = getEm();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(donneur);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Donneur findById(Long id) {
        EntityManager em = getEm();
        try {
            return em.find(Donneur.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Donneur> findAll() {
        EntityManager em = getEm();
        try {
            return em.createQuery("SELECT d FROM Donneur d", Donneur.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Donneur donneur) {
        EntityManager em = getEm();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(donneur);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEm();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Donneur d = em.find(Donneur.class, id);
            if (d != null) em.remove(d);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Donneur> findAvailableByBloodGroup(String group) {
        EntityManager em = getEm();
        try {
            return em.createQuery(
                    "SELECT d FROM Donneur d WHERE d.groupeSanguin = :g AND d.statutDisponibilite = :s",
                    Donneur.class)
                    .setParameter("g", Enum.valueOf(model.GroupeSanguin.class, mapToEnumName(group)))
                    .setParameter("s", model.StatutDisponibilite.DISPONIBLE)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Donneur> findAvailable() {
        EntityManager em = getEm();
        try {
            return em.createQuery("SELECT d FROM Donneur d WHERE d.statutDisponibilite = :s", Donneur.class)
                    .setParameter("s", model.StatutDisponibilite.DISPONIBLE)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // helper: map "O+" -> O_POS etc.
    private String mapToEnumName(String display) {
        if (display == null) return null;
        switch (display) {
            case "O+": return "O_POS";
            case "O-": return "O_NEG";
            case "A+": return "A_POS";
            case "A-": return "A_NEG";
            case "B+": return "B_POS";
            case "B-": return "B_NEG";
            case "AB+": return "AB_POS";
            case "AB-": return "AB_NEG";
            default: return display;
        }
    }
}
