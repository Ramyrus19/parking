package fr.eni.parking.ihm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.parking.bll.CarManager;
import fr.eni.parking.bo.Car;


@Controller
public class HomepageController {
	@Autowired
	CarManager carManager;
	
	@GetMapping("/homepage")
	public String homepage() {
		return "homepage";
	}
	
	@GetMapping("/car/create")
	public String showCarForm(Car car, Model model) {
		return "addCar";
	}
	
	@PostMapping("/car/add")
	public String addCar(@Valid Car car, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addcar";
		}
		carManager.addCar(car);
		return "redirect:/ticket/create";
	}
}
