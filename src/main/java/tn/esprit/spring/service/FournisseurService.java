package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.entity.Produit;


public interface FournisseurService {

	public List<Fournisseur> retrieveAllFournisseur();

	Fournisseur addFournisseurt(Fournisseur c);

	void deleteFournisseur(Long id);

	Fournisseur updateFournisseur(Fournisseur c);

	Fournisseur retrieveFournisseur(Long id);
	
	List<Produit> allProductFournisseur(Long idFournisseur);
	int nbproduit(Long idFournisseur);
	
	List <Fournisseur> GetFournisseurByCategorie(CategorieProduit categorieProduit);
	float ChiffreAffaireByFournisseur(Long idFournisseur);
	List<Map<String,Object>> chiffreDaffaireChaqueFournisseur();
	void assignFournisseurToProduit(Long fournisseurId, Long produitId);
	List<Map<String,Object>> ChiffreAffaireParDateFournisseur(Long idFournisseur);
}
