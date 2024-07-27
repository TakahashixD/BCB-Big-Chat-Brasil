package br.com.bcb.unittest.mocks;

import java.util.ArrayList;
import java.util.List;

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
        return client;
    }
}
