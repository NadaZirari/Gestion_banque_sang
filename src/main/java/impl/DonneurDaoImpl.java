package impl;
import dao.DonneurDao;
import model.Donneur;
import jakarta.persistence.*;
import java.util.List;


public class DonneurDaoImpl implements DonneurDao {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("BloodbankPU");
    private EntityManager em = emf.createEntityManager();

    @Override
    public void save(Donneur donneur) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(donneur);
        tx.commit();
    }

    @Override
    public Donneur findById(Long id) {
        return em.find(Donneur.class, id);
    }

    @Override
    public List<Donneur> findAll() {
        return em.createQuery("SELECT d FROM Donneur d", Donneur.class).getResultList();
    }

    @Override
    public void update(Donneur donneur) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(donneur);
        tx.commit();
    }

    @Override
    public void delete(Long id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Donneur donneur = em.find(Donneur.class, id);
        if (donneur != null) {
            em.remove(donneur);
        }
        tx.commit();
    }

    @Override
    public List<Donneur> findAvailableByBloodGroup(String bloodGroup) {
        return em.createQuery(
                "SELECT d FROM Donneur d WHERE d.groupeSanguin = :groupe AND d.statutDisponibilite = 'DISPONIBLE'",
                Donneur.class)
                .setParameter("groupe", bloodGroup)
                .getResultList();
    }
	
	

}
