package tn.esprit.spring.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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

	

	//@min and @max is for numbers and string. Size is only for strings
	//@Min(1) //  "qte": "must be greater than or equal to 1",
	//@Max(9999) // return   "qte": "must be less than or equal to 9999",
	//@Range(min = 1, max=9999 )  //return "qte": "must be between 1 and 9999"
	
	@NotNull
	//@Size(min = 1, max = 9999)
	
	@Min(1)
	@Max(9999)
	//@Pattern(regexp ="^[1-9][0-9]*$")
	private Integer qte;
	
	//The below lines wil lbe executed one at the time how ever the one above is much simpler
	
	@NotNull
	//The below lines will be executed one at the time. However, the one above is much simpler (@Size)
	@Min(1) // return "qteMin": "must be greater than or equal to 1"
	@Max(9999) // return   "qteMin": "must be less than or equal to 9999"
	private Integer qteMin;
	

	
	@Size(min=3, max=30)
	@NotBlank(message="This field must not be empty")
	//@Column(nullable = false, length=30)
	private String libelleStock;
	
	@ColumnDefault(value="CURRENT_DATE")
	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.DATE)
    private Date dateDerniereModification;
	
	////@JsonIgnore
	@OneToMany(mappedBy="stockproduit")
	private Set<Produit> stockproduittt;
	

	//Not null does not see to work within the database since I don't want to persist a null value
	//Secondly I would like to show a message for numbers the same as strings
	//This type of validation is called "Persistence layer validation", it is not recommended since this is the last line of defense that is why it need to be handled in the rest controller


	



	
	
	
}