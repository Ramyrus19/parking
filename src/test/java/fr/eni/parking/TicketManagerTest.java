package fr.eni.parking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.parking.bll.CarManager;
import fr.eni.parking.bll.ParkingManager;
import fr.eni.parking.bll.TicketManager;
import fr.eni.parking.bo.Car;
import fr.eni.parking.bo.Parking;
import fr.eni.parking.bo.Ticket;

@SpringBootTest
public class TicketManagerTest {
	@Autowired
	ParkingManager parkingManager;
	
	@Autowired 
	CarManager carManager;
	
	@Autowired
	TicketManager ticketManager;
	
	@Test
	@Transactional
	void generateTicketTest() {
		Parking resistance = new Parking("Place de la Resistance", 50, 2.50);
		parkingManager.addParking(resistance);
		
		Car peugeot = new Car("AB123CD", "Peugeot", "207");
		carManager.addCar(peugeot);
		
		Ticket t = ticketManager.generateTicket(peugeot, resistance, LocalDateTime.now());
		
		Ticket fromManager = ticketManager.getTicketById(t.getId());
		
		assertNotNull(fromManager);
	}
	
	@Test
	@Transactional
	void calculateRateTest() {
		Parking resistance = new Parking("Place de la Resistance", 50, 2.50);
		parkingManager.addParking(resistance);
		
		Car peugeot = new Car("AB123CD", "Peugeot", "207");
		carManager.addCar(peugeot);
		
		Ticket ticket = ticketManager.generateTicket(peugeot, resistance, LocalDateTime.parse("2021-04-30T08:00:00"));
		ticket.setExitAt(LocalDateTime.now());
		Double total = ticketManager.calculateTotal(ticket);
		
		assertEquals(total, 5);
	}

}
