package br.com.bcb.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bcb.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("SELECT c FROM Client c WHERE c.name LIKE LOWER(CONCAT ('%',:name,'%'))")
	Page<Client> findClientsByName(@Param("name") String name, Pageable pageable);
}
