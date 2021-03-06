package acme.entities.patronage;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------
	
	@NotNull
	protected Status status;
	
	@Column(unique=true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$",message = "Must follow the patron [A-Z]{3}-[0-9]{3}(-[A-Z])")
	@NotBlank
	protected String code;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String legalStuff;
	
	@Valid
	protected Money budget;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				initialDate;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				finalDate;
	
	@URL
	protected String furtherInfo;
	
	protected boolean			published;

	// Derived attributes -----------------------------------------------------


	// Relationships ----------------------------------------------------------
	@ManyToOne(optional=false)
	@Valid
	@NotNull
	protected Patron			patron;

	@ManyToOne(optional=false)
	@Valid
	@NotNull
	protected Inventor			inventor;
}