package tn.esprit.spring.repository;


import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;

@Repository
public interface FactureRepository extends CrudRepository<Facture, Long> {
	
	
	@Query("SELECT SUM(c.montantFacture) FROM Facture c where c.active= TRUE ")
	Number GetMontantbro();
	 @Transactional
	@Modifying
	@Query("update Facture c set c.Modepaiement= :Modepaiement where c.idFacture= :idfacture")
	int UpdateModePaiement(@Param("idfacture") Long idfacture,@Param("Modepaiement") String Modepaiement);
	
	
	
	@Query("SELECT c FROM Facture c where c.ClientFacure.idClient= :idClient ")
	List<Facture> getFacturesByClient(@Param("idClient") Long idClient);
	
	
	@Query("SELECT SUM(montantFacture) FROM Facture c WHERE c.ClientFacure.categorieClient= :categorieClient AND c.datefacture BETWEEN :startDate and :endDate")
    float getChiffreAffaireParCategorieClient(@Param("categorieClient") CategorieClient categorieClient,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	
//@Param("d1") Date startDate,@Param("d2") Date endDate
}
