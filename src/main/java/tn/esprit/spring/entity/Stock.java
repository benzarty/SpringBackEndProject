package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

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
@Table( name = "Stock")
public class Stock implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	//@NotBlank(message="")
	@Column(name="idStock")
	private Long idStock;
	
	@NotNull
	//@Size(min = 1, max = 9999)
	@Min(1)
	@Max(9999)
	private Integer qte;
	
	@NotNull
	@Min(1)
	@Max(9999)
	private Integer qteMin;
	
	@NotNull
	@Size(min=3, max=30)
	//@Column(nullable = false, length=30)
	private String libelleStock;
	
	@ColumnDefault(value="CURRENT_DATE")
	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@Temporal(TemporalType.DATE)
    private Date dateDerniereModification;
	
	
	
	@OneToMany(mappedBy="stockproduit")
	private Set<Produit> stockproduittt;
	




	



	
	
	
}