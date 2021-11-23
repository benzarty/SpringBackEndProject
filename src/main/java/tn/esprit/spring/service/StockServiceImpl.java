package tn.esprit.spring.service;

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
	StockRepository stockrepo;
	
	@Override
	public List<Stock> retrieveAllStocks() {
		// TODO Auto-generated method stub
		return (List<Stock>) stockrepo.findAll();
	}

	@Override
	public Stock addStock(Stock s) {
		// TODO Auto-generated method stub
		return stockrepo.save(s);
	}

	@Override
	public Stock updateStock(Stock u) {
		// TODO Auto-generated method stub
		return stockrepo.save(u);
	}

	@Override
	public Stock retrieveStock(Long id) {
		// TODO Auto-generated method stub
		return stockrepo.findById(id).orElse(null);
	}

	@Override
	public void deleteStock(Long id) {
		stockrepo.deleteById(id);
		
	}
	
	@Scheduled(fixedRate = 60000)
	public void fixedRateMethod() {
		List<Stock> stocks=stockrepo.retrieveStockByqte();
		for(Stock s:stocks)
		{
		log.info(s.getLibelleStock()+"  Stock est en rupture sorry  ");
		}
		
	
	}
	
	

}
