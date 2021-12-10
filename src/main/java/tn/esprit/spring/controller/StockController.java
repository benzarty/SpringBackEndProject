package tn.esprit.spring.controller;

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

import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.service.StockService;



@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	StockService servicestock;
	
	@CrossOrigin(origins = "http://localhost:4200")

	// http://localhost:8049/SpringMVC/stock/retrieve-all-stock
	@GetMapping("/retrieve-all-stock")
	@ResponseBody
	public List<Stock>getStocks() {
	List<Stock> liststock = servicestock.retrieveAllStocks();
	return liststock;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	//http://localhost:8049/SpringMVC/stock/retrieve-stock/8
	@GetMapping("/retrieve-stock/{stock-id}")
	@ResponseBody
	public Stock retrieveStock(@PathVariable("stock-id") Long stockId) {
	return servicestock.retrieveStock(stockId);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	// http://localhost:8049/SpringMVC/stock/add-stock
	@PostMapping("/add-stock")
	@ResponseBody
	public Stock addStock(@RequestBody Stock c3)
	{
	Stock stockk = servicestock.addStock(c3);
	return stockk;
	}


	@CrossOrigin(origins = "http://localhost:4200")
	//http://localhost:8049/SpringMVC/stock/remove-stock/{stock-id}
	@DeleteMapping("/remove-stock/{stock-id}")
	@ResponseBody
	public void removestock(@PathVariable("stock-id") Long stockId) {
		servicestock.deleteStock(stockId);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	//http://localhost:8049/SpringMVC/stock/modify-stock
	@PutMapping("/modify-stock")
	@ResponseBody
	public Stock modifyStock(@RequestBody Stock stock) {
	return servicestock.updateStock(stock);
	}

}