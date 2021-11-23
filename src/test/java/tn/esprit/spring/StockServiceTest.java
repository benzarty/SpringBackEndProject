package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.StockRepository;
import tn.esprit.spring.service.StockServiceImpl;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {
	@Autowired
	StockServiceImpl stockServiceImpl;

	@Autowired
	StockRepository stockRepository;

	@Test
	public void testAddStock() {
		try {
			List<Stock> stocks = stockServiceImpl.retrieveAllStocks();
			int expected = stocks.size();
			Stock s = new Stock();
			s.setLibelleStock("stock test");
			s.setQte(10);
			s.setQteMin(100);
			Stock savedStock = stockServiceImpl.addStock(s);
			assertEquals(expected + 1, stockServiceImpl.retrieveAllStocks().size());
			assertNotNull(savedStock.getLibelleStock());
			Stock stock = stockRepository.findByLibelleStock("stock test");
			assertThat(stock.getLibelleStock()).isEqualTo("stock test");
			assertThat(savedStock).isNotNull();
			
		assertNotNull(savedStock.getIdStock());
			stockServiceImpl.deleteStock(savedStock.getIdStock());

		
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	@Test
	public void DeleteTest() {
		
		Stock s = new Stock();
		s.setIdStock(1L);
		s.setLibelleStock("stock test");
		s.setQte(10);
		s.setQteMin(100);
		System.out.println("stock : " + s);
		Stock savedStock = stockServiceImpl.addStock(s);
		stockServiceImpl.deleteStock(savedStock.getIdStock());
		assertEquals(0, stockServiceImpl.retrieveAllStocks().size());

		}

	
	
	
	
	
	
}

