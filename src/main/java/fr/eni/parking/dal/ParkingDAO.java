/**
 * 
 */
package fr.eni.parking.dal;

import org.springframework.data.repository.CrudRepository;

import fr.eni.parking.bo.Parking;

/**
 * DAO for the Parking
 * @author ramona
 *
 */
public interface ParkingDAO extends CrudRepository<Parking, Integer> {

}
