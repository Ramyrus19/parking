/**
 * 
 */
package fr.eni.parking.bll;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.parking.bo.Car;
import fr.eni.parking.bo.Parking;
import fr.eni.parking.bo.Ticket;
import fr.eni.parking.dal.CarDAO;
import fr.eni.parking.dal.ParkingDAO;
import fr.eni.parking.dal.TicketDAO;

/**
 * Implementation of the ParkingManager
 * @author ramona
 *
 */

@Service
public class ParkingManagerImpl implements ParkingManager {
	
	@Autowired
	private CarDAO carDAO;
	
	@Autowired
	private TicketDAO ticketDAO;
	
	@Autowired
	private ParkingDAO parkingDAO;

	@Override
	@Transactional
	public void addParking(Parking parking) {
		parkingDAO.save(parking);
	}

	@Override
	@Transactional
	public void updateParking(Parking parking) {
		parkingDAO.save(parking);
	}

	@Override
	@Transactional
	public void deleteParking(Parking parking) {
		parkingDAO.delete(parking);
	}

	@Override
	public List<Parking> getAllParkings() {
		return (List<Parking>) parkingDAO.findAll();
	}

	@Override
	public Parking getParkingById(Integer id) {
		return parkingDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void addCar(Car car) {
		carDAO.save(car);
	}

	@Override
	@Transactional
	public Ticket generateTicket(Car car, Parking parking) {
		Ticket ticket = new Ticket(car, parking, LocalDateTime.now());
		ticketDAO.save(ticket);
		return ticket;
	}

	@Override
	public Double calculateRate(Ticket ticket) {
		//TODO : calculate the total to pay 
		//ticket.getParking().getRate() * ticket.getCreatedAt().getHour()-now().getHour()
		return null;
	}

}
