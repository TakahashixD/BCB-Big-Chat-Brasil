package br.com.bcb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bcb.model.Sms;

@RestController
@RequestMapping("sms")
public class SmsController {
	
	@Autowired
	private Environment enviroment;
	
	@GetMapping(value = "/{id}")
	public Sms findById(@PathVariable("id") Long id) {
		var port = enviroment.getProperty("local.server.port");
		return null;
	}
}
