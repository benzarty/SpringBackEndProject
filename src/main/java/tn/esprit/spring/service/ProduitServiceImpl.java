package tn.esprit.spring.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.DetailProduit;
import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.DetailProduitRepository;
import tn.esprit.spring.repository.FournisseurRepository;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.RayonRepository;
import tn.esprit.spring.repository.StockRepository;



@Service
public class ProduitServiceImpl implements ProduitService {
	@Autowired
	ProduitRepository produitRepo;
	

	@Autowired
	StockService stockService;
	
	
	
	@Autowired
	RayonRepository rayonrepo;
	
	@Autowired
	StockRepository stockrepo;
	
	@Autowired
	DetailProduitRepository detailrepo;
	
	@Autowired
	RayonService rayonService;
	
	@Autowired
	DetailProduitService detailproduitservice;
	
	@Autowired
	FournisseurRepository fourrepo;
	

	@Override
	public List<Produit> retrieveAllProduits() {
		// TODO Auto-generated method stub
		return (List<Produit>) produitRepo.findAll();
	}

	@Override
	public Produit addProduit(Produit p,Long idRayon,Long idStock) {
		// TODO Auto-generated method stub
		Rayon rayon=rayonrepo.findById(idRayon).orElse(null);
		Stock stock=stockrepo.findById(idStock).orElse(null);

		
		p.setRayonproduit(rayon);
		p.setStockproduit(stock);
		DetailProduit dp=detailrepo.save(p.getDetailproduit());
		p.setDetailproduit(dp);
		produitRepo.save(p);
		
		return p;
	}	

	@Override
	public Produit retrieveProduit(Long id) {
		// TODO Auto-generated method stub
		return produitRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteProduit(Long id) {
		produitRepo.deleteById(id);
		
	}

	@Override
	public Produit updateProduit(Produit c) {
	
		return produitRepo.save(c);
	}

	@Override
	public void assignProduitToStockgo(Long idProduit, Long stockproduit) {
		Produit produit=produitRepo.findById(idProduit).orElse(null);
		
		Stock stokk=stockrepo.findById(stockproduit).orElse(null);
		
		produit.setStockproduit(stokk);
		
		produitRepo.save(produit);
		
		
		
	}
	

	@Override
	public void assignFournisseurToProduit(Long idfourn, Long produitId) {
		
		Fournisseur fourni = fourrepo.getById(idfourn);
		Produit produit=produitRepo.getById(produitId);
		
		
		
		
        Set<Fournisseur> listfournisseur = new HashSet<Fournisseur>();
        listfournisseur =produit.getFournisseurproduit();
        listfournisseur.add(fourni);
		
        produit.getFournisseurproduit().add(fourni);
        produitRepo.save(produit);
			
		
	
		
	}

	

	
	

	

}
