package br.com.bcb.unittest.mocks;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.bcb.model.Client;

public class MockClient {
	
    public Client mockEntity() {
        return mockEntity(0);
    }
    
    public List<Client> mockEntityList() {
        List<Client> clients = new ArrayList<Client>();
        for (int i = 0; i < 12; i++) {
            clients.add(mockEntity(i));
        }
        return clients;
    }
    
    public Page<Client> mockEntityPage(Pageable pageable) {
    	List<Client> clients = new ArrayList<Client>();
        for (int i = 0; i < pageable.getPageSize(); i++) {
            clients.add(mockEntity(i));
        }
        Page<Client> clientsPage = new PageImpl<>(clients, pageable, pageable.getPageSize());
        return clientsPage;
    }
  
    public Client mockEntity(Integer number) {
    	Client client = new Client();
    	client.setName("nome"+ number);
    	client.setEmail("email@"+ number);
    	client.setPhoneNumber("phone"+ number);
    	client.setCpf("cpf"+ number);
    	client.setCnpj("cnpj"+ number);
    	client.setCompanyName("company"+ number);
    	client.setCredits(number.longValue());
    	client.setLimit(number.longValue()*2);
    	client.setPlan("plan"+number);
        return client;
    }
}
