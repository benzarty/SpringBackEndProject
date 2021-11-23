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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Facture implements Serializable {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idFacture; 
	private float montantRemise;
	private float montantFacture;
	@Temporal(TemporalType.DATE)
    private Date datefacture;
	private boolean active;
	
	
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="facturechildren")
	@JsonIgnore
	private Set<detailFacture> detailFacture;
	
	
	
	
	
	@ManyToOne
	@JsonIgnore
	private Client ClientFacure; 
	

}
