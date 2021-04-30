package fr.eni.parking.bll;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.parking.bo.Car;
import fr.eni.parking.bo.Parking;
import fr.eni.parking.bo.Ticket;
import fr.eni.parking.dal.CarDAO;
import fr.eni.parking.dal.TicketDAO;

@Service
public class CarManagerImpl implements CarManager{
	@Autowired
	private CarDAO carDAO;
	
	@Autowired
	private TicketDAO ticketDAO;
	
	@Override
	public List<Car> getAllCars() {
		return (List<Car>) carDAO.findAll();
	}

	@Override
	public Car getCarById(Integer id) {
		return carDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void addCar(Car car) {
		carDAO.save(car);
	}

	@Override
	public List<Car> getAllCarsFromParking(Parking parking) {
		List<Car> carsByParking = new ArrayList<>();
		List<Ticket> tickets = ticketDAO.findActiveTicketsByParking(parking);
		for (Ticket ticket : tickets) {
			carsByParking.add(ticket.getCar());
		}
		return carsByParking;
	}

}
