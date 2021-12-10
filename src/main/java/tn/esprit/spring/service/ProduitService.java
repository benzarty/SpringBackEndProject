package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.DetailProduit;
import tn.esprit.spring.entity.Produit;

public interface ProduitService {
	List<Produit> retrieveAllProduits();

	Produit addProduit(Produit p,Long idRayon,Long idStock);

	Produit retrieveProduit(Long id);
	

	void assignProduitToStockgo(Long idProduit, Long stockproduit);
	
	


	Produit addProduitfile(Produit p, String file);

	void deleteproduit(Long id);
	Produit updateproduit(Produit p);
	void afecterProduitimage(Long idProduit, String file);

	public List<Produit> findProductsWithSorting(String field);
	public Page<Produit> findProductsWithPagination(int offset,int pageSize);
	


}
