/**
 * 
 */
package fr.eni.parking.bll;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.parking.bo.Parking;
import fr.eni.parking.dal.ParkingDAO;

/**
 * Implementation of the ParkingManager
 * @author ramona
 *
 */

@Service
public class ParkingManagerImpl implements ParkingManager {
	
	@Autowired
	private ParkingDAO parkingDAO;

	@Override
	@Transactional
	public void addParking(Parking parking) {
		parkingDAO.save(parking);
	}

	@Override
	@Transactional
	public void updateParking(Parking parking) {
		parkingDAO.save(parking);
	}

	@Override
	@Transactional
	public void deleteParking(Parking parking) {
		parkingDAO.delete(parking);
	}

	@Override
	public List<Parking> getAllParkings() {
		return (List<Parking>) parkingDAO.findAll();
	}

	@Override
	public Parking getParkingById(Integer id) {
		return parkingDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void removeParkingFromId(Integer id) {
		parkingDAO.deleteById(id);
	}

	

	


}
