package br.com.bcb.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bcb.model.Sms;
import br.com.bcb.repositories.SmsRepository;


@RestController
@RequestMapping("sms")
public class SmsController {
	
	@Autowired
	private Environment enviroment;
	
	@Autowired
	private SmsRepository smsRepository;
	
	@PostMapping()
	public Sms createSms(@RequestBody Sms sms) {
		return smsRepository.save(sms);
	}
	
	@GetMapping(value="/{id}")
	public Sms findById(@PathVariable("id") Long id) {
		var sms = smsRepository.findById(id);
		if(sms.isEmpty()) throw new RuntimeException("Sms not found!");
		var port = enviroment.getProperty("local.server.port");
		sms.get().setEnviroment(port);
		return sms.get();
	}
	
	@GetMapping(value="/byClient/{id}")
	public Sms findByClientId(@PathVariable("id") Long client_id) {
		var sms = smsRepository.findByClientId(client_id);
		if(sms.isEmpty()) throw new RuntimeException("Sms not found!");
		var port = enviroment.getProperty("local.server.port");
		sms.get().setEnviroment(port);
		return sms.get();
	}
	
	@PutMapping(value="/{id}")
	public Sms updateSms(@PathVariable("id") Long id) {
		return smsRepository.findById(id).get();
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteSms(@PathVariable("id") Long id) {
		smsRepository.deleteById(id);
	}
}
