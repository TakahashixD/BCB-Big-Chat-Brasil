package br.com.bcb.unittest.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import br.com.bcb.model.Client;
import br.com.bcb.repositories.ClientRepository;
import br.com.bcb.services.ClientService;
import br.com.bcb.unittest.mocks.MockClient;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
	
	MockClient mockClient;
	
    @Mock
    Environment environment;
	
	@InjectMocks
	private ClientService clientService;
	
	@Mock
	ClientRepository clientRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		mockClient = new MockClient();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreateClient() {
		Client client = mockClient.mockEntity(1);
		Client persisted = client;
		persisted.setId(1L);
		
		when(clientRepository.save(client)).thenReturn(persisted);
		
		var result = clientService.createClient(client);
		assertNotNull(result);
		assertEquals("nome1", result.getName());
		assertEquals("email@1", result.getEmail());
		assertEquals("phone1", result.getPhoneNumber());
		assertEquals("cpf1", result.getCpf());
		assertEquals("cnpj1", result.getCnpj());
		assertEquals("company1", result.getCompanyName());
		assertEquals(1L, result.getCredits());
		assertEquals(1L*2, result.getLimit());
		
	}
	
	@Test
	void testFindById() {
		Client client = mockClient.mockEntity(1);
		client.setId(1L);
		
		when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
		when(environment.getProperty("local.server.port")).thenReturn("8080");
		
		var result = clientService.findById(1L);
		assertNotNull(result);
		assertEquals("nome1", result.getName());
		assertEquals("email@1", result.getEmail());
		assertEquals("phone1", result.getPhoneNumber());
		assertEquals("cpf1", result.getCpf());
		assertEquals("cnpj1", result.getCnpj());
		assertEquals("company1", result.getCompanyName());
		assertEquals(1L, result.getCredits());
		assertEquals(1L*2, result.getLimit());
	}
	
	@Test
	void testFindCreditsClientById() {
		Client client = mockClient.mockEntity(1);
		client.setId(1L);
		
		when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
		
		var result = clientService.findCreditsClientById(1l);
		assertNotNull(result);
		assertEquals(1L, result);
	}
	
	@Test
	void testDelete() {
		Client client = mockClient.mockEntity(1);
		client.setId(1L);
		
		when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
		
		clientService.deleteClient(1L);
	}
}
