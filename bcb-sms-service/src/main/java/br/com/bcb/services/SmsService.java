package br.com.bcb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bcb.model.Sms;
import br.com.bcb.repositories.SmsRepository;

@Service
public class SmsService {

	@Autowired
	private Environment enviroment;
	
	@Autowired
	private SmsRepository smsRepository;
	
	public Sms createSms(Sms sms) {
		return smsRepository.save(sms);
	}
	
	public Page<Sms> findAll(Pageable pageable) {	
		var smsPage = smsRepository.findAll(pageable);
		var port = enviroment.getProperty("local.server.port");
		smsPage.getContent().stream().forEach(c -> c.setEnviroment(port));
		return smsPage;
	}
	
	public Sms findById(Long id) {
		var sms = smsRepository.findById(id);
		if(sms.isEmpty()) throw new RuntimeException("Sms not found!");
		var port = enviroment.getProperty("local.server.port");
		sms.get().setEnviroment(port);
		return sms.get();
	}
	
	public Page<Sms> findByClientId(Long client_id, Pageable pageable) {
		var sms = smsRepository.findByClientId(client_id, pageable);
		if(sms.isEmpty()) throw new RuntimeException("Sms not found!");
		var port = enviroment.getProperty("local.server.port");
		sms.getContent().stream().forEach(c -> c.setEnviroment(port));
		return sms;
	}
	
	public Sms updateSms(Sms sms) {
		var smsFound = smsRepository.findById(sms.getId());
		if(smsFound.isEmpty()) throw new RuntimeException("Sms not found!");
		var smsReturn = smsFound.get();
		var port = enviroment.getProperty("local.server.port");
		smsReturn.setClientId(sms.getClientId());
		smsReturn.setMessageType(sms.getMessageType());
		smsReturn.setPhoneNumber(sms.getPhoneNumber());
		smsReturn.setTextSms(sms.getTextSms());
		smsReturn = smsRepository.save(smsReturn);
		smsReturn.setEnviroment(port);
		return smsReturn;
	}
	
	public void deleteSms( Long id) {
		var smsFound = smsRepository.findById(id);
		if(smsFound.isEmpty()) throw new RuntimeException("Sms not found!");
		smsRepository.deleteById(id);
	}
	
}
