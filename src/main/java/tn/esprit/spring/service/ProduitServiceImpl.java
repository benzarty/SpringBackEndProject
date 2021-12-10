package tn.esprit.spring.service;

import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	
	//@Autowired
	//FounisseurServiceImpl FounisseurServiceImpl;
	

	@Override
	public List<Produit> retrieveAllProduits() {
		// TODO Auto-generated method stub
		return (List<Produit>) this.produitRepo.findAll();
	}

	@Override
	public Produit addProduit(Produit p,Long idRayon,Long idStock) {
		// TODO Auto-generated method stub
		Rayon rayon=rayonrepo.findById(idRayon).orElse(null);
		Stock stock=stockrepo.findById(idStock).orElse(null);

		
		p.setRayonproduit(rayon);
		p.setStockproduit(stock);
		if(p.getDetailproduit().getDateCreation() == null)
		{
			p.getDetailproduit().setDateCreation(new Date());
			p.getDetailproduit().setDateDerniereModification(new Date());
		} else {
			p.getDetailproduit().setDateDerniereModification(new Date());
		}
				
		DetailProduit d = detailrepo.save(p.getDetailproduit());

	   p.setDetailproduit(d);
		return produitRepo.save(p);
		
	}	

	@Override
	public Produit retrieveProduit(Long id) {
		// TODO Auto-generated method stub
		return produitRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteproduit(Long id) {
		produitRepo.deleteById(id);
		
	}

	@Override
	public Produit updateproduit(Produit c) {
	
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
	public Produit addProduitfile(Produit p, String file) {
		p.setFileName(file);
				return produitRepo.save(p);
	}

	
	
	@Override
	public void afecterProduitimage(Long idProduit, String file) {
		// TODO Auto-generated method stub
		Produit produit=produitRepo.findById(idProduit).orElse(null);// TODO Auto-generated method stub
		produit.setFileName(file);
		produitRepo.save(produit);
		}

	@Override
	public List<Produit> findProductsWithSorting(String field) {
		// TODO Auto-generated method stub
				return  produitRepo.findAll(Sort.by(Sort.Direction.ASC,field));
	}

	@Override
	public Page<Produit> findProductsWithPagination(int offset, int pageSize) {
		Page<Produit> products = produitRepo.findAll(PageRequest.of(offset, pageSize));
        return  products;
	}

	

	

	
	

	

}
