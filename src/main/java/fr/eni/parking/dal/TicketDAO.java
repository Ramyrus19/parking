/**
 * 
 */
package fr.eni.parking.dal;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.eni.parking.bo.Parking;
import fr.eni.parking.bo.Ticket;

/**
 * DAO foe the Ticket
 * @author ramona
 *
 */
public interface TicketDAO extends CrudRepository<Ticket, Integer> {

	@Query("select t from Ticket t where t.car.licence = :licence")
	List<Ticket> findByLicencePlate(@Param("licence") String licence);
	
	@Query("select t from Ticket t where t.parking = :parking and t.status=true")
	List<Ticket> findActiveTicketsByParking(@Param("parking") Parking parking);

}
