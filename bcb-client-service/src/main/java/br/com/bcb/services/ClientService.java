package br.com.bcb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bcb.model.Client;
import br.com.bcb.proxy.SmsProxy;
import br.com.bcb.repositories.ClientRepository;
import br.com.bcb.response.Sms;

@Service
public class ClientService {
	@Autowired
	private Environment enviroment;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private SmsProxy smsProxy;
	
	public Sms findSmsByClientId(Long client_id) {
		var proxy = smsProxy.findByClientId(client_id);
		var port = enviroment.getProperty("local.server.port");
		proxy.setEnviroment("Local Port: "+ port +
				" Proxy Port: " + proxy.getEnviroment());
		return proxy;
	}
	
	public Sms createSms(Sms sms) {
		return smsProxy.createSms(sms);
	}
	
	public Client createClient(Client client) {
		return clientRepository.save(client);
	}
	
	public Page<Client> findAll(Pageable pageable) {	
		var clientPage = clientRepository.findAll(pageable);
		var port = enviroment.getProperty("local.server.port");
		clientPage.getContent().stream().forEach(c -> c.setEnvironment(port));
		return clientPage;
	}
	
	public Page<Client> findClientsByName(String name, Pageable pageable) {	
		var clientPage = clientRepository.findClientsByName(name, pageable);
		var port = enviroment.getProperty("local.server.port");
		clientPage.getContent().stream().forEach(c -> c.setEnvironment(port));
		return clientPage;
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
