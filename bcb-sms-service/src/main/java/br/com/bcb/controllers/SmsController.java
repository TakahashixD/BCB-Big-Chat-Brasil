package br.com.bcb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bcb.model.Sms;
import br.com.bcb.services.SmsService;


@RestController
@RequestMapping("sms-service")
public class SmsController {
		
	@Autowired
	private SmsService smsService;
	
	@PostMapping()
	public Sms createSms(@RequestBody Sms sms) {
		return smsService.createSms(sms);
	}
	
	@GetMapping(value="/{id}")
	public Sms findById(@PathVariable("id") Long id) {
		return smsService.findById(id);
	}
	
	@GetMapping(value="/byClient/{id}")
	public Sms findByClientId(@PathVariable("id") Long client_id) {
		return smsService.findByClientId(client_id);
	}
	
	@PutMapping()
	public Sms updateSms(@RequestBody Sms sms) {
		return smsService.updateSms(sms);
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteSms(@PathVariable("id") Long id) {
		smsService.deleteSms(id);
	}
}
