package fr.eni.parking.bll;

import java.util.List;

import fr.eni.parking.bo.Car;
import fr.eni.parking.bo.Parking;
import fr.eni.parking.bo.Ticket;

/**
 * Interface for managing a parking :
 * 	- generate parking tickets
 * 	- add cars in DB if they don't exist
 * 	- add, modify, delete and retrieve parkings
 * 	- calculate the price of the tickets
 * @author ramona
 *
 */
public interface ParkingManager {
	/**
	 * create a new parking
	 * @param parking
	 */
	public void addParking(Parking parking);
	/**
	 * update a parking
	 * @param parking
	 */
	public void updateParking(Parking parking);
	/**
	 * delete a parking
	 * @param parking
	 */
	public void deleteParking(Parking parking);
	/**
	 * get a list of all parkings
	 * @return
	 */
	public List<Parking> getAllParkings();
	/**
	 * get a parking by id
	 * @param id
	 * @return the parking
	 */
	public Parking getParkingById(Integer id);
	/**
	 * create a new car if it doesn't exist in DB
	 * @param car
	 */
	public void addCar(Car car);
	/**
	 * generate a ticket for the arrival of a car
	 * @param car
	 * @param parking
	 * @return the parking ticket
	 */
	public Ticket generateTicket(Car car, Parking parking);
	/**
	 * calculate the price to pay for the parking
	 * @param ticket
	 * @return the total amount to be paid by the car owner
	 */
	public Double calculateRate(Ticket ticket);
}
