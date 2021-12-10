package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.service.ClientServiceImpl;

@RestController
@RequestMapping("/client")
public class ClientController {

@Autowired
ClientServiceImpl clientService;

// http://localhost:8089/SpringMVC/client/retrieve-all-clients
@ApiOperation(value = "Récupérer la liste des clients")
@GetMapping("/retrieve-all-clientsss")
@ResponseBody
public List<Client>getClientsss() {
List<Client> listClients = clientService.retrieveAllClients();
return listClients;
}


//http://localhost:8089/SpringMVC/client/getClientWithdate
@ApiOperation(value = "Récupérer la liste des clients")
@GetMapping("/getClientWithdate/{d1}/{d2}")
@ResponseBody
public List<Client>getClientWithdate(@PathVariable(name = "d1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d1,@PathVariable(name = "d2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d2) {
List<Client> listClients = clientService.getClientWithDate(d1,d2);
return listClients;
}




//http://localhost:8089/SpringMVC/client/retrieve-client/8
@GetMapping("/retrieve-client/{client-id}")
@ResponseBody
public Client retrieveClient(@PathVariable("client-id") Long clientId) {
return clientService.retrieveClient(clientId);
}

// http://localhost:8089/SpringMVC/client/add-client
@PostMapping("/add-client")
@ResponseBody
public Client addClient(@RequestBody Client c)
{
Client client = clientService.addClient(c);
return client;
}



//http://localhost:8089/SpringMVC/client/remove-client/{client-id}
@DeleteMapping("/remove-client/{client-id}")
@ResponseBody
public void removeClient(@PathVariable("client-id") Long clientId) {
clientService.deleteClient(clientId);
}



//http://localhost:8089/SpringMVC/client/modify-client
@PutMapping("/modify-client")
@ResponseBody
public Client modifyClient(@RequestBody Client client) {
return clientService.updateClient(client);
}




// http://localhost:8089/SpringMVC/client/add-client
@PostMapping("/SendMail")
@ResponseBody
public void SendMail(@RequestBody Facture facture)
{
	
	
	clientService.SendMail(facture);

   
}	








}
