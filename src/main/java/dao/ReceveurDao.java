package dao;

import model.Receveur;
import java.util.List;


public interface ReceveurDao {
    void save(Receveur receveur);
    List<Receveur> findAll();
    Receveur findById(Long id);
    void delete(Long id);
    void update(Receveur receveur);
    List<Receveur> findByPriorite();
}

