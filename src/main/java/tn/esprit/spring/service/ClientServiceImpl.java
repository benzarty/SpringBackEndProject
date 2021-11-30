package tn.esprit.spring.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.repository.ClientRepository;
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

	
	@Autowired
	ClientRepository clientrepo; 

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







	
	
	
	
		
	

}
