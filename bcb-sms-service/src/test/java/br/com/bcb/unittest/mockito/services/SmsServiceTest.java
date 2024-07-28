package br.com.bcb.unittest.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.bcb.enums.MessageType;
import br.com.bcb.model.Sms;
import br.com.bcb.repositories.SmsRepository;
import br.com.bcb.services.SmsService;
import br.com.bcb.unittest.mocks.MockSms;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class SmsServiceTest {
	MockSms mockSms;
	
    @Mock
    Environment environment;
	
	@InjectMocks
	private SmsService smsService;
	
	@Mock
	SmsRepository smsRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		mockSms = new MockSms();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreateSms() {
		Sms sms = mockSms.mockEntity(1);
		Sms persisted = sms;
		persisted.setId(1L);
		
		when(smsRepository.save(sms)).thenReturn(persisted);
		
		var result = smsService.createSms(sms);
		assertNotNull(result);
		assertEquals(1L, result.getClientId());
		assertEquals("phone1", result.getPhoneNumber());
		assertEquals(MessageType.WHATSAPP, result.getMessageType());
		assertEquals("text1", result.getTextSms());
	}
	
	@Test
	void testFindAll() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<Sms> sms = mockSms.mockEntityPage(pageable);
		when(smsRepository.findAll(pageable)).thenReturn(sms);
		
		var result = smsService.findAll(pageable);
		assertNotNull(result);
		
		assertNotNull(result.getContent().get(0));
		assertEquals(0, result.getContent().get(0).getClientId());
		assertEquals("phone0", result.getContent().get(0).getPhoneNumber());
		assertEquals(MessageType.SMS, result.getContent().get(0).getMessageType());
		assertEquals("text0", result.getContent().get(0).getTextSms());
		
		assertNotNull(result.getContent().get(1));
		assertEquals(1, result.getContent().get(1).getClientId());
		assertEquals("phone1", result.getContent().get(1).getPhoneNumber());
		assertEquals(MessageType.WHATSAPP, result.getContent().get(1).getMessageType());
		assertEquals("text1", result.getContent().get(1).getTextSms());
	}
	
	@Test
	void testFindByClientId() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<Sms> sms = mockSms.mockEntityPage(pageable);
		
		when(smsRepository.findByClientId(0L, pageable)).thenReturn(sms);
		
		var result = smsService.findByClientId(0L, pageable);
		assertNotNull(result);
		
		assertNotNull(result.getContent().get(0));
		assertEquals(0, result.getContent().get(0).getClientId());
		assertEquals("phone0", result.getContent().get(0).getPhoneNumber());
		assertEquals(MessageType.SMS, result.getContent().get(0).getMessageType());
		assertEquals("text0", result.getContent().get(0).getTextSms());
	}
	
	@Test
	void testFindById() {
		Sms sms = mockSms.mockEntity(1);
		sms.setId(1L);
		
		when(smsRepository.findById(1L)).thenReturn(Optional.of(sms));
		
		var result = smsService.findById(1L);
		assertNotNull(result);
		assertEquals(1L, result.getClientId());
		assertEquals("phone1", result.getPhoneNumber());
		assertEquals(MessageType.WHATSAPP, result.getMessageType());
		assertEquals("text1", result.getTextSms());
	}
	
	@Test
	void testUpdate() {
		Sms sms = mockSms.mockEntity(1);
		sms.setId(1L);
		var persisted = sms;
		when(smsRepository.findById(1L)).thenReturn(Optional.of(sms));
		when(smsRepository.save(sms)).thenReturn(persisted);
		
		var result = smsService.updateSms(sms);
		
		assertNotNull(result);
		assertEquals(1L, result.getClientId());
		assertEquals("phone1", result.getPhoneNumber());
		assertEquals(MessageType.WHATSAPP, result.getMessageType());
		assertEquals("text1", result.getTextSms());
	}
	
	@Test
	void testDelete() {
		Sms sms = mockSms.mockEntity(1);
		sms.setId(1L);
		
		when(smsRepository.findById(1L)).thenReturn(Optional.of(sms));
		
		smsService.deleteSms(1L);
	}
}
