package com.glearning.tickettracker.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// A class acts as model for the database. 
// Object used for database transaction and between controller and service.
public class TicketDto {  


	 private Long id;
	 
	 @NotEmpty
	 private String title;
	 private String url;
	 @NotEmpty	
	 private  String content;
	 @NotEmpty
	 private String shortDescription;
	 private LocalDateTime createdOn;
	 private LocalDateTime updatedOn;

	 
	 
}
