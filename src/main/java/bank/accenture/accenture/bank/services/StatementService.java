package bank.accenture.accenture.bank.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.accenture.accenture.bank.domain.CheckingAccount;
import bank.accenture.accenture.bank.domain.Statement;
import bank.accenture.accenture.bank.repositories.StatementRepository;
import bank.accenture.accenture.bank.services.exceptions.ResourceNotFoundException;

@Service
public class StatementService {

	@Autowired
	private StatementRepository repository;
	
	@Autowired
	private CheckingAccountService checkingAccountService;
	
	
	public List<Statement> getAll() {
		return repository.findAll();
	}
	
	public Statement getById(Long id) {
		Optional<Statement> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Statement> getAllByAccount(Long id) {
		CheckingAccount checkingAccount = checkingAccountService.getAccountByClientId(id);
		
		List<Statement> statementByCheckingAccountId = repository.findByCheckingAccount(checkingAccount);
		
		if (statementByCheckingAccountId.isEmpty() || statementByCheckingAccountId == null) {
			throw new ResourceNotFoundException("Empty Statement");
		}
		return statementByCheckingAccountId;
	}
}
