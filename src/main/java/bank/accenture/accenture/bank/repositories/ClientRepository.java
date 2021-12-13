package bank.accenture.accenture.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.accenture.accenture.bank.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
