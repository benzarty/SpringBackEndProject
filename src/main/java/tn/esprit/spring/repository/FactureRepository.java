package tn.esprit.spring.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;

@Repository
public interface FactureRepository extends CrudRepository<Facture, Long> {
	
	
	@Query("SELECT SUM(c.montantFacture) FROM Facture c where c.active= TRUE ")
	Number GetMontantbro();
	
	@Query("SELECT c FROM Facture c where c.ClientFacure.idClient= :idClient ")
	List<Facture> getFacturesByClient(@Param("idClient") Long idClient);
	
	
	
	

}
