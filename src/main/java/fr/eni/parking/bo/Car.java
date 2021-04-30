package fr.eni.parking.bo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank(message = "Please provide a licence number")
	private String licence;
	@NotBlank(message = "Please provide a car brand")
	private String brand;
	@NotBlank(message = "Please provide a model")
	private String model;
	
	@OneToMany(mappedBy = "car")
	@JsonManagedReference
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

	@Override
	public String toString() {
		return "Car [id=" + id + ", licence=" + licence + ", brand=" + brand + ", model=" + model + "]";
	}
	
	
}
