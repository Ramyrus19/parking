package fr.eni.parking.bo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Parking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank(message = "Address cannot be empty !")
	private String address;
	@NotNull(message = "Please provide a number of places")
	private Integer places;
	@NotNull(message = "Please provide an hourly rate")
	private Double rateByHour;
	
	@OneToMany(mappedBy = "parking")
	@JsonBackReference
	private List<Ticket> tickets;
	
	private Double turnover;
	
	/**
	 * @param address
	 * @param places
	 * @param rate
	 */
	public Parking(String address, Integer places, Double rateByHour) {
		super();
		this.address = address;
		this.places = places;
		this.rateByHour = rateByHour;
		this.turnover = 0.0;
	}
	
	
}
