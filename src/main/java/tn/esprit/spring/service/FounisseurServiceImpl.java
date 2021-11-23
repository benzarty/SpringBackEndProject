package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.repository.FournisseurRepository;

public class FounisseurServiceImpl implements FournisseurService{
	
	@Autowired
	FournisseurRepository fourrepo;

	@Override
	public List<Fournisseur> retrieveAllFournisseur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fournisseur addFournisseur(Fournisseur c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFournisseur(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Fournisseur updateFournisseur(Fournisseur c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fournisseur retrieveFournisseur(Long id) {
		return fourrepo.findById(id).orElse(null);
	}

	

}
