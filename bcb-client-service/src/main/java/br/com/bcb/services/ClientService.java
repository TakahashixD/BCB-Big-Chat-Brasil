package br.com.bcb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.bcb.configuration.ClientConfiguration;
import br.com.bcb.model.Client;
import br.com.bcb.repositories.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private Environment enviroment;
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client createClient(Client client) {
		return clientRepository.save(client);
	}
	
	public Client findById(Long id) {
		var client = clientRepository.findById(id);
		if(client.isEmpty()) throw new RuntimeException("Client not found!");
		var port = enviroment.getProperty("local.server.port");
		client.get().setEnvironment(port);
		return client.get();
	}
	
	public Client updateClient(Client client) {
		var clientToUpdate = clientRepository.findById(client.getId());
		if(clientToUpdate.isEmpty()) throw new RuntimeException("Client not found!");
		var port = enviroment.getProperty("local.server.port");
		var clientReturn = clientToUpdate.get();
		clientReturn.setName(client.getName());
		clientReturn.setEmail(client.getEmail());
		clientReturn.setPhoneNumber(client.getPhoneNumber());
		clientReturn.setCpf(client.getCpf());
		clientReturn.setCnpj(client.getCnpj());
		clientReturn.setCompanyName(client.getCompanyName());
		clientReturn.setPlan(client.getPlan());
		clientReturn.setCredits(client.getCredits());
		clientReturn.setLimit(client.getLimit());
		clientReturn = clientRepository.save(clientReturn);
		clientReturn.setEnvironment(port);
		return clientReturn;
	}
	
	public void deleteClient(Long id) {
		var client = clientRepository.findById(id).get();
		clientRepository.delete(client);
	}
	
	public Long findCreditsClientById(Long id) {
		var client = clientRepository.findById(id);
		if(client.isEmpty()) throw new RuntimeException("Client not found!");
		return client.get().getCredits();
	}
	
	public Long findLimitClientById(Long id) {
		var client = clientRepository.findById(id);
		if(client.isEmpty()) throw new RuntimeException("Client not found!");
		return client.get().getLimit();
	}
	
	public Client addCreditsToClient(Long id, Long credits) {
		var client = clientRepository.findById(id);
		if(client.isEmpty()) throw new RuntimeException("Client not found!");
		if(credits == null || credits <= 0) throw new RuntimeException("Invalid number of credits!");
		var port = enviroment.getProperty("local.server.port");
		var clientReturn = client.get();
		clientReturn.setCredits(clientReturn.getCredits() + credits);
		clientReturn = clientRepository.save(clientReturn);
		clientReturn.setEnvironment(port);
		return clientReturn;
	}
	
	public Client changeClientLimit(Long id, Long limit) {
		var client = clientRepository.findById(id);
		if(client.isEmpty()) throw new RuntimeException("Client not found!");
		if(limit == null || limit <= 0) throw new RuntimeException("Invalid Limit!");
		var port = enviroment.getProperty("local.server.port");
		var clientReturn = client.get();
		clientReturn.setLimit(clientReturn.getLimit() + limit);
		clientReturn = clientRepository.save(clientReturn);
		clientReturn.setEnvironment(port);
		return clientReturn;
	}
	
	public Client changeClientPlan(Long id, String plan) {
		var client = clientRepository.findById(id);
		if(client.isEmpty()) throw new RuntimeException("Client not found!");
		var clientReturn = client.get();
		if(plan == null || clientReturn.getPlan().equals(plan)) throw new RuntimeException("Invalid plan or Already is this plan!");
		var port = enviroment.getProperty("local.server.port");
		clientReturn.setPlan(plan);
		clientReturn = clientRepository.save(clientReturn);
		clientReturn.setEnvironment(port);
		return clientReturn;
	}
}
