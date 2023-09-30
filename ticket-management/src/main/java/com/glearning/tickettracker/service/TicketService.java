package com.glearning.tickettracker.service;

import java.util.List;

import com.glearning.tickettracker.dto.TicketDto;

//Service with CRUDS operation for tickets
public interface TicketService {
	
	List<TicketDto> findAllTickets();
	
	void createTicket(TicketDto ticketDto);
	
	TicketDto findTickerById(Long ticketId);
	
	void updateTicket(TicketDto ticketDto);
	
	void deleteTicket(Long ticketId);

	TicketDto findTicketByUrl(String ticketUrl);
	
	List<TicketDto> searchTickets(String query);

}
