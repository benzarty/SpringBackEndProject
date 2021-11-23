package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.repository.RayonRepository;
@Service
public class RayonServiceImpl implements RayonService {

	
	@Autowired
	RayonRepository rayonrepo;
	
	
	@Override
	public Rayon addRayon(Rayon r) {
		// TODO Auto-generated method stub
		return rayonrepo.save(r);
	}

	@Override
	public Rayon retrieveRayon(Long id) {
		// TODO Auto-generated method stub
		return rayonrepo.findById(id).orElse(null);
	}

	@Override
	public List<Rayon> retrieveAllRayons() {
		 List<Rayon> rayons=(List<Rayon>)rayonrepo.findAll() ;
		 return rayons;
	}

	@Override
	public void deleteRayon(Long id) {
		rayonrepo.deleteById(id);
		
	}

	@Override
	public Rayon updateRayon(Rayon c) {
		return rayonrepo.save(c);
	}

}
