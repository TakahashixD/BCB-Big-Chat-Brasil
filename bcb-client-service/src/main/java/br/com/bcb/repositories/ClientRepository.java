package br.com.bcb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bcb.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
}
