package br.com.bcb.unittest.mockito.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

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
}
