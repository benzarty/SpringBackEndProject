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
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.DetailProduit;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.detailFacture;
import tn.esprit.spring.service.DetailFactureService;
import tn.esprit.spring.service.DetailFactureServiceImpl;

@RestController
@RequestMapping("/DetailFacture")
public class DetailFactureController {
	
	@Autowired
	DetailFactureServiceImpl detailfac;
	
	
	
	// http://localhost:8089/SpringMVC/client/retrieve-all-clients
	@GetMapping("/retrieveDetailFacture")
	@ResponseBody
	public List<detailFacture>retrieveDetailFacture() {
	List<detailFacture> listDetail = detailfac.retrieveAllDetailFacture();
	return listDetail;
	}
	
	

	//http://localhost:8089/SpringMVC/client/retrieve-client/8
	@GetMapping("/retrieveDetailFacture/{detailid}")
	@ResponseBody
	public detailFacture retrieveDetailFacture(@PathVariable("detailid") Long detailid) {
	return detailfac.retrieveDetailFacture(detailid);
	}
	
	
	//http://localhost:8089/SpringMVC/client/retrieve-client/8
		@GetMapping("/retrieveDetailFactureBYidfacture/{idfacture}")
		@ResponseBody
		public List<detailFacture> retrieveDetailFactureBYidfacture(@PathVariable("idfacture") Long idfacture) {
		return detailfac.retrieveDetailFactureByidFacture(idfacture);
		}
	
	
	

	// http://localhost:8089/SpringMVC/Produit/add-produit
	@PostMapping("/add-DetailFacture/{idproduit}/{idfacture}")
	@ResponseBody
	public detailFacture createProduit(@RequestBody detailFacture p1,@PathVariable Long idproduit,@PathVariable Long idfacture) {
		
		detailFacture p=detailfac.addDetailFacture(p1, idproduit, idfacture);
			return p;
		

	}

	
	//http://localhost:8089/SpringMVC/client/remove-client/{client-id}
	@DeleteMapping("/removeDetailFacture/{iddetailfacture}")
	@ResponseBody
	public void removeDetailFacture(@PathVariable("iddetailfacture") Long iddetailfacture) {
		detailfac.deleteDetailFacture(iddetailfacture);
	}

	
	//http://localhost:8089/SpringMVC/client/retrieve-client/8
		@GetMapping("/getRevenuBrutProduit/{idProduit}/{startDate}/{endDate}")
		@ResponseBody
		public float getRevenuBrutProduit(@PathVariable("idProduit") Long idProduit,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate)
		{
		return detailfac.getRevenuBrutProduit(idProduit, startDate, endDate);
		}

		
		//http://localhost:8089/SpringMVC/client/modify-client
		@PutMapping("/modify-detailfacture")
		@ResponseBody
		public detailFacture modifyClient(@RequestBody detailFacture Detaill) {
		return detailfac.updateDetailFacture(Detaill);
		}

	
	
	
	
	

}
