package tn.esprit.spring.controller;

import java.util.List;

import javax.validation.Valid;

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

import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.service.RayonServiceImpl;
import tn.esprit.spring.service.StockService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.service.RayonServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rayon")
public class RayonController {
	
	
	@Autowired
	RayonServiceImpl servicerayon;
	
	
	// http://localhost:8089/SpringMVC/rayon/add-rayon
	@PostMapping("/add-rayon")
	@ResponseBody
	public Rayon addRayon(@Valid @RequestBody Rayon r)
	{
		return servicerayon.addRayon(r);
	}
	
	// http://localhost:8089/SpringMVC/client/retrieve-all-rayon
	@GetMapping("/retrieve-all-rayon")
	@ResponseBody
	public List<Rayon>getRayon() {
		return servicerayon.retrieveAllRayons();
	}

	//http://localhost:8089/SpringMVC/rayon/retrieve-rayon/8
	@GetMapping("/retrieve-rayon/{rayon-id}")
	@ResponseBody
	public Rayon retrieveStock(@PathVariable("rayon-id") Long rayonId) {
		return servicerayon.retrieveRayon(rayonId);
	}
	


	//http://localhost:8089/SpringMVC/rayon/remove-rayon/{rayon-id}
	@DeleteMapping("/remove-rayon/{rayon-id}")
	@ResponseBody
	public void removeRayon(@PathVariable("rayon-id") Long rayonId) {
		servicerayon.deleteRayon(rayonId);
	}

	//http://localhost:8089/SpringMVC/rayon/modify-rayon
	@PutMapping("/modify-rayon")
	@ResponseBody
	public Rayon modifyrayon( @Valid @RequestBody Rayon rayon) {
		return servicerayon.updateRayon(rayon);
	}
}