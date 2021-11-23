package tn.esprit.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.RayonRepository;
import tn.esprit.spring.repository.StockRepository;
import tn.esprit.spring.service.ProduitServiceImpl;

@RestController
@RequestMapping("/Produit")
public class ProduitController {

	@Autowired
	ProduitServiceImpl serviceproduit;

	@Autowired
	ProduitRepository repoproduit;

	@Autowired
	StockRepository repostock;

	@Autowired
	RayonRepository reporepo;

	// http://localhost:8041/SpringMVC/Produit/retrieve-all-produit
	@GetMapping("/retrieve-all-produit")
	@ResponseBody
	public List<Produit> getProduits() {
		List<Produit> listproduit = serviceproduit.retrieveAllProduits();
		return listproduit;
	}

	// http://localhost:8100/SpringMVC/Produit/retrieveproduitttt/8
	@GetMapping("/retrieveproduitttt/{produit-id}")
	@ResponseBody
	public Produit retrieveProduit(@PathVariable("produit-id") Long produitId) {
		return serviceproduit.retrieveProduit(produitId);
	}
	
	
	

	// http://localhost:8089/SpringMVC/Produit/modify-produit
	@PutMapping("/modify-produit")
	@ResponseBody
	public Produit modifyProduit(@RequestBody Produit produit) {
		return serviceproduit.updateProduit(produit);
	}

	
	
	
	
	
	
	
	// http://localhost:8089/SpringMVC/Produit/assignProduitToStockgogo
	@PutMapping("/assignProduitToStockgo/{idProduit}/{stockproduit}")
	@ResponseBody
	public void assignProduitToStockgo(@PathVariable("idProduit") Long produitId,@PathVariable("stockproduit") Long stockproduit)
	{
		 serviceproduit.assignProduitToStockgo(produitId, stockproduit);
		
	}

	
	
	
	
	
	// http://localhost:8089/SpringMVC/Produit/add-produit
	@PostMapping("/add-produit/{idRayon}/{idStock}")
	@ResponseBody
	public Produit createProduit(@RequestBody Produit p1,@PathVariable Long idRayon,@PathVariable Long idStock) {
		
			Produit p=serviceproduit.addProduit(p1, idRayon, idStock);
			return p;
		

	}



	// http://localhost:8089/SpringMVC/Produit/remove-produit/{produit-id}
	@DeleteMapping("/remove-produit/{produit-id}")
	@ResponseBody
	public void removeproduit(@PathVariable("produit-id") Long produitId) {
		serviceproduit.deleteProduit(produitId);
	}
	
	
	@PutMapping("/affecterProduitTofournisseur/{idProduit}/{idfourn}")
	@ResponseBody
	public void affecterProduitToFournisseur(@PathVariable("idProduit") Long produitId,@PathVariable("idfourn") Long idfourn)
	{
		serviceproduit.assignFournisseurToProduit(produitId, idfourn);
		
	}
	
	
	
	
	

}
