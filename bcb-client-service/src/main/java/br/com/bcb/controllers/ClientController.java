package br.com.bcb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bcb.model.Client;
import br.com.bcb.proxy.SmsProxy;
import br.com.bcb.response.Sms;
import br.com.bcb.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Client endpoint")
@RestController
@RequestMapping("client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private SmsProxy smsProxy;
	
	@PostMapping(value="/sms")
	public Sms createSms(Sms sms) {
		return smsProxy.createSms(sms);
	}
	
	@GetMapping(value="/sms/{id}")
	public Sms findByClientId(Long client_id) {
		return smsProxy.findByClientId(client_id);
	}
	
	@PostMapping(value="/{id}")
	@Operation(summary = "Create a new client", description = "Create a new client", 
	tags = {"Clients"},
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
	
	@GetMapping(value="/{id}")
	@Operation(summary = "Finds a client", description = "Finds a client", 
	tags = {"Client"},
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
	tags = {"Client"},
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
	tags = {"Client"},
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
	tags = {"Client"},
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
	tags = {"Client"},
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
	tags = {"Client"},
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
	tags = {"Client"},
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
	public Client changeClientPlan(@PathVariable("id") Long id, @PathVariable("plan") String plan) {
		return clientService.changeClientPlan(id, plan);
	}
}
