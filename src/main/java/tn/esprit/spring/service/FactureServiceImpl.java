	package tn.esprit.spring.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.FactureRepository;
@Slf4j
@Service
public class FactureServiceImpl implements FactureService {

	@Autowired
	FactureRepository facturerepo;
	
	@Autowired
	ClientRepository repoclient;
	
	
	@Autowired
	private JavaMailSender mailSender;
	

	@Override
	public List<Facture> retrieveAllFactures() {

		return (List<Facture>) facturerepo.findAll();
	}

	@Override
	public void cancelFacture(Long id) {
		Facture facture = this.retrieveFacture(id);
		facture.setActive(false);
		this.facturerepo.save(facture);
	}

	@Override
	public Facture retrieveFacture(Long id) {
		return this.facturerepo.findById(id).orElse(null);
	}

	@Override
	@Scheduled(cron ="0 0 0 1 1 *")
	public void GetMontantbro() {
		Number x=facturerepo.GetMontantbro();
		
		log.info("La somme des factures du magasin est "+x); ;
		
	}

	@Override
	public List<Facture> getFacturesByClient(Long idClient) {
		
		
	return 	facturerepo.getFacturesByClient(idClient);
		
	}

	@Override
	public Facture addFacture(Facture f, Long idClient) {
		
		Client client=repoclient.findById(idClient).orElse(null);
		
		f.setClientFacure(client);
		return facturerepo.save(f);

	}

	@Override
	public void deleteFacture(Long id) {
		facturerepo.deleteById(id);		
	}

	@Override
	public Facture UpdateFacture(Facture f) {
		return facturerepo.save(f);
	}

	@Override
	public  float getChiffreAffaireParCategorieClient(CategorieClient categorieClient, Date startDate, Date endDate) {
		return  facturerepo.getChiffreAffaireParCategorieClient(categorieClient,startDate,endDate);
		
	}
	
	@Override
	public  int UpdateModePaiement(Long idfacture, String Modepaiement) {
		return  facturerepo.UpdateModePaiement(idfacture,Modepaiement);
		
	}

	@Override
	public List<Facture> getFacturesHistoriqueClient(Long idClient) {
		return 	facturerepo.getFacturesHistoriqueClient(idClient);
	}

	
	
	@Override
	public float getChiffreaffairetoday() {
		 Date date = new Date();
		 
		return  facturerepo.getChiffreaffairetoday(date);

		
		
	}

	
	@Override
	public float getNbFactureLastMonth() {
        Date date = new Date();
        
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        
        Date currentDatePlusOne = c.getTime();


			return  facturerepo.getNbFactureLastMonth(currentDatePlusOne,date);

	}

	@Override
	public float getNbFactureBetweenSpecifiedDates(Date startDate, Date endDate) {
		
		return  facturerepo.getNbFactureBetweenSpecifiedDates(startDate,endDate);
	}

	@Override
	public float getChiffreaffaireLastMonth() {
		  Date date = new Date();
	        
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.add(Calendar.MONTH, -1);
	        
	        Date currentDatePlusOne = c.getTime();


				return  facturerepo.getChiffreaffaireLastMonth(currentDatePlusOne,date);
	}

	
	
	@Override
	public void Closefacture(Facture fac) {
		
		Long id=fac.getIdFacture();
		
		facturerepo.Closefacture(id);

				
	}


	
	

}
