package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entity.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {

}
