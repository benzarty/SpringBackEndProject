package tn.esprit.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.domaine.Response;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.RayonRepository;
import tn.esprit.spring.repository.StockRepository;
import tn.esprit.spring.service.APIResponse;
import tn.esprit.spring.service.ProduitServiceImpl;
import tn.esprit.spring.service.RayonServiceImpl;












@RestController
@CrossOrigin(origins = "http://localhost:8750")
@RequestMapping("/Produit")
public class ProduitController {

	@Autowired
	ProduitServiceImpl serviceproduit;

	@Autowired
	ProduitRepository repoproduit;
	@Autowired  ServletContext context;
	@Autowired
	StockRepository repostock;

	@Autowired
	RayonRepository reporepo;
	@Autowired
	RayonServiceImpl rayonservice;


	// http://localhost:8089/SpringMVC/client/retrieve-all-clients
	@GetMapping("/retrieve-all-produits")
	@ResponseBody
	public List<Produit> getProduits() {
	List<Produit> listproduits = serviceproduit.retrieveAllProduits();
	return listproduits; 
	}
	
	// http://localhost:8089/SpringMVC/client/retrieve-client/8
			@GetMapping("/retrieve-produit/{produit-id}")
			@ResponseBody
			public Produit retrieveProduit(@PathVariable("produit-id") Long produitId) {
			return serviceproduit.retrieveProduit(produitId);
			}
	
	
		
			
			
	// http://localhost:8089/SpringMVC/Produit/modify-produit
	@PutMapping("/modify-produit")
	@ResponseBody
	public Produit modifyProduit(@RequestBody Produit produit) {
		return serviceproduit.updateproduit(produit);
	}

	


	// http://localhost:8089/SpringMVC/client/remove-client/{client-id}
			@DeleteMapping("/remove-produit/{produit-id}")
		     @ResponseBody
			public void removeProduit(@PathVariable("produit-id") Long produitId) {
				serviceproduit.deleteproduit(produitId);
			}
			
			
			 @GetMapping("/pagination/{offset}/{pageSize}")
			    private APIResponse<Page<Produit>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
			        Page<Produit> productsWithPagination = serviceproduit.findProductsWithPagination(offset, pageSize);
			        return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
			    }
			 
				// http://localhost:8089/SpringMVC/Produit/assignProduitToStockgogo
				@PutMapping("/assignProduitToStockgo/{idProduit}/{stockproduit}")
				@ResponseBody
				public void assignProduitToStockgo(@PathVariable("idProduit") Long produitId,@PathVariable("stockproduit") Long stockproduit)
				{
					 serviceproduit.assignProduitToStockgo(produitId, stockproduit);
					
				}
				

				@GetMapping("/trier/{field}")
			    private APIResponse<List<Produit>> getProductsWithSort(@PathVariable String field) {
			        List<Produit> allProducts = serviceproduit.findProductsWithSorting(field);
			        return new APIResponse<>(allProducts.size(), allProducts);
			    }
				@PutMapping("/affecterProduitToimage/{idProduit}/{stringfile}")
				@ResponseBody
				public void affecterProduitToFournisseur(@PathVariable("idProduit") Long idProduit,@PathVariable("stringfile") String file)
				{
					serviceproduit.afecterProduitimage(idProduit, file);
					
				} 
				// http://localhost:8089/SpringMVC/client/add-client
				@PostMapping("/add-produit/{idRayon}/{idStock}")
				@ResponseBody
				public Produit addProduit(@RequestBody Produit p ,@PathVariable("idRayon") Long idRayon, @PathVariable("idStock") Long idStock)
				{
					Produit pro = serviceproduit.addProduit(p, idRayon , idStock);
				return pro;
				}
				
				@PostMapping("/save-produit1/")
				//@ResponseBody
				public String saveProduit( Produit p1, @RequestParam("fileImage") MultipartFile multipartfile  ) throws IOException {
					
					 String fileName = StringUtils.cleanPath(multipartfile.getOriginalFilename());
				        p1.setFileName(fileName);
				        Produit savedprod =  repoproduit.save(p1);
				        String uploadDir = "/produit-photos/" + savedprod.getIdProduit();
				        Path uploadpath = Paths.get(uploadDir);
				        //FileUploadUtil.saveFile(uploadDir, fileName, multipartfile);
					if (!Files.exists(uploadpath)) {
						Files.createDirectories(uploadpath);
					}
					try (InputStream inputStream = multipartfile.getInputStream()) {
			            Path filePath = uploadpath.resolve(fileName);
			            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			        } catch (IOException ioe) {        
			            throw new IOException("Could not save image file: " + fileName, ioe);
			        }  
					return "ok succes";
				}

				
	
	
	
	
	
	
	

	
	
		//a verifier fil dar 	
	
	@PostMapping("/createproduitfile")
	@ResponseBody
	public Produit createProduitavecimage(@RequestBody Produit p1, @RequestBody String file) {
		
			Produit p=serviceproduit.addProduitfile(p1, file);
			return p;
		

	}
	

	
	@PostMapping("/create-produit/{idRayon}/{idStock}")
	@ResponseBody
	public Produit createProduit(@RequestBody Produit p1,@PathVariable Long idRayon,@PathVariable Long idStock) {
		
			Produit p=serviceproduit.addProduit(p1, idRayon, idStock);
			return p;
		

	}
	
	
	
	




	
	
	


	
	
	
	
	
	
	


	
	

	
	
	
	
	
	 @RequestMapping(value ="/file",  method = RequestMethod.POST ,  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	 public ResponseEntity<Response> createproduit (@RequestParam("file") MultipartFile file,
			 @RequestParam("produit") String produit) throws JsonParseException , JsonMappingException , Exception
	 {
		 System.out.println("Ok .............");
		 Produit arti = new ObjectMapper().readValue(produit, Produit.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
        	new File (context.getRealPath("/Images/")).mkdir();
        	System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
        	System.out.println("Image");
        	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
        	 
        }catch(Exception e) {
        	e.printStackTrace();
        }

       
        arti.setFileName(newFileName);
        Produit art = repoproduit.save(arti);
        if (art != null)
        {
        	return new ResponseEntity<Response>(new Response (),HttpStatus.OK);
        }
        else
        {
        	return new ResponseEntity<Response>(new Response (),HttpStatus.BAD_REQUEST);	
        }
	 }
	
	

}
