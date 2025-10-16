package dao;


import model.Donneur;

import java.util.List;



public interface DonneurDao {
	
    void save(Donneur donneur);
    List<Donneur> findAll();
    Donneur findById(Long id);
    void delete(Long id);
    void update(Donneur donneur);
    List<Donneur> findAvailable(); 
    List<Donneur> findAvailableByBloodGroup(String groupeSanguin);


}
