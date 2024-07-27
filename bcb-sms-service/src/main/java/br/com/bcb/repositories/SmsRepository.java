package br.com.bcb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bcb.model.Sms;

public interface SmsRepository extends JpaRepository<Sms, Long>{
	
	Optional<Sms> findByClientId(Long clientId);
}
