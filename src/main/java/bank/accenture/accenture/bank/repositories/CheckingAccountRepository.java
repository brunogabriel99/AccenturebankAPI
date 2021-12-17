package bank.accenture.accenture.bank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.accenture.accenture.bank.domain.CheckingAccount;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long>{
	
	public Optional<CheckingAccount> findByClientId(Long id);
}
