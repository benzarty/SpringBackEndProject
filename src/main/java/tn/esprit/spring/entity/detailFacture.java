package tn.esprit.spring.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "detailFacture")
public class detailFacture  implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="iddetailFacture")
	private Long iddetailFacture; // Clé primaire
	private Integer qte;
	private float prixtotal;
	private Integer pourcentageRemise;
	private float montantRemise;
	
	
	
	
	
	@ManyToOne
	@JsonIgnore
	private Produit Totheparentdetailfacture; 
	
	
	@ManyToOne
	@JsonIgnore
	private Facture facturechildren; 
	
	

}
