package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.service.FactureService;

@RestController
@RequestMapping("/facture")
public class FactureController {
	
	@Autowired
	FactureService factureserv;
	
	@Autowired
	FactureRepository facrepo;

	
	
	// http://localhost:8089/SpringMVC/client/retrieve-all-clients
		@GetMapping("/GetAllFacture")
		@ResponseBody
	     List<Facture>GetAllFacture() {
		   return (List<Facture>) facrepo.findAll();
		
		}
		
		
	
	
	// http://localhost:8089/SpringMVC/client/retrieve-all-clients
	@GetMapping("/getFacturesByClient/{idClient}")
	@ResponseBody
     List<Facture>getFacturesByClient(@PathVariable("idClient") Long idClient) {
	   return factureserv.getFacturesByClient(idClient);
	
	}
	
	// http://localhost:8089/SpringMVC/client/add-client
	@PostMapping("/addFacture/{idClient}")
	@ResponseBody
	public Facture addFacture(@RequestBody Facture c,@PathVariable("idClient") Long idClient)
	{
		
		
	 Facture fac = factureserv.addFacture(c, idClient);
	
	    return fac;
	}
	
	
	//http://localhost:8089/SpringMVC/client/remove-client/{client-id}
	@DeleteMapping("/removeFacture/{idfacture}")
	@ResponseBody
	public void removeFacture(@PathVariable("idfacture") Long idfacture) {
		factureserv.deleteFacture(idfacture);
	}

	
	//http://localhost:8089/SpringMVC/client/modify-client
	@PutMapping("/modify-facture")
	@ResponseBody
	public Facture modifyFacture(@RequestBody Facture facture) {
	return factureserv.UpdateFacture(facture);
	}
	
	
	// http://localhost:8089/SpringMVC/client/retrieve-all-clients
			@GetMapping("/getChiffreAffaireParCategorieClient/{c}/{startDate}/{endDate}")
			@ResponseBody
			float getChiffreAffaireParCategorieClient(@PathVariable CategorieClient c,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
			   return  facrepo.getChiffreAffaireParCategorieClient(c,startDate,endDate);
			
			}
			
	

}
