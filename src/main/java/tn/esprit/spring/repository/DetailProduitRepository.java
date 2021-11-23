package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.DetailProduit;

@Repository
public interface DetailProduitRepository  extends CrudRepository<DetailProduit, Long>{

}
