package br.com.bcb.model;

import java.io.Serializable;
import java.util.Objects;

import br.com.bcb.enums.MessageType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Table(name = "sms")
@Entity
public class Sms implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "client_id", nullable = false)
	private Long clientId;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name = "message_type", length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private MessageType messageType;
	
	@Column(name = "text_sms", nullable = false)
	private String textSms;
	
	@Transient
	private String enviroment;
	
	public Sms() {
	}

	public Sms(Long id, Long clientId, String phoneNumber, MessageType messageType, String textSms, String enviroment) {
		this.id = id;
		this.clientId = clientId;
		this.phoneNumber = phoneNumber;
		this.messageType = messageType;
		this.textSms = textSms;
		this.enviroment = enviroment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public String getTextSms() {
		return textSms;
	}

	public void setTextSms(String textSms) {
		this.textSms = textSms;
	}

	public String getEnviroment() {
		return enviroment;
	}

	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, enviroment, id, messageType, phoneNumber, textSms);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sms other = (Sms) obj;
		return Objects.equals(clientId, other.clientId) && Objects.equals(enviroment, other.enviroment)
				&& Objects.equals(id, other.id) && messageType == other.messageType
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(textSms, other.textSms);
	}

}
