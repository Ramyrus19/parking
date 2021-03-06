package fr.eni.parking.ihm;


import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.parking.bll.CarManager;
import fr.eni.parking.bll.ParkingManager;
import fr.eni.parking.bll.TicketManager;
import fr.eni.parking.bll.TicketManagerException;
import fr.eni.parking.bo.Parking;
import fr.eni.parking.bo.Ticket;

@Controller
public class TicketController {
	@Autowired
	TicketManager ticketManager;
	
	@Autowired 
	ParkingManager parkingManager;
	
	@Autowired
	CarManager carManager;
	
	@GetMapping("/ticket/create")
	public String showTicketForm(Ticket ticket, Model model) {
		model.addAttribute("cars", carManager.getAllCars());
		model.addAttribute("parkings", parkingManager.getAllParkings());
		return "ticket/add";
	}
	
	@PostMapping("/ticket/add")
	public String generateTicket(@Valid Ticket ticket, BindingResult result, Model model) {
		try {
			ticketManager.generateTicket(ticket.getCar(), ticket.getParking(), LocalDateTime.now());
		} catch (TicketManagerException err) {
			result.addError(new ObjectError("global", err.getMessage()));
			model.addAttribute("cars", carManager.getAllCars());
			model.addAttribute("parkings", parkingManager.getAllParkings());
		}			
		if (result.hasErrors()) {
			
			return "ticket/add";
		}
		return "redirect:/ticket/index";
	}
	
	@GetMapping("/ticket/index")
	public String listTickets(Model model) {
		model.addAttribute("tickets", ticketManager.getAllTickets());
		return "ticket/index";
	}
	
	@GetMapping("/ticket/search")
	public String showSearchForm(Ticket ticket, Model model) {
		model.addAttribute("ticket", ticket);
		return "ticket/search";
	}
	
	@PostMapping("/ticket/find")
	public String findTicket(Ticket ticket, Model model) {
		List<Ticket> tickets = ticketManager.getByLicencePlate(ticket.getCar().getLicence());
		model.addAttribute("tickets", tickets);
		return "ticket/allByCar";
	}
	
	@GetMapping("/ticket/details/{id}")
	public String showTicketDetails(@PathVariable("id") Integer id, Ticket ticket, Model model) {
		Ticket found = ticketManager.getTicketById(id);
		model.addAttribute("ticket", found);
		return "ticket/show";
	}
	
	@GetMapping("/ticket/close/{id}")
	public String closeTicket(@PathVariable("id") Integer id, Model model) {
		Ticket t = ticketManager.getTicketById(id);
		try {
			ticketManager.closeTicket(t);
			model.addAttribute("ticket", t);
			
			Parking p = t.getParking();
			Double turnover = p.getTurnover() + t.getTotal();
			p.setTurnover(turnover);
			parkingManager.updateParking(p);
			
		} catch (TicketManagerException err) {
			model.addAttribute("error", err.getMessage());
			model.addAttribute("tickets", ticketManager.getAllTickets());
			//TODO : return to index with the error - not working
			return "redirect:/ticket/index";
		}
		
		return "redirect:/ticket/index";
	}
	
	@GetMapping("/ticket/delete/{id}")
	public String deleteTicket(@PathVariable("id") Integer id, Model model) {
		Ticket fromManager = ticketManager.getTicketById(id);
		ticketManager.removeTicketFromId(fromManager.getId());
		
		return "redirect:/ticket/index";
	}
	
	
}
