package tn.esprit.spring.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.entity.Produit;
@Repository
public interface FournisseurRepository extends CrudRepository<Fournisseur, Long>, JpaRepository<Fournisseur,Long> {


	@Query("SELECT u FROM Produit u join u.fournisseurproduit f WHERE (f.idFournisseur LIKE :idFournisseur)")
	List<Produit> retrieveProduitFournisseur(@Param("idFournisseur") Long idFournisseur);
	
	
	@Query("SELECT count(u) FROM Produit u join u.fournisseurproduit f  WHERE (f.idFournisseur LIKE :idFournisseur)")	
	int nbProduit(@Param("idFournisseur") Long idFournisseur);
	@Query("SELECT DISTINCT f FROM Produit u join u.fournisseurproduit f join u.Detailproduit dp WHERE (dp.categorieProduit LIKE :categorieProduit)")
	List<Fournisseur> GetFournisseurByCategorie(@Param("categorieProduit") CategorieProduit categorieProduit);
	
	@Query("SELECT COALESCE(SUM(df.qte*u.prixUnitaire),0) FROM Produit u join u.fournisseurproduit f join u.detailFacture df WHERE (f.idFournisseur LIKE :idFournisseur)")
	float ChiffreAffaireByFournisseur(@Param("idFournisseur") Long idFournisseur);
	
	@Query("SELECT f.idFournisseur as name, COALESCE(SUM(df.qte*u.prixUnitaire),0) as value  FROM Produit u join u.fournisseurproduit f join u.detailFacture df group by f")
	List<Map<String,Object>> ChiffreAffaireChaqueFournisseur();
	
	@Query("SELECT (df.qte*u.prixUnitaire) as Total , facture.datefacture AS date FROM Produit u join u.fournisseurproduit f join u.detailFacture df join df.facturechildren facture where f.idFournisseur like :idFournisseur order BY date")
	List<Map<String,Object>> ChiffreAffaireParDateFournisseur(@Param("idFournisseur") Long idFournisseur);
	
	
}
