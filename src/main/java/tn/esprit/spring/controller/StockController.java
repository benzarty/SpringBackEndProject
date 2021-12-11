package tn.esprit.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.service.StockService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/stock")
public class StockController {
	@Autowired
	StockService stockService;
	
	
    //http://localhost:8089/SpringMVC/stock/add-stock
	@PostMapping("/add-stock")
	@ApiOperation(value = "ajouter stock")
	public ResponseEntity addStock(@Valid @RequestBody Stock s) {
		return new ResponseEntity<Stock>(stockService.addStock(s),HttpStatus.CREATED);
		
	}

	// http://localhost:8089/SpringMVC/stock/retrieve-all-stocks
	@GetMapping("/retrieve-all-stocks")
	@ApiOperation(value = "recuperer la liste des stocks")
	@ResponseBody
	public List<Stock> listStock() {
		return stockService.retrieveAllStocks();
	}

	// http://localhost:8089/SpringMVC/stock/retrieve-stock/1
	@GetMapping("/retrieve-stock/{stock-id}")
	@ApiOperation(value = "recuperer stock par id")
	@ResponseBody
	public ResponseEntity retrieveStock(@PathVariable("stock-id") Long stockId) {
		if(stockService.retrieveStock(stockId) != null) {
			return new ResponseEntity<Stock>(stockService.retrieveStock(stockId),HttpStatus.ACCEPTED);
		}
		return new ResponseEntity("There is no stock registered under the id "+stockId ,HttpStatus.NOT_FOUND);
	}
	
	
	// http://localhost:8089/SpringMVC/stock/modify-stock
	@PutMapping("/modify-stock")
	@ApiOperation(value = "modifier stock")
	@ResponseBody
	public ResponseEntity modifyStock(@Valid @RequestBody Stock stock) {
		if(stockService.updateStock(stock)!=null){
			return new ResponseEntity(stockService.updateStock(stock) ,HttpStatus.ACCEPTED);
		}
		return new ResponseEntity("There is no stock to update like "+stock.getIdStock() +" in database",HttpStatus.NOT_FOUND);

	}
	
	// http://localhost:8089/SpringMVC/stock/retrieve-stock-between/{start-date}/{end-date}
		@GetMapping("/retrieve-stock-between/{start-date}/{end-date}")
		@ApiOperation(value = "Get stock where date between")
		@ResponseBody
		public List<Stock> getStockWhereDateBetwwen(@PathVariable("start-date") String startDate, @PathVariable("end-date") String endDate ) throws ParseException  {
			
			Date  startDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date  endDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			
				return stockService.getStockWhereDateBetween(startDate1, endDate1);
			
		}

	

	// http://localhost:8089/SpringMVC/stock/remove-stock/{stock-id}
	@DeleteMapping("/remove-stock/{stock-id}")
	@ApiOperation(value = "supprimer stock")
	@ResponseBody
	public ResponseEntity removeStock(@PathVariable("stock-id") Long stocktId) {
		
		if (stockService.deleteStock(stocktId)==1) {
			//return new ResponseEntity("Stock Removed Successffuly", HttpStatus.FOUND);
			return ResponseEntity.accepted().build();
		}
		return new ResponseEntity("There is no stock registered under the id "+stocktId ,HttpStatus.NOT_FOUND);
		
	}

}