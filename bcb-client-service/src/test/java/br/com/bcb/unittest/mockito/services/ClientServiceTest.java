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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
	void testFindAll() {
		Pageable pageable = PageRequest.of(0, 5, Sort.by("asc", "name"));
		Page<Client> clients = mockClient.mockEntityPage(pageable);
		when(clientRepository.findAll(pageable)).thenReturn(clients);
		
		var client = clientService.findAll(pageable);
		assertNotNull(client);
		
		assertNotNull(client.getContent().get(0));
		assertEquals("nome0", client.getContent().get(0).getName());
		assertEquals("email@0", client.getContent().get(0).getEmail());
		assertEquals("phone0", client.getContent().get(0).getPhoneNumber());
		assertEquals("cpf0", client.getContent().get(0).getCpf());
		assertEquals("cnpj0", client.getContent().get(0).getCnpj());
		assertEquals("company0", client.getContent().get(0).getCompanyName());
		assertEquals(0, client.getContent().get(0).getCredits());
		assertEquals(0, client.getContent().get(0).getLimit());
		assertEquals("plan0", client.getContent().get(0).getPlan());
		
		assertNotNull(client.getContent().get(1));
		assertEquals("nome1", client.getContent().get(1).getName());
		assertEquals("email@1", client.getContent().get(1).getEmail());
		assertEquals("phone1", client.getContent().get(1).getPhoneNumber());
		assertEquals("cpf1", client.getContent().get(1).getCpf());
		assertEquals("cnpj1", client.getContent().get(1).getCnpj());
		assertEquals("company1", client.getContent().get(1).getCompanyName());
		assertEquals(1, client.getContent().get(1).getCredits());
		assertEquals(1*2, client.getContent().get(1).getLimit());
		assertEquals("plan1", client.getContent().get(1).getPlan());
	}
	
	@Test
	void testFindClientsByName() {
		Pageable pageable = PageRequest.of(0, 5, Sort.by("asc", "name"));
		Page<Client> clients = mockClient.mockEntityPage(pageable);
		when(clientRepository.findClientsByName("no", pageable)).thenReturn(clients);
		
		var client = clientService.findClientsByName("no", pageable);
		assertNotNull(client);
		
		assertTrue(client.getContent().get(0).getName().contains("no"));
		assertNotNull(client.getContent().get(0));
		assertEquals("nome0", client.getContent().get(0).getName());
		assertEquals("email@0", client.getContent().get(0).getEmail());
		assertEquals("phone0", client.getContent().get(0).getPhoneNumber());
		assertEquals("cpf0", client.getContent().get(0).getCpf());
		assertEquals("cnpj0", client.getContent().get(0).getCnpj());
		assertEquals("company0", client.getContent().get(0).getCompanyName());
		assertEquals(0, client.getContent().get(0).getCredits());
		assertEquals(0, client.getContent().get(0).getLimit());
		assertEquals("plan0", client.getContent().get(0).getPlan());
		
		assertTrue(client.getContent().get(1).getName().contains("no"));
		assertNotNull(client.getContent().get(1));
		assertEquals("nome1", client.getContent().get(1).getName());
		assertEquals("email@1", client.getContent().get(1).getEmail());
		assertEquals("phone1", client.getContent().get(1).getPhoneNumber());
		assertEquals("cpf1", client.getContent().get(1).getCpf());
		assertEquals("cnpj1", client.getContent().get(1).getCnpj());
		assertEquals("company1", client.getContent().get(1).getCompanyName());
		assertEquals(1, client.getContent().get(1).getCredits());
		assertEquals(1*2, client.getContent().get(1).getLimit());
		assertEquals("plan1", client.getContent().get(1).getPlan());
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
		assertEquals("plan1", result.getPlan());
	}
	
	@Test
	void testUpdateClient() {
		Client client = mockClient.mockEntity(1);
		client.setId(1L);
		Client persisted = client;
		
		when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
		when(clientRepository.save(client)).thenReturn(persisted);
		
		var result = clientService.updateClient(client);
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
