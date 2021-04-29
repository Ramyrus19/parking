package fr.eni.parking.bo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Parking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String address;
	private Integer places;
	private Double rate;
	
	@OneToMany(mappedBy = "parking")
	private List<Ticket> tickets;
	
	/**
	 * @param address
	 * @param places
	 * @param rate
	 */
	public Parking(String address, Integer places, Double rate) {
		super();
		this.address = address;
		this.places = places;
		this.rate = rate;
	}
	
	
}
