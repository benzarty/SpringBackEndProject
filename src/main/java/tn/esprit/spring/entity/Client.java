package tn.esprit.spring.entity;



import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//lezem entity w serializable w @id kahaw
//non serializable il y a yne chance de perte de donnée
//ken te7otech GenerationType y7ot auto(table sequence)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Client")
public class Client implements Serializable {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
@Column(name="idClient")
private Long idClient; // Clé primaire
private String nom; 
private String prenom; 
private String email; 
private String password; 
@Temporal(TemporalType.DATE)
private Date dateNaissance; //util import
@Enumerated(EnumType.STRING)
private Profession profession;
@Enumerated(EnumType.STRING)
private CategorieClient categorieClient;



@OneToMany(cascade = CascadeType.ALL, mappedBy="ClientFacure")
private Set<Facture> factures;





}