package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.Fournisseur;


public interface FournisseurService {

	List<Fournisseur> retrieveAllFournisseur();

	Fournisseur addFournisseur(Fournisseur c);

	void deleteFournisseur(Long id);

	Fournisseur updateFournisseur(Fournisseur c);

	Fournisseur retrieveFournisseur(Long id);
	
}
