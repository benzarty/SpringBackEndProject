package tn.esprit.spring.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.FactureRepository;
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

	
	@Autowired
	ClientRepository clientrepo; 
	
	@Autowired
	FactureRepository facrepo;
	
	@Autowired 
	FactureServiceImpl FactureServiceImpl;

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public List<Client> retrieveAllClients() {
		 List<Client> clients=(List<Client>)clientrepo.findAll() ;
		 for(Client client: clients)
		 {
			 log.info("Client"+client);
			 //bech 5aliw trace 3la 5edma mete3ena
				//starter web hiya bech negedou biha exposition service

		 }
		 
		 return clients;
	}
	
	

	@Override
	public Client addClient(Client c) {
		return clientrepo.save(c);
		
	}

	@Override
	public void deleteClient(Long id) {
		clientrepo.deleteById(id);

	}

	@Override
	public Client updateClient(Client c) {
		
		return clientrepo.save(c);
	}

	@Override
	public Client retrieveClient(Long id) {
		
		return clientrepo.findById(id).orElse(null);
	}

	@Override
	public List<Client> getClientWithDate(Date d1 ,Date d2) {
		// TODO Auto-generated method stub
		return clientrepo.retrieveClientsByDateNaissance( d1,d2);

	}



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
