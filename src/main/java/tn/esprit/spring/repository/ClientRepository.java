package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

	void findByDateNaissanceGreaterThan(Date date);
	
	
	
	@Query("SELECT c FROM Client c WHERE c.dateNaissance BETWEEN :d1 and :d2")
	List<Client> retrieveClientsByDateNaissance(@Param("d1") Date d1 , @Param("d2") Date d2);
	
	
//	@Query(value = "SELECT c FROM Client c WHERE c.dateNaissance BETWEEN '01/01/1995' and '31/12/1995'" ,nativeQuery = true)
//	List<Client> retrieveClientsByDateNaisance();

}
