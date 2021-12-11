package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.Stock;

public interface StockService  {

	List<Stock> retrieveAllStocks();

	Stock addStock(Stock s);

	Stock updateStock(Stock u);

	Stock retrieveStock(Long id);
	
	
	int deleteStock(Long id);
	
	void retrieveStatusStock();
	
	List<Stock> getStockWhereDateBetween(Date startDate, Date endDate);

	
}
