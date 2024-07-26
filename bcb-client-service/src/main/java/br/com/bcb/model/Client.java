package br.com.bcb.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Table(name = "client")
@Entity
public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(nullable = false, length = 11)
	private String cpf;
	
	@Column(nullable = false, length = 14)
	private String cnpj;
	
	@Column(name = "company_name", nullable = false)
	private String companyName;
	
	@Column(nullable = false)
	private String plan;
	
	@Column(nullable = false)
	private Long credits;
	
	@Column(name = "max_limit", nullable = false)
	private Long maxLimit;
	
	@Transient
	private String environment;
	
	public Client() {
	}

	public Client(Long id, String name, String email, String phoneNumber, String cpf, String cnpj, String companyName,
			String plan, Long credits, Long maxLimit, String environment) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.companyName = companyName;
		this.plan = plan;
		this.maxLimit = maxLimit;
		this.environment = environment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public Long getCredits() {
		return credits;
	}

	public void setCredits(Long credits) {
		this.credits = credits;
	}

	public Long getLimit() {
		return maxLimit;
	}

	public void setLimit(Long maxLimit) {
		this.maxLimit = maxLimit;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpj, companyName, cpf, credits, email, environment, id, maxLimit, name, phoneNumber, plan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(companyName, other.companyName)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(credits, other.credits)
				&& Objects.equals(email, other.email) && Objects.equals(environment, other.environment)
				&& Objects.equals(id, other.id) && Objects.equals(maxLimit, other.maxLimit)
				&& Objects.equals(name, other.name) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(plan, other.plan);
	}
	
}
