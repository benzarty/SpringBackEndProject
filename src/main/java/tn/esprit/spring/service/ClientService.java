package tn.esprit.spring.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;



import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Profession;

public interface ClientService {

	List<Client> retrieveAllClients();

	Client addClient(Client c);

	void deleteClient(Long id);

	Client updateClient(Client c);

	Client retrieveClient(Long id);
	
	List<Client> getClientsWhereDateBetween(Date d1, Date d2);
	
	Set<Facture> getFacturesByClient(Long idClient);
	
    float getChiffreAffaireParCategorieClient(CategorieClient categorieClient, Date startDate, Date endDate); 
    
    
  //  float getChiffreAffaireParProfessionClient(Profession profession, Date startDate, Date endDate); 
	
	
	void SendMail(Facture facture);



}
