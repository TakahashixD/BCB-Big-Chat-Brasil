package br.com.bcb.integrationtests.controllers;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bcb.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.bcb.model.Client;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class ClientControllerTests extends AbstractIntegrationTest {
	
	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	private static Client client;
	
	@BeforeAll
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		client = new Client();
	}
	private void mockClient() {
		client.setName("nome");
		client.setEmail("email@");
		client.setPhoneNumber("phone");
		client.setCpf("cpf");
		client.setCnpj("cnpj");
		client.setCompanyName("company");
		client.setCredits(1L);
		client.setLimit(2l);
		client.setPlan("plan");
		client.setEnvironment("8080");
	}
	
	@Test
	@Order(1)
	public void testCreateClient() throws JsonMappingException, JsonProcessingException {
		mockClient();
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "https://localhost:8080")
				.setBasePath("/api/client/v1")
				.setPort(8080)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = given().spec(specification)
			.contentType("application/json")
				.body(client)
			.when()
				.post()
			.then()
				.statusCode(200)
					.extract()
						.body()
							.asString();
		
		Client createdClient = objectMapper.readValue(content, Client.class);
		client = createdClient;
		assertNotNull(createdClient.getId());
		assertNotNull(createdClient.getName());
		assertNotNull(createdClient.getEmail());
		assertNotNull(createdClient.getPhoneNumber());
		assertNotNull(createdClient.getCpf());
		assertNotNull(createdClient.getCnpj());
		assertNotNull(createdClient.getCompanyName());
		assertNotNull(createdClient.getCredits());
		assertNotNull(createdClient.getLimit());
		assertNotNull(createdClient.getPlan());
		
		assertTrue(createdClient.getId() > 0);
		
		assertEquals("nome", createdClient.getName());
		assertEquals("email@", createdClient.getEmail());
		assertEquals("phone", createdClient.getPhoneNumber());
		assertEquals("cpf", createdClient.getCpf());
		assertEquals("cnpj", createdClient.getCnpj());
		assertEquals("company", createdClient.getCompanyName());
		assertEquals(1L, createdClient.getCredits());
		assertEquals(2L, createdClient.getLimit());
		assertEquals("plan", createdClient.getPlan());
	}
	
	@Test
	@Order(2)
	public void testFindById() throws JsonMappingException, JsonProcessingException {
		mockClient();
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "https://localhost:8080")
				.setBasePath("/api/client/v1")
				.setPort(8080)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = given().spec(specification)
			.contentType("application/json")
				.pathParam("id", client.getId())
			.when()
				.get("{id}")
			.then()
				.statusCode(200)
					.extract()
						.body()
							.asString();
		
		Client createdClient = objectMapper.readValue(content, Client.class);
		client = createdClient;
		assertNotNull(createdClient.getId());
		assertNotNull(createdClient.getName());
		assertNotNull(createdClient.getEmail());
		assertNotNull(createdClient.getPhoneNumber());
		assertNotNull(createdClient.getCpf());
		assertNotNull(createdClient.getCnpj());
		assertNotNull(createdClient.getCompanyName());
		assertNotNull(createdClient.getCredits());
		assertNotNull(createdClient.getLimit());
		assertNotNull(createdClient.getPlan());
		
		assertTrue(createdClient.getId() > 0);
		
		assertEquals("nome", createdClient.getName());
		assertEquals("email@", createdClient.getEmail());
		assertEquals("phone", createdClient.getPhoneNumber());
		assertEquals("cpf", createdClient.getCpf());
		assertEquals("cnpj", createdClient.getCnpj());
		assertEquals("company", createdClient.getCompanyName());
		assertEquals(1L, createdClient.getCredits());
		assertEquals(2L, createdClient.getLimit());
		assertEquals("plan", createdClient.getPlan());
	}
	

}
