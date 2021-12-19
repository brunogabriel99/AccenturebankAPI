package bank.accenture.accenture.bank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bank.accenture.accenture.bank.domain.CheckingAccount;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long>{
	
	public Optional<CheckingAccount> findByClientId(Long id);
}
