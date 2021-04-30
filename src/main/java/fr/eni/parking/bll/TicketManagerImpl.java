package fr.eni.parking.bll;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.parking.bo.Car;
import fr.eni.parking.bo.Parking;
import fr.eni.parking.bo.Ticket;
import fr.eni.parking.dal.TicketDAO;

@Service
public class TicketManagerImpl implements TicketManager{
	
	@Autowired
	private TicketDAO ticketDAO;
	
	@Override
	public Ticket getTicketById(Integer id) {
		return ticketDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Ticket generateTicket(Car car, Parking parking, LocalDateTime arrivedAt) throws TicketManagerException {
		List<Ticket> ticketsByParking = ticketDAO.findActiveTicketsByParking(parking);
		if (ticketsByParking.size() >= parking.getPlaces()) {
			throw new TicketManagerException("Le parking est plein");
		}
		if (isItNight(LocalDateTime.now())) {
			throw new TicketManagerException("Le parking est fermé !");
		}
		Ticket ticket = new Ticket(car, parking, LocalDateTime.now());
		ticket.setStatus(true);
		ticketDAO.save(ticket);
		return ticket;
	}
	
	public Boolean isItNight(LocalDateTime now) {
		Integer time = Integer.parseInt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmm")));
		if (time > 1600 || time < 600) {
			return true;
		}
		return false;
	}

	@Override
	public List<Ticket> getAllTickets() {
		return (List<Ticket>) ticketDAO.findAll();
	}

	@Override
	public Double calculateTotal(Ticket ticket) {
		Duration duration = Duration.between(ticket.getExitAt(), ticket.getArrivedAt());
	    long diff = Math.abs(duration.toHours());
	    
	    Double total = ticket.getParking().getRateByHour() * diff;
	    
		return total;
	}

	@Override
	@Transactional
	public void removeTicketFromId(Integer id) {
		ticketDAO.deleteById(id);
	}

	@Override
	public List<Ticket> getByLicencePlate(String licence) {
		return ticketDAO.findByLicencePlate(licence);
	}

	@Override
	@Transactional
	public void updateTicket(Ticket ticket) {
		ticketDAO.save(ticket);
	}

	@Override
	public List<Ticket> getActiveTicketsByParking(Parking parking) {
		return (List<Ticket>) ticketDAO.findActiveTicketsByParking(parking);
	}

	@Override
	@Transactional
	public void closeTicket(Ticket ticket) throws TicketManagerException {
		if (isItNight(LocalDateTime.now())) {
			throw new TicketManagerException("Le parking est fermé !");
		}
		Ticket t = ticketDAO.findById(ticket.getId()).get();
		t.setStatus(false);
		t.setExitAt(LocalDateTime.now());
		t.setTotal(calculateTotal(t));
		ticketDAO.save(t);
		
	}

}
