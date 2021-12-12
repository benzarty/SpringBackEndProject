package tn.esprit.spring.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.service.FounisseurServiceImpl;


@Api(tags = "Fournisseur management")
@RestController 
@RequestMapping("/Fournisseur") 
public class FournisseurController {
	@Autowired 
	FounisseurServiceImpl fournisseurServ; 

	// http://localhost:8750/SpringMVC/Fournisseur/retrieve-all-Fournisseurs
	@ApiOperation(value = "Récupérer la liste des Fournisseurs")
	@GetMapping("/retrieve-all-Fournisseurs") 
	@ResponseBody 
	public List<Fournisseur> getFournisseurs() { 
	List<Fournisseur> listFournisseur = fournisseurServ.retrieveAllFournisseur();
	return listFournisseur; 
	} 

	// http://localhost:8750/SpringMVC/Fournisseur/retrieve-Fournisseur/1
	@GetMapping("/retrieve-Fournisseur/{Fournisseur-id}") 
	@ResponseBody 
	public Fournisseur retrieveFournisseur(@PathVariable("Fournisseur-id") Long FournisseurId) { 
		return fournisseurServ.retrieveFournisseur(FournisseurId); 
	} 
	//http://localhost:8750/SpringMVC/Fournisseur/add-fournisseur
	@PostMapping("/add-fournisseur") 
	@ResponseBody 
	public Fournisseur addFournisseur(@RequestBody Fournisseur c)  
	{ 
		Fournisseur fournisseur = fournisseurServ.addFournisseurt(c);
	return fournisseur; 
	} 


	// http://localhost:8750/SpringMVC/Fournisseur/remove-fournisseur/1
	@DeleteMapping("/remove-fournisseur/{fournisseur-id}") 
	@ResponseBody 
	public void removeFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) { 
		fournisseurServ.deleteFournisseur(fournisseurId); 
	} 
	// http://localhost:8750/SpringMVC/Fournisseur/modify-Fournisseur
	@PutMapping("/modify-Fournisseur") 
	@ResponseBody 
	public Fournisseur modifyFournisseur(@RequestBody Fournisseur fournisseur) { 
	return fournisseurServ.updateFournisseur(fournisseur); 
	} 
	//Vous devez faire la methode "assignFournisseurToProduit"
	// http://localhost:8750/SpringMVC/Fournisseur/retrieve-Fournisseur-produit/1
	@GetMapping("/retrieve-Fournisseur-produit/{Fournisseur-id}") 
	@ResponseBody 
	public List<Produit> retrieveFournisseurProduit(@PathVariable("Fournisseur-id") Long FournisseurId) { 
		return fournisseurServ.allProductFournisseur(FournisseurId); 
	} 
	// //Vous devez faire la methode "assignFournisseurToProduit"
	// http://localhost:8750/SpringMVC/Fournisseur/nb-produit/1
	@GetMapping("/nb-produit/{Fournisseur-id}") 
	@ResponseBody 
	public int chiffredaffairefournisseur(@PathVariable("Fournisseur-id") Long FournisseurId) { 
		return fournisseurServ.nbproduit(FournisseurId);
	} 
	// //Vous devez faire la methode "assignFournisseurToProduit" + remplir les tables "detail produi  + produit"
	// http://localhost:8750/SpringMVC/Fournisseur/GetIdByCategorie/Electromenager
	@GetMapping("/GetIdByCategorie/{Categorie}") 
	@ResponseBody 
	public List<Fournisseur> getFournisseurById(@PathVariable("Categorie") CategorieProduit categorieProduit) { 
		return fournisseurServ.GetFournisseurByCategorie(categorieProduit);
	} 
	// //Vous devez faire la methode "assignFournisseurToProduit" + remplir les tables "deatil facture +facture  + produit"
		// http://localhost:8750/SpringMVC/Fournisseur/GetIdByCategorie/Electromenager
	
	//http://localhost:8750/SpringMVC/Fournisseur/getChiffreDaffaure/1
	@GetMapping("/getChiffreDaffaure/{Fournisseur-id}") 
	@ResponseBody 
	public float ChiffreAffaireByFournisseur(@PathVariable("Fournisseur-id") Long FournisseurId) { 
		return fournisseurServ.ChiffreAffaireByFournisseur(FournisseurId);
	} 
	// //Vous devez faire la methode "assignFournisseurToProduit" + remplir les tables "deatil facture +facture  + produit"
	//http://localhost:8750/SpringMVC/Fournisseur/getChiffreDaffaureChaqueFournisseur
	@GetMapping("/getChiffreDaffaureChaqueFournisseur") 
	@ResponseBody 
	public List<Map<String,Object>> ChiffreAffaireAllFournisseur() { 
		return fournisseurServ.chiffreDaffaireChaqueFournisseur();
	} 
	
	@PostMapping("/assignfournisseurproduit/{fournisseur-id}/{produit-id}")
	@ResponseBody
	@ApiOperation("assigns fournisseur to a produit")
	public void assignFournisseurToProduit(@PathVariable("fournisseur-id") Long FournisseurId,@PathVariable("produit-id") Long produitId) {
		fournisseurServ.assignFournisseurToProduit(produitId, FournisseurId);
	}
	// //Vous devez faire la methode "assignFournisseurToProduit" + remplir les tables "deatil facture +facture  + produit"

	// http://localhost:8750/SpringMVC/Fournisseur/ChiAffaireDateFournisseur/1
	@GetMapping("/ChiAffaireDateFournisseur/{fournisseur-id}") 
	@ResponseBody 
	public List<Map<String, Object>> ChiffreAffaireParDateFournisseur(@PathVariable("fournisseur-id") Long idFournisseur){
		return fournisseurServ.ChiffreAffaireParDateFournisseur(idFournisseur);
	}
	}
