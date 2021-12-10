package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	
	
	

	@Query("SELECT c FROM Produit c WHERE c.idProduit= :idd")
	List<Produit> retrieveAllProduitsbyid(@Param("idd") Long idd);

	

	 
	@Query("SELECT c FROM Produit c WHERE c.idProduit= :id2")
	List<Produit> retrieveAllFournisseursbyid(@Param("id2") Long id2);

	
	
	

}
