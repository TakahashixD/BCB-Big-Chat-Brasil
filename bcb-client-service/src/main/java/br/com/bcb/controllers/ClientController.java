package br.com.bcb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bcb.model.Client;
import br.com.bcb.response.Sms;
import br.com.bcb.services.ClientService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Client endpoint")
@RestController
@RequestMapping("client-service")
@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	public String fallbackMethod(Exception ex) {
		return ex.getMessage();
	}
	
	@GetMapping(value="sms-service/byClient/{id}")
	@Operation(summary = "Finds all sms of a client", description = "Finds all sms of a client", 
	responses = {
			@ApiResponse(description="Success", responseCode = "200", content= @Content(schema=@Schema(implementation = Sms.class))),
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public ResponseEntity<Page<Sms>> findByClientId(@PathVariable("id") Long client_id,
			@RequestParam(value= "page", defaultValue = "0") Integer page,
			@RequestParam(value= "size", defaultValue = "5") Integer size) {
		return ResponseEntity.ok(clientService.findSmsByClientId(client_id, page, size));
	}
	
	@PostMapping(value="/sms-service")
	@Operation(summary = "Create a new from the client sms", description = "Create a new from the client sms", 
	responses = {
			@ApiResponse(description="Created", responseCode = "200", content= @Content(schema=@Schema(implementation = Sms.class))),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public Sms createSms(Sms sms) {
		return clientService.createSms(sms);
	}
	
	@PostMapping()
	@Operation(summary = "Create a new client", description = "Create a new client", 
	responses = {
			@ApiResponse(description="Created", responseCode = "200", content= @Content(schema=@Schema(implementation = Client.class))),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public Client createClient(@RequestBody Client client) {
		return clientService.createClient(client);
	}
	
	@GetMapping()
	@Operation(summary = "Finds all client", description = "Finds all client", 
	responses = {
			@ApiResponse(description="Success", responseCode = "200", content= @Content(schema=@Schema(implementation = Client.class))),
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public ResponseEntity<Page<Client>> findAll(
			@RequestParam(value= "page", defaultValue = "0") Integer page,
			@RequestParam(value= "size", defaultValue = "5") Integer size,
			@RequestParam(value= "direction", defaultValue = "asc") String direction
			) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) 
				? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
		return ResponseEntity.ok(clientService.findAll(pageable));
	}
	
	@GetMapping(value = "/findClientsByName/{name}")
	@Operation(summary = "Finds all client by name", description = "Finds all client by name", 
	responses = {
			@ApiResponse(description="Success", responseCode = "200", content= @Content(schema=@Schema(implementation = Client.class))),
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public ResponseEntity<Page<Client>> findClientsByName(
			@RequestParam(value= "page", defaultValue = "0") Integer page,
			@RequestParam(value= "size", defaultValue = "5") Integer size,
			@RequestParam(value= "direction", defaultValue = "asc") String direction,
			@PathVariable(value = "name") String name
			) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) 
				? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
		return ResponseEntity.ok(clientService.findClientsByName(name, pageable));
	}
	
	@GetMapping(value="/{id}")
	@Operation(summary = "Finds a client", description = "Finds a client", 
	responses = {
			@ApiResponse(description="Success", responseCode = "200", content= @Content(schema=@Schema(implementation = Client.class))),
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public Client findById(@PathVariable("id") Long id) {
		return clientService.findById(id);
	}
	
	@PutMapping(value="/{id}")
	@Operation(summary = "Update a client", description = "Update a client", 
	responses = {
			@ApiResponse(description="Success", responseCode = "200", content= @Content(schema=@Schema(implementation = Client.class))),
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public Client updateClient(@RequestBody Client client) {
		return clientService.updateClient(client);
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary = "Delete a client", description = "Delete a client", 
	responses = {
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public void deleteClient(@PathVariable("id") Long id) {
		clientService.deleteClient(id);
	}
	
	@GetMapping(value="/credits/{id}")
	@Operation(summary = "Finds credits of a client", description = "Finds credits of a client", 
	responses = {
			@ApiResponse(description="Success", responseCode = "200", content= @Content),
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public Long findCreditsClientById(@PathVariable("id") Long id) {
		return clientService.findCreditsClientById(id);
	}
	
	@GetMapping(value="/limit/{id}")
	@Operation(summary = "Finds limits of a client", description = "Finds limits of a client", 
	responses = {
			@ApiResponse(description="Success", responseCode = "200", content= @Content),
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public Long findLimitClientById(@PathVariable("id") Long id) {
		return clientService.findLimitClientById(id);
	}
	
	@PutMapping(value="/credits/{id}/{credits}")
	@Operation(summary = "Add credits to a client", description = "Add credits to a client", 
	responses = {
			@ApiResponse(description="Success", responseCode = "200", content= @Content(schema=@Schema(implementation = Client.class))),
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public Client addCreditsToClient(@PathVariable("id") Long id, @PathVariable("credits") Long credits) {
		return clientService.addCreditsToClient(id, credits);
	}
	
	@PutMapping(value="/limit/{id}/{limit}")
	@Operation(summary = "Add credits to a client", description = "Add credits to a client", 
	responses = {
			@ApiResponse(description="Success", responseCode = "200", content= @Content(schema=@Schema(implementation = Client.class))),
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public Client changeClientLimit(@PathVariable("id") Long id, @PathVariable("limit") Long limit) {
		return clientService.changeClientLimit(id, limit);
	}
	
	@PutMapping(value="/plan/{id}/{plan}")
	@Operation(summary = "Update client plan", description = "Update client plan", 
	responses = {
			@ApiResponse(description="Updated", responseCode = "200", content= @Content(schema=@Schema(implementation = Client.class))),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public Client changeClientPlan(@PathVariable("id") Long id, @PathVariable("plan") String plan) {
		return clientService.changeClientPlan(id, plan);
	}
}
