/**
 * 
 */
package fr.eni.parking.dal;

import org.springframework.data.repository.CrudRepository;

import fr.eni.parking.bo.Ticket;

/**
 * DAO foe the Ticket
 * @author ramona
 *
 */
public interface TicketDAO extends CrudRepository<Ticket, Integer> {

}
