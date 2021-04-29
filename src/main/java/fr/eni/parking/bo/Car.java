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
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String licence;
	private String brand;
	private String model;
	
	@OneToMany(mappedBy = "car")
	private List<Ticket> tickets;
	
	/**
	 * @param licence
	 * @param brand
	 * @param model
	 */
	public Car(String licence, String brand, String model) {
		super();
		this.licence = licence;
		this.brand = brand;
		this.model = model;
	}
	
	
}
