package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entity.DetailProduit;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.service.DetailProduitServiceImpl;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/detailproduit")
public class DetailProduitController {
	
	@Autowired
	DetailProduitServiceImpl detailprod;
	
	

	// http://localhost:8089/SpringMVC/client/add-client
	@PostMapping("/add-detail")
	@ResponseBody
	public DetailProduit addProduit(@RequestBody DetailProduit c)
	{
		DetailProduit pro = detailprod.addDetailProduit(c);
	return pro;
	}

}
