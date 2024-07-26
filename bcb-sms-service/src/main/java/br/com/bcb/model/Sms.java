package br.com.bcb.model;

import java.io.Serializable;

import br.com.bcb.enums.MessageType;

public class Sms implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long clientId;
	private String phoneNumber;
	private MessageType messageType;
	private String textSms;
	private String enviroment;
	
	
}
