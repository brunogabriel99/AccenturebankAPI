package bank.accenture.accenture.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bank.accenture.accenture.bank.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
