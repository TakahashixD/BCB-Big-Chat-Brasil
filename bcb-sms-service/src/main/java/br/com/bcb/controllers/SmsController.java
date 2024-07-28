package br.com.bcb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import br.com.bcb.model.Sms;
import br.com.bcb.services.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Sms endpoint")
@RestController
@RequestMapping("sms-service")
public class SmsController {
		
	@Autowired
	private SmsService smsService;
	
	@PostMapping()
	@Operation(summary = "Create a new sms", description = "Create a new sms", 
	responses = {
			@ApiResponse(description="Created", responseCode = "200", content= @Content(schema=@Schema(implementation = Sms.class))),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public Sms createSms(@RequestBody Sms sms) {
		return smsService.createSms(sms);
	}
	
	@GetMapping()
	@Operation(summary = "Finds all sms", description = "Finds all sms", 
	responses = {
			@ApiResponse(description="Success", responseCode = "200", content= @Content(schema=@Schema(implementation = Sms.class))),
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public ResponseEntity<Page<Sms>> findAll(
			@RequestParam(value= "page", defaultValue = "0") Integer page,
			@RequestParam(value= "size", defaultValue = "5") Integer size
			) {
		
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(smsService.findAll(pageable));
	}
	
	@GetMapping(value="/{id}")
	@Operation(summary = "Finds a sms", description = "Finds a sms", 
	responses = {
			@ApiResponse(description="Success", responseCode = "200", content= @Content(schema=@Schema(implementation = Sms.class))),
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public Sms findById(@PathVariable("id") Long id) {
		return smsService.findById(id);
	}
	
	@GetMapping(value="/byClient/{id}")
	@Operation(summary = "Finds all sms by client id", description = "Finds all sms by client id", 
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
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(smsService.findByClientId(client_id, pageable));
	}
	
	@PutMapping()
	@Operation(summary = "Update a sms", description = "Update a sms", 
	responses = {
			@ApiResponse(description="Success", responseCode = "200", content= @Content(schema=@Schema(implementation = Sms.class))),
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public Sms updateSms(@RequestBody Sms sms) {
		return smsService.updateSms(sms);
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary = "Delete a sms", description = "Delete a sms", 
	responses = {
			@ApiResponse(description="No Content", responseCode = "204", content= @Content),
			@ApiResponse(description="Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description="Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description="Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description="Internal Server Error", responseCode = "500", content= @Content),
	})
	public void deleteSms(@PathVariable("id") Long id) {
		smsService.deleteSms(id);
	}
}
