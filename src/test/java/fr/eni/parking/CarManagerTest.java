package fr.eni.parking;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.parking.bll.CarManager;
import fr.eni.parking.bo.Car;

@SpringBootTest
class CarManagerTest {

	@Autowired
	CarManager manager;
	
	@Test
	@Transactional
	void addCarTest() {
		Car peugeot = new Car("AB123CD", "Peugeot", "207");
		manager.addCar(peugeot);

		Car fromManager = manager.getCarById(peugeot.getId());
		
		assertNotNull(fromManager);
	}

}
