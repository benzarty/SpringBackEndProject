package tn.esprit.spring.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.DetailProduit;
import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.DetailProduitRepository;

@Service
public class DetailProduitServiceImpl  implements DetailProduitService{
	
	
	@Autowired
	DetailProduitRepository detailProduitRepository;
	
	
	
	
	
	
	
	@Override
	public Fournisseur retrieveFournisseur(Long id) {
		return null;
	}

	@Override
	public DetailProduit addDetailProduit(DetailProduit d) {
		return detailProduitRepository.save(d);
	}

	@Override
	public DetailProduit addDetailProduit1(Produit d) {
		d.getDetailproduit().setDateCreation(new Date());
		d.getDetailproduit().setDateDerniereModification(new Date());
		return detailProduitRepository.save (d.getDetailproduit());
	}
	
	

	

}
