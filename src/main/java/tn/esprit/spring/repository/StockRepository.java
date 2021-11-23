package tn.esprit.spring.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {

	Stock findByLibelleStock(String s);
	//esm table yebda bil majus
	@Query("SELECT c FROM Stock c WHERE c.qte <=c.qteMin")
	List<Stock> retrieveStockByqte();
	
}
