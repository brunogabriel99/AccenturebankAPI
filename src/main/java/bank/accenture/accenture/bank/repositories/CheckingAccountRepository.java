package bank.accenture.accenture.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.accenture.accenture.bank.domain.CheckingAccount;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long>{

}
