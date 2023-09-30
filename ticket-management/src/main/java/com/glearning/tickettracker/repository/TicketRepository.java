package com.glearning.tickettracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.glearning.tickettracker.entity.Ticket;
    
public interface TicketRepository extends JpaRepository<Ticket, Long>{ //entity name, data type of primary key

	//create query using method name , spring jpa will pass internally and uses hibernate to create the query
	Optional<Ticket> findByUrl(String url); 
	
	//JPQL for search operation : use JPA entity and its field names
	@Query("SELECT p from Ticket p WHERE " +
            " p.title LIKE CONCAT('%', :query, '%') OR " +
            " p.shortDescription LIKE CONCAT('%', :query, '%')")
	List<Ticket> searchTickets(String query);
	
}
