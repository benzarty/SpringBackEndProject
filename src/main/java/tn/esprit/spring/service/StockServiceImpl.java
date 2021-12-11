package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.StockRepository;
@Slf4j
@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepo;
	
	@Override
	public List<Stock> retrieveAllStocks() {
		return (List<Stock>) this.stockRepo.findAll();
	}

	@Override
	public Stock addStock(Stock s) {
		return this.stockRepo.save(s);
	}

	@Override
	public Stock updateStock(Stock s) {
		if(this.stockRepo.existsById(s.getIdStock())){
			return this.stockRepo.save(s);
		 }
		 return null;	
	}

	@Override
	public Stock retrieveStock(Long id) {
			return this.stockRepo.findById(id).orElse(null);
	}

	@Override
	public int deleteStock(Long id) {
		 if(this.stockRepo.existsById(id)){
			 stockRepo.deleteById(id);
			 return 1;
		 }
		 //log.info("WRONGGG this is the id "+id);
		 return 0;
	}

	
	
	@Override
	@Scheduled(fixedRate = 60000)
	public void retrieveStatusStock() {
		List<Stock> my_stock = this.retrieveAllStocks();
		for(Stock s: my_stock) {
			if(s.getQte() <= s.getQteMin()) {
				log.info("Produit "+s.getLibelleStock()+" a une quantit moins que la quanite minmal "+s.getQteMin());
			}
		}		
	}

	@Override
	public List<Stock> getStockWhereDateBetween(Date startDate, Date endDate) {
		return this.stockRepo.getStockWhereDateBetween(startDate, endDate);
	}
	
	

}
