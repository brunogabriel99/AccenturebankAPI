package bank.accenture.accenture.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.accenture.accenture.bank.domain.Statement;

public interface StatementRepository extends JpaRepository<Statement, Long>{

}
