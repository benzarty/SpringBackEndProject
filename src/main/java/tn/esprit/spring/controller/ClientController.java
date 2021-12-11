package tn.esprit.spring.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Profession;
import tn.esprit.spring.service.ClientService;
import tn.esprit.spring.service.ClientServiceImpl;

@RestController
@RequestMapping("/client")
public class ClientController {


DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");

@Autowired
ClientService clientService;

@ApiOperation(value="Get every single client")

// http://localhost:8075/SpringMVC/client/retrieve-all-clients
@GetMapping("/retrieve-all-clients")
@ResponseBody
public List<Client> getClients() {
List<Client> listClients = clientService.retrieveAllClients();
return listClients;
}


// http://localhost:8075/SpringMVC/client/retrieve-client/8
@ApiOperation(value="Get a single client using his ID with type Long")
@GetMapping("/retrieve-client/{client-id}")
@ResponseBody
public Client retrieveClient(@PathVariable("client-id") Long clientId) {
return clientService.retrieveClient(clientId);
}




// http://localhost:8075/SpringMVC/client/add-client
//@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
@ApiOperation(value="Adding client - JSON format - Unset fields will be set to NULL")
@PostMapping("/add-client")
@ResponseBody
public Client addClient(@RequestBody Client c)
{ 
	return clientService.addClient(c);
}

// http://localhost:8075/SpringMVC/client/modify-client
@ApiOperation(value="Update client using Client as an object - You must send as an object - Sending only the ID will not work")
@PutMapping("/modify-client")
@ResponseBody
public Client modifyClient(@RequestBody Client client) {
return clientService.updateClient(client);
}

// http://localhost:8075/SpringMVC/client/remove-client/{client-id}
@ApiOperation(value="Delete client using his ID")
@DeleteMapping("/remove-client/{client-id}")
@ResponseBody
public void removeClient(@PathVariable("client-id") Long clientId) {
clientService.deleteClient(clientId);
}

@ApiOperation(value="Get invoices of a client under a specific ID")
@GetMapping("/retrieve-invoices-of-client/{client-id}")
@ResponseBody
public Set<Facture> getInvoicesByClient(@PathVariable("client-id") Long clientId) {
	return clientService.getFacturesByClient(clientId);

}


@ApiOperation(value="Get Client By birth date interval")
@PostMapping("/retrieve-client/{start-date}/{end-date}")
@ResponseBody
public List<Client> getClientWhereDateBetween(@PathVariable("start-date")  String startDate, @PathVariable("end-date") String endDate ) throws ParseException {
	//Check out the use of
	//@DateTimeFormat(pattern = "yyyy-MM-dd") 
	Date  startDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
	Date  endDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
	
	return clientService.getClientsWhereDateBetween(startDate1, endDate1);

}

//Try to repeat this message using the client=>Facture
@ApiOperation(value="Get The Ammount Spent By A Client Between 2 specific dates")
@PostMapping("/retrieve-client-expenditure/{categorie-client}/{start-date}/{end-date}")
@ResponseBody
public float getChiffreAffaireParCategorieClient(@PathVariable("categorie-client") CategorieClient categorieClient, @PathVariable("start-date") String startDate, @PathVariable("end-date") String endDate ) throws ParseException {
	Date  startDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
	Date  endDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
	
	return  clientService.getChiffreAffaireParCategorieClient(categorieClient, startDate1, endDate1);
}


//Try to repeat this message using the client=>Facture
//	@ApiOperation(value="Get The Ammount Spent By A Profseeion Client Between 2 specific dates")
//	@PostMapping("/retrieve-client-expenditure/{profession}/{start-date}/{end-date}")
//	@ResponseBody
//public float getChiffreAffaireParProfessionClient(@PathVariable("profession") Profession profession, @PathVariable("start-date") String startDate, @PathVariable("end-date") String endDate ) throws ParseException {
	//	Date  startDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
	//	Date  endDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
	//	
//		return  clientService.getChiffreAffaireParProfessionClient(profession, startDate1, endDate1);
//	}



// http://localhost:8089/SpringMVC/client/add-client
@PostMapping("/SendMail")
@ResponseBody
public void SendMail(@RequestBody Facture facture)
{
      
	clientService.SendMail(facture);

}	








}
