package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.FournisseurRepository;
import tn.esprit.spring.repository.ProduitRepository;
@Service
public class FounisseurServiceImpl implements FournisseurService{
	
	@Autowired
	FournisseurRepository fourrepo;

	@Autowired
	ProduitRepository produirepo;
	@Override
	public List<Fournisseur> retrieveAllFournisseur() {
		return	fourrepo.findAll();
		
		 
	}

	@Override
	public Fournisseur addFournisseurt(Fournisseur c) {
		return fourrepo.save(c);
	}

	@Override
	public void deleteFournisseur(Long id) {
		fourrepo.deleteById(id);
		
	}

	@Override
	public Fournisseur updateFournisseur(Fournisseur c) {
		return fourrepo.save(c);
	}

	@Override
	public Fournisseur retrieveFournisseur(Long id) {
		// TODO Auto-generated method stub
		return fourrepo.findById(id).orElse(null);
	}

	@Override
	public List<Produit> allProductFournisseur(Long idFournisseur) {
		
		return fourrepo.retrieveProduitFournisseur(idFournisseur);
	}

	@Override
	public int nbproduit(Long idFournisseur) {
		// TODO Auto-generated method stub
		return fourrepo.nbProduit(idFournisseur);
	}

	@Override
	public List<Fournisseur> GetFournisseurByCategorie(CategorieProduit categorieProduit) {
		
		return fourrepo.GetFournisseurByCategorie(categorieProduit);
	}

	@Override
	public float ChiffreAffaireByFournisseur(Long idFournisseur) {
		// TODO Auto-generated method stub
		return fourrepo.ChiffreAffaireByFournisseur(idFournisseur);
	}

	@Override
	public List<Map<String,Object>>chiffreDaffaireChaqueFournisseur() {
		// TODO Auto-generated method stub
		return fourrepo.ChiffreAffaireChaqueFournisseur();
	}

	@Override
	public void assignFournisseurToProduit(Long fournisseurId, Long produitId) {
		Fournisseur f = new Fournisseur();
		Produit p = new Produit();
		f = fourrepo.getById(fournisseurId);
		p = produirepo.findById(produitId).orElse(null);
		p.getFournisseurproduit().add(f);
		System.out.print(f);
		produirepo.save(p);
		

		
	}

	@Override
	public List<Map<String, Object>> ChiffreAffaireParDateFournisseur(Long idFournisseur) {
		// TODO Auto-generated method stub
		return fourrepo.ChiffreAffaireParDateFournisseur(idFournisseur);
	}

}
