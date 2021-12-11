package tn.esprit.spring.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Profession;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.FactureRepository;
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

	
	@Autowired
	ClientRepository clientRepo;

	
	@Autowired
	FactureRepository factureRepo;
	
	@Autowired 
	FactureServiceImpl FactureServiceImpl;

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public List<Client> retrieveAllClients() {
		return (List<Client>) this.clientRepo.findAll();
		
	}

	@Override
	public Client addClient(Client c) {
		return this.clientRepo.save(c);
	}

	@Override
	public void deleteClient(Long id) {
		 this.clientRepo.deleteById(id);
	}

	@Override
	public Client updateClient(Client c) {
		return this.clientRepo.save(c);
	}

	@Override
	public Client retrieveClient(Long id) {
		return this.clientRepo.findById(id).orElse(null);
	}

	@Override
	public List<Client> getClientsWhereDateBetween(Date d1, Date d2) {
		
		return this.clientRepo.retrieveClientsByDateNaissance(d1, d2);
	}
	//do not forget to intall sonar plugin

	
	
	@Override
	public Set<Facture> getFacturesByClient(Long idClient) {
		Client c = new Client();
		c = clientRepo.findById(idClient).orElse(null);
		Set<Facture> myset = new HashSet<Facture>();
		//System.out.println("Get facture");
		//System.out.println("Size"+c.getFactures().size());
		return  c.getFactures();
	}
	
	
	
	
	@Override
	public float getChiffreAffaireParCategorieClient(CategorieClient categorieClient, Date startDate, Date endDate) {
		return this.factureRepo.getChiffreAffaireParCategorieClient(categorieClient, startDate, endDate);
	}

//	@Override
//	public float getChiffreAffaireParProfessionClient(Profession profession, Date startDate, Date endDate) {
//		
	//	return this.factureRepo.getChiffreAffaireParProfessionClient(profession, startDate, endDate);
//	}
	

	@Override
	public void SendMail(Facture facture) {
		
		Long id=facture.getIdFacture();
		
		Facture fac=FactureServiceImpl.retrieveFacture(id);
		
		Client client=fac.getClientFacure();
		
		
			float sommetotal=fac.getMontantFacture();
		
		
		String mailto=client.getEmail();
		
		
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("m.benzarti.1996@gmail.com");
		message.setTo(mailto);
		
		message.setText("Good Morning,Your purchase has been approvad ,you have to pay this ammount to proceed : " +sommetotal+". Unitl Further Notice " );
		message.setSubject("Purchase");
		
		mailSender.send(message);
	}
	

	







	
	
	
	
		
	

}
