/**
 * 
 */
package fr.eni.parking.ws;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.parking.bll.CarManager;
import fr.eni.parking.bll.ParkingManager;
import fr.eni.parking.bll.TicketManager;
import fr.eni.parking.bll.TicketManagerException;
import fr.eni.parking.bo.Car;
import fr.eni.parking.bo.Parking;
import fr.eni.parking.bo.Ticket;

/**
 * @author ramon
 *
 */
@Component
public class InitDatabase {
	@Autowired
	private ParkingManager parkingManager;
	
	@Autowired
	private CarManager carManager;
	
	@Autowired
	private TicketManager ticketManager;
	
	@PostConstruct
	private void init() throws IOException, TicketManagerException {
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream("/application.properties"));
		if ("create".equals(properties.getProperty("spring.jpa.hibernate.ddl-auto"))) {
			Parking resistance = new Parking("Place de la Resistance", 2, 2.50);
			Parking tourbie = new Parking("Place de la Tourbie", 80, 2.30);
			parkingManager.addParking(resistance);
			parkingManager.addParking(tourbie);
			
			Car peugeot = new Car("AB123CD", "Peugeot", "207");
			Car renault = new Car("EF456GH", "Renault", "Captur");
			Car citroen = new Car("IJ789KL", "Citroën", "DS4");
			carManager.addCar(peugeot);
			carManager.addCar(renault);
			carManager.addCar(citroen);
			
//			Ticket ticket1 = ticketManager.generateTicket(peugeot, resistance, LocalDateTime.parse("2021-04-30T09:55:30"));			
//			Ticket ticket2 = ticketManager.generateTicket(renault, resistance, LocalDateTime.parse("2021-04-30T12:30:36"));
			
			
			
		}
	}
}
