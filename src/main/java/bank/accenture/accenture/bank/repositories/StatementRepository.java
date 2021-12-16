package bank.accenture.accenture.bank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.accenture.accenture.bank.domain.CheckingAccount;
import bank.accenture.accenture.bank.domain.Statement;

public interface StatementRepository extends JpaRepository<Statement, Long>{

	List<Statement> findByCheckingAccount(CheckingAccount checkingAccount);
}
