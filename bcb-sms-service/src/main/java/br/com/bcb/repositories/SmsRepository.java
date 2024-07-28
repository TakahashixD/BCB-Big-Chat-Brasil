package br.com.bcb.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bcb.model.Sms;

public interface SmsRepository extends JpaRepository<Sms, Long>{
	
	Page<Sms> findByClientId(Long clientId, Pageable pageable);
}
