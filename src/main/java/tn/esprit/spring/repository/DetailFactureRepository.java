package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.detailFacture;


@Repository
public interface DetailFactureRepository extends JpaRepository<detailFacture, Long> {
	
	
	
	@Query("SELECT c FROM detailFacture c where c.facturechildren.idFacture= :idFacture ")
	List<detailFacture> getDetailFactureByFacture(@Param("idFacture") Long idFacture);
	
	
	
	
	@Query("SELECT SUM(prixtotal) FROM detailFacture c where (c.Totheparentdetailfacture.idProduit= :idProduit AND (c.facturechildren.datefacture BETWEEN :startDate AND :endDate))  ")
float getRevenuBrutProduit(Long idProduit, Date startDate, Date endDate);
	
		
	
	
	@Query("SELECT SUM(qte) FROM detailFacture c where (c.Totheparentdetailfacture.idProduit= :idProduit AND (c.facturechildren.datefacture BETWEEN :startDate AND :endDate))  ")
float getQuantieVendue(Long idProduit, Date startDate, Date endDate);
	

}
