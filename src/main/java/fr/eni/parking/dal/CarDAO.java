/**
 * 
 */
package fr.eni.parking.dal;

import org.springframework.data.repository.CrudRepository;

import fr.eni.parking.bo.Car;

/**
 * DAO for the Car
 * @author ramona
 *
 */
public interface CarDAO extends CrudRepository<Car, Integer> {

}
