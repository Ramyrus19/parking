package fr.eni.parking.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.parking.bll.CarManager;
import fr.eni.parking.bll.ParkingManager;
import fr.eni.parking.bll.TicketManager;
import fr.eni.parking.bo.Car;
import fr.eni.parking.bo.Parking;
import fr.eni.parking.bo.Ticket;

@RestController
public class ParkingRest {
	@Autowired
	ParkingManager parkingManager;
	
	@Autowired
	TicketManager ticketManager;
	
	@Autowired
	CarManager carManager;
	
	@GetMapping("/ws/tickets/{licence}")
	public List<Ticket> getAllByCar(@PathVariable String licence){
		return ticketManager.getByLicencePlate(licence);
	}
	
	@GetMapping("/ws/parkings")
	public List<Parking> getAllParkings(){
		return parkingManager.getAllParkings();
	}
	
	@GetMapping("/ws/parkings/{id}/cars")
	public List<Car> getAllCarsFromParking(@PathVariable Integer id){
		Parking p = parkingManager.getParkingById(id);
		return carManager.getAllCarsFromParking(p);
	}
}
