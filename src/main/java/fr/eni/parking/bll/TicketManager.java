/**
 * 
 */
package fr.eni.parking.bll;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.parking.bo.Car;
import fr.eni.parking.bo.Parking;
import fr.eni.parking.bo.Ticket;

/**
 * Interface for managing a parking :
 *  - CRUD ticket
 * 	- generate parking tickets
 * 	- calculate the price of the tickets
 * @author ramona
 *
 */
public interface TicketManager {
	/**
	 * get a ticket by id
	 * @param id
	 * @return the ticket
	 */
	public Ticket getTicketById(Integer id);
	/**
	 * generate a ticket for the arrival of a car
	 * @param car
	 * @param parking
	 * @return the parking ticket
	 * @throws TicketManagerException 
	 */
	public Ticket generateTicket(Car car, Parking parking, LocalDateTime arrivedAt) throws TicketManagerException;
	/**
	 * update a ticket
	 * @param ticket
	 */
	public void updateTicket(Ticket ticket);
	/**
	 * get a list of all tickets
	 * @return
	 */
	public List<Ticket> getAllTickets();
	/**
	 * get a list of active tickets by parking
	 * @return
	 */
	public List<Ticket> getActiveTicketsByParking(Parking parking);
	/**
	 * get tickets by car licence plate
	 * @param id
	 * @return the tickets
	 */
	public List<Ticket> getByLicencePlate(String licence);
	
	
	/**
	 * calculate the price to pay for the parking
	 * @param ticket
	 * @return the total amount to be paid by the car owner
	 */
	public Double calculateTotal(Ticket ticket);
	/**
	 * delete ticket by id
	 * @param id
	 */
	public void removeTicketFromId(Integer id);
}
