package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;


import tn.esprit.spring.entity.DetailProduit;
import tn.esprit.spring.entity.detailFacture;

public interface DetailFactureService {
	
	List<detailFacture> retrieveAllDetailFacture();

	detailFacture addDetailFacture(detailFacture c,Long idProduit,Long idFacture);

	void deleteDetailFacture(Long id);

	detailFacture updateDetailFacture(detailFacture c);

	detailFacture retrieveDetailFacture(Long id);
	
	List<detailFacture> retrieveDetailFactureByidFacture(Long idfacture);
	float getRevenuBrutProduit(Long idProduit, Date startDate, Date endDate);

	

}
