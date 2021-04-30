/**
 * 
 */
package fr.eni.parking.bll;

import java.util.List;

import fr.eni.parking.bo.Car;
import fr.eni.parking.bo.Parking;

/**
 * Interface for managing a car :
 * 	- add, modify, delete and retrieve cars
 * @author ramona
 *
 */
public interface CarManager {
	/**
	 * get a list of all cars
	 * @return
	 */
	public List<Car> getAllCars();
	
	/**
	 * get a list of all cars from a parking
	 * @return a list of cars
	 */
	public List<Car> getAllCarsFromParking(Parking parking);
	
	/**
	 * get a car by id
	 * @param id
	 * @return the car
	 */
	public Car getCarById(Integer id);
	/**
	 * create a new car if it doesn't exist in DB
	 * @param car
	 */
	public void addCar(Car car);
}
