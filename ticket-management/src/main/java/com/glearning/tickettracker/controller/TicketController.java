package com.glearning.tickettracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.glearning.tickettracker.dto.TicketDto;
import com.glearning.tickettracker.service.TicketService;

@Controller
public class TicketController {

	private TicketService ticketService;

	// Constructor injection
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@GetMapping("/admin/tickets")
	// Get method to list all tickets
	public String tickets(Model model) {
		List<TicketDto> tickets = ticketService.findAllTickets();
		model.addAttribute("tickets", tickets); // key value pair
		return "/tickets/list-tickets";
	}

	// Get method to enter details of new ticket to create
	@GetMapping("admin/tickets/newTicket")
	public String newTicketForm(Model model) {
		TicketDto ticketDto = new TicketDto();
		model.addAttribute("ticket", ticketDto);
		return "tickets/create_ticket_form";
	}

	// Post method for submit action to create a new ticket
	@PostMapping("/admin/tickets")
	public String createTicket(@Valid @ModelAttribute("ticket") TicketDto ticketDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("ticket", ticketDto);
			return "tickets/create_ticket_form";
		}
		ticketDto.setUrl(getUrl(ticketDto.getTitle()));
		ticketService.createTicket(ticketDto);
		return "redirect:/admin/tickets";
	}

	// Get the unique pattern url for a new ticket
	private static String getUrl(String ticketTitle) {

		String title = ticketTitle.trim().toLowerCase();
		String url = title.replaceAll("\\s+", "-");
		url = url.replaceAll("[A-Za-z0-9]", "=");
		return url;
	}

	// Get method to enter details to edit a ticket
	@GetMapping("/admin/tickets/{ticketId}/edit")
	public String editTicketForm(@PathVariable("ticketId") Long ticketId, Model model) {

		TicketDto ticketDto = ticketService.findTickerById(ticketId);
		model.addAttribute("ticket", ticketDto);
		return "tickets/edit_ticket_form";

	}

	// Post method to take submit action to update a ticket
	@PostMapping("/admin/tickets/{ticketId}")
	public String updateTicket(@PathVariable("ticketId") long ticketId,
			@Valid @ModelAttribute("ticket") TicketDto ticket, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("ticket", ticket);
			return "tickets/edit_ticket_form";
		}
		ticket.setId(ticketId);
		ticketService.updateTicket(ticket);
		return "redirect:/admin/tickets";
	}

	// Get method to delete a ticket
	@GetMapping("/admin/tickets/{ticketId}/delete")

	public String deleteTicket(@PathVariable("ticketId") Long ticketId) {
		ticketService.deleteTicket(ticketId);
		return "redirect:/admin/tickets";
	}

	//Get method to view a particular ticket based on url
	@GetMapping("/admin/ticket/{ticketUrl}/view")
	public String viewPost(@PathVariable("ticketUrl") String ticketUrl, Model model) {
		TicketDto ticketDto = ticketService.findTicketByUrl(ticketUrl);
		model.addAttribute("ticket",ticketDto);
		return "tickets/view_ticket_form";
	}
	
	//Get method to search a ticket based on a query
	@GetMapping("/admin/tickets/search")
	public String searchTickets(@RequestParam(value="query") String query, Model model) {
		
		List<TicketDto> tickets = ticketService.searchTickets(query);
		model.addAttribute("tickets", tickets);
		return "tickets/list-tickets";
		
	}

}