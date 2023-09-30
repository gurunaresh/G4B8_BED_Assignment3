package com.glearning.tickettracker.serviceImpl;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.glearning.tickettracker.dto.TicketDto;
import com.glearning.tickettracker.entity.Ticket;
import com.glearning.tickettracker.mapper.TicketMapper;
import com.glearning.tickettracker.repository.TicketRepository;
import com.glearning.tickettracker.service.TicketService;

//Implementation of the CRUDS operation for tickets
@Service
public class TicketServiceImpl implements TicketService {

	private TicketRepository ticketRepository;

	public TicketServiceImpl(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@Override
	public List<TicketDto> findAllTickets() {
		List<Ticket> tickets = ticketRepository.findAll();
		return tickets.stream().map(TicketMapper::mapToTicketDto).collect(Collectors.toList());
	}

	@Override
	public void createTicket(TicketDto ticketDto) {
		Ticket ticket = TicketMapper.mapToTicket(ticketDto);
		ticketRepository.save(ticket);
	}

	@Override
	public TicketDto findTickerById(Long ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId).get();
		return TicketMapper.mapToTicketDto(ticket);
	}

	@Override
	public void updateTicket(TicketDto ticketDto) {
		Ticket ticket = TicketMapper.mapToTicket(ticketDto);
		ticketRepository.save(ticket);

	}

	@Override
	public void deleteTicket(Long ticketId) {
		ticketRepository.deleteById(ticketId);

	}

	@Override
	public TicketDto findTicketByUrl(String ticketUrl) {
		Ticket ticket = ticketRepository.findByUrl(ticketUrl).get();
		return TicketMapper.mapToTicketDto(ticket);
	}

	@Override
	public List<TicketDto> searchTickets(String query) {
		List<Ticket> tickets = ticketRepository.searchTickets(query);
		return tickets.stream().map(TicketMapper::mapToTicketDto).collect(Collectors.toList());
	}

}
