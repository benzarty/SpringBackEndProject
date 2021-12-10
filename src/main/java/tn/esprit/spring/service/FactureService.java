package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Facture;


public interface FactureService  {
	List<Facture> retrieveAllFactures();
	void cancelFacture(Long id);
	Facture retrieveFacture(Long id);
	void GetMontantbro();
	List<Facture> getFacturesByClient(Long idClient);
	
	List<Facture> getFacturesHistoriqueClient(Long idClient);

	
	
	Facture addFacture(Facture f, Long idClient);
	void deleteFacture(Long id);
	
	Facture UpdateFacture(Facture f);

	float getChiffreAffaireParCategorieClient(CategorieClient categorieClient,Date startDate, Date endDate) ;
	
	
	
	float getChiffreaffairetoday();
	
	float getNbFactureLastMonth();
	
	
	float getChiffreaffaireLastMonth();
	

	float getNbFactureBetweenSpecifiedDates(Date startDate, Date endDate);
	
	
	
	int UpdateModePaiement(Long idfacture,String Modepaiement);
	
	void Closefacture(Facture fac);
	
	
	
}
