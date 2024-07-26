package br.com.bcb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bcb.configuration.ClientConfiguration;
import br.com.bcb.model.Client;
import br.com.bcb.repositories.ClientRepository;

@RestController
@RequestMapping("client")
public class ClientController {
	
	@Autowired
	private Environment enviroment;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientConfiguration clientConfiguration;
	
	@PostMapping(value="/{id}")
	public Client createClient(@RequestBody Client client) {
		return clientRepository.save(client);
	}
	
	@GetMapping(value="/{id}")
	public Client findById(@PathVariable("id") Long id) {
		var client = clientRepository.findById(id);
		if(client.isEmpty()) throw new RuntimeException("Client not found!");
		var port = enviroment.getProperty("local.server.port");
		client.get().setEnvironment(port);
		return client.get();
	}
	
	@PutMapping(value="/{id}")
	public Client updateClient(@PathVariable("id") Long id) {
		return clientRepository.findById(id).get();
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteClient(@PathVariable("id") Long id) {
		clientRepository.deleteById(id);
	}
}
