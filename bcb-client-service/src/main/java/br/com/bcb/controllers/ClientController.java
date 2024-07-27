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
	public Client createClient(@RequestBody Client client) {
		return clientService.createClient(client);
	}
	
	@GetMapping(value="/{id}")
	public Client findById(@PathVariable("id") Long id) {
		return clientService.findById(id);
	}
	
	@PutMapping(value="/{id}")
	public Client updateClient(@PathVariable("id") Long id) {
		return clientService.updateClient(id);
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteClient(@PathVariable("id") Long id) {
		clientService.deleteClient(id);
	}
	
	@GetMapping(value="/credits/{id}")
	public Long findCreditsClientById(@PathVariable("id") Long id) {
		return clientService.findCreditsClientById(id);
	}
	
	@GetMapping(value="/limit/{id}")
	public Long findLimitClientById(@PathVariable("id") Long id) {
		return clientService.findLimitClientById(id);
	}
	
	@PutMapping(value="/credits/{id}/{credits}")
	public Client addCreditsToClient(@PathVariable("id") Long id, @PathVariable("credits") Long credits) {
		return clientService.addCreditsToClient(id, credits);
	}
	
	@PutMapping(value="/limit/{id}/{limit}")
	public Client changeClientLimit(@PathVariable("id") Long id, @PathVariable("limit") Long limit) {
		return clientService.changeClientLimit(id, limit);
	}
	
	@PutMapping(value="/plan/{id}/{plan}")
	public Client changeClientPlan(@PathVariable("id") Long id, @PathVariable("plan") String plan) {
		return clientService.changeClientPlan(id, plan);
	}
}
