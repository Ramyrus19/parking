/**
 * 
 */
package fr.eni.parking;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.parking.bll.ParkingManager;
import fr.eni.parking.bo.Parking;

/**
 * @author ramon
 *
 */
@SpringBootTest
class ParkingManagerTest {

	@Autowired
	ParkingManager manager;
	
	@Test
	@Transactional
	void addParkingTest() {
		Parking resistance = new Parking("Place de la Resistance", 50, 2.50);
		manager.addParking(resistance);
		
		Parking fromManager = manager.getParkingById(resistance.getId());
		
		assertNotNull(fromManager);
	}

}
