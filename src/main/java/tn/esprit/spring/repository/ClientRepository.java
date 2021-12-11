package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.detailFacture;

@Repository
public interface ClientRepository extends CrudRepository <Client, Long>{
	
	
	
	
		@Query("SELECT c FROM Client c WHERE c.dateNaissance between :d1 and :d2")
		List<Client> retrieveClientsByDateNaissance(@Param("d1") Date firstDate, @Param("d2") Date secondDate );
		
	
		//@Query("select c.factures.montantFacture from client c where c.categorieClient = :ct and c.factures.dateFacture between :d1 and :d2")
	    //List<Facture> getMontantFactureParCategorieClient(@Param("ct") CategorieClient categorieClient,@Param("d1") Date startDate, @Param("d2") Date endDate); 

		//query syntax error client is not mapped [select c.factures.montantFacture from client c where c.categorieClient = :ct and c.factures.dateFacture between :d1 and :d2]
}

