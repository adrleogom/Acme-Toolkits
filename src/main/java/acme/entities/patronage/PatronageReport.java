package acme.entities.patronage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PatronageReport extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------
	
	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------

	@Digits(integer=4, fraction=0)
	@Min(1)
	protected Integer			sNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date				creationMoment;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String 			memorandum;
	
	@URL
	protected String			furtherInfo;
	
	// Derived attributes -----------------------------------------------------
	public String sequenceNumber() {
		return this.patronage.getCode() + ":" + this.sNumber;
	}
	
	
	// Relationships ----------------------------------------------------------
	@NotNull
	@Valid  
	@ManyToOne(optional=false)
	protected Patronage			patronage;
	
}
