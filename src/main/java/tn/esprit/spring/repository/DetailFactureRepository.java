package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.detailFacture;


@Repository
public interface DetailFactureRepository extends JpaRepository<detailFacture, Long> {
	
	

}
