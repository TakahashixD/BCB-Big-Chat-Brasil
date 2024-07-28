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

import br.com.bcb.enums.MessageType;
import br.com.bcb.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.bcb.model.Sms;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class SmsControllerTests extends AbstractIntegrationTest {
	
	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	private static Sms sms;
	
	@BeforeAll
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		sms = new Sms();
	}
	private void mockSms() {
		sms.setTextSms("text");
		sms.setPhoneNumber("phone");
		sms.setMessageType(MessageType.WHATSAPP);
		sms.setClientId(1L);
		sms.setEnviroment("8100");
	}
	
	@Test
	@Order(1)
	public void testCreateSms() throws JsonMappingException, JsonProcessingException {
		mockSms();
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "https://localhost:8100")
				.setBasePath("/sms-service")
				.setPort(8100)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = given().spec(specification)
			.contentType("application/json")
				.body(sms)
			.when()
				.post()
			.then()
				.statusCode(200)
					.extract()
						.body()
							.asString();
		
		Sms createdSms = objectMapper.readValue(content, Sms.class);
		sms = createdSms;
		assertNotNull(createdSms.getId());
		assertNotNull(createdSms.getClientId());
		assertNotNull(createdSms.getMessageType());
		assertNotNull(createdSms.getPhoneNumber());
		assertNotNull(createdSms.getTextSms());
		
		assertTrue(createdSms.getId() > 0);

		assertEquals(1L, createdSms.getClientId());
		assertEquals(MessageType.WHATSAPP, createdSms.getMessageType());
		assertEquals("phone", createdSms.getPhoneNumber());
		assertEquals("text", createdSms.getTextSms());
	}
	
	@Test
	@Order(2)
	public void testFindById() throws JsonMappingException, JsonProcessingException {
		mockSms();
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "https://localhost:8100")
				.setBasePath("/sms-service")
				.setPort(8100)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = given().spec(specification)
			.contentType("application/json")
				.pathParam("id", sms.getId())
			.when()
				.get("{id}")
			.then()
				.statusCode(200)
					.extract()
						.body()
							.asString();
		
		Sms createdSms = objectMapper.readValue(content, Sms.class);
		sms = createdSms;
		assertNotNull(createdSms.getId());
		assertNotNull(createdSms.getClientId());
		assertNotNull(createdSms.getMessageType());
		assertNotNull(createdSms.getPhoneNumber());
		assertNotNull(createdSms.getTextSms());
		
		assertTrue(createdSms.getId() > 0);
		
		assertEquals(1L, createdSms.getClientId());
		assertEquals(MessageType.WHATSAPP, createdSms.getMessageType());
		assertEquals("phone", createdSms.getPhoneNumber());
		assertEquals("text", createdSms.getTextSms());
	}
	

}
