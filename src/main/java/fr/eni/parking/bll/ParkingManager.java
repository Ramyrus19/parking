package fr.eni.parking.bll;

import java.util.List;

import fr.eni.parking.bo.Parking;

/**
 * Interface for managing a parking :
 * 	- add, modify, delete and retrieve parkings
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
	 * delete parking by id
	 * @param id
	 */
	public void removeParkingFromId(Integer id);
	
}
