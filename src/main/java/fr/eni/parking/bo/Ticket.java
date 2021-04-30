package fr.eni.parking.bo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	@NotNull(message = "Please select a car")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name = "parking_id")
	@NotNull(message = "Please choose a parking")
	private Parking parking;
	
	private Double total;
	
	private Boolean status;
	
	private LocalDateTime arrivedAt;
	
	private LocalDateTime exitAt;

	/**
	 * @param car
	 * @param parking
	 * @param arrivedAt
	 */
	public Ticket(Car car, Parking parking, LocalDateTime arrivedAt) {
		super();
		this.car = car;
		this.parking = parking;
		this.arrivedAt = arrivedAt;
	}
	
	
}
