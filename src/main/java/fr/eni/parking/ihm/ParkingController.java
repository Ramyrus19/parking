/**
 * 
 */
package fr.eni.parking.ihm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.parking.bll.ParkingManager;
import fr.eni.parking.bo.Parking;

/**
 * @author ramon
 *
 */
@Controller
public class ParkingController {
	@Autowired
	ParkingManager manager;
	
	@GetMapping("/parking/create")
	public String showCreateForm(Parking parking) {
		return "addParking";
	}
	
	@PostMapping("/parking/add")
	public String addParking(@Valid Parking parking, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addParking";
		}
		manager.addParking(parking);
		return "redirect:/parking/index";
	}
	
	@GetMapping("/parking/index")
	public String listParkings(Model model) {
		model.addAttribute("parkings", manager.getAllParkings());
		return "index";
	}
	
	@GetMapping("/parking/edit/{id}")
	public String showEditForm(@PathVariable("id") Integer id, Model model) {
		Parking parking = manager.getParkingById(id);
		model.addAttribute("parking", parking);
		
		return "updateParking";
	}
	
	@PostMapping("/parking/update/{id}")
	public String updateParking(@PathVariable("id") Integer id, @Valid Parking parking, 
			BindingResult result, Model model) {
		parking.setId(id);
		if (result.hasErrors()) {
			return "updateParking";
		}
		manager.updateParking(parking);
		return "redirect:/parking/index";
	}
	
	@GetMapping("/parking/delete/{id}")
	public String deleteParking(@PathVariable("id") Integer id, Model model) {
		manager.removeParkingFromId(id);
		
		return "redirect:/parking/index";
	}
}
