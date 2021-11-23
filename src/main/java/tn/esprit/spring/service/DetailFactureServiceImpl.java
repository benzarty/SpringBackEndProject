package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.DetailProduit;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.detailFacture;
import tn.esprit.spring.repository.DetailFactureRepository;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.ProduitRepository;


@Service
public class DetailFactureServiceImpl implements DetailFactureService{
	
	
	@Autowired
	DetailFactureRepository detailrepo;
	
	@Autowired
	ProduitRepository produitrepo;
	
	@Autowired
	FactureRepository facturerepo;

	@Override
	public List<detailFacture> retrieveAllDetailFacture() {
		List<detailFacture> List=(List<detailFacture>)detailrepo.findAll() ;
		return List;
	}

	

	@Override
	public void deleteDetailFacture(Long id) {
		 detailrepo.deleteById(id);
		
	}

	@Override
	public detailFacture updateDetailFacture(detailFacture c) {
		
		return detailrepo.save(c);
	}

	@Override
	public detailFacture retrieveDetailFacture(Long id) {
		// TODO Auto-generated method stub
		return detailrepo.findById(id).orElse(null);
	}



	@Override
	public detailFacture addDetailFacture(detailFacture c,Long idProduit,Long idFacture) {
		
		
		Produit prod=produitrepo.findById(idProduit).orElse(null);
		
		
		Float prix=prod.getPrixUnitaire();
		Float prixtotal=prix*c.getQte();
		
		Facture fact=facturerepo.findById(idFacture).orElse(null);
		
		Float mantantremise=c.getPourcentageRemise()*prixtotal/100;
		
		Float newprix=prixtotal-mantantremise;
		
		c.setPrixtotal(newprix);
		c.setMontantRemise(mantantremise);
		
	c.setTotheparentdetailfacture(prod);
	c.setFacturechildren(fact);
	
           fact.setMontantRemise(fact.getMontantRemise()+mantantremise);
           fact.setMontantFacture(fact.getMontantFacture()+newprix);
	detailrepo.save(c);
	return c;
		
		
		
	}



}
