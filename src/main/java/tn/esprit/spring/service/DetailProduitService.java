package tn.esprit.spring.service;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.DetailProduit;
import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.entity.Produit;

@Service
public interface DetailProduitService {
	
	Fournisseur retrieveFournisseur(Long id);
	DetailProduit addDetailProduit(DetailProduit d);
	DetailProduit addDetailProduit1(Produit d);


}
