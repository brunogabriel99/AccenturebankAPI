package bank.accenture.accenture.bank.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import bank.accenture.accenture.bank.DTO.CheckingAccountDTO;
import bank.accenture.accenture.bank.DTO.DepositVoucherDTO;
import bank.accenture.accenture.bank.DTO.TransferVoucherDTO;
import bank.accenture.accenture.bank.DTO.WithdrawVoucherDTO;
import bank.accenture.accenture.bank.domain.Agency;
import bank.accenture.accenture.bank.domain.CheckingAccount;
import bank.accenture.accenture.bank.domain.Client;
import bank.accenture.accenture.bank.domain.Statement;
import bank.accenture.accenture.bank.enums.OperationTypeEnum;
import bank.accenture.accenture.bank.interfaces.AccountTransactions;
import bank.accenture.accenture.bank.repositories.CheckingAccountRepository;
import bank.accenture.accenture.bank.repositories.StatementRepository;
import bank.accenture.accenture.bank.services.exceptions.BalanceException;
import bank.accenture.accenture.bank.services.exceptions.DatabaseException;
import bank.accenture.accenture.bank.services.exceptions.RequiredFieldException;
import bank.accenture.accenture.bank.services.exceptions.ResourceNotFoundException;

@Service
public class CheckingAccountService implements AccountTransactions {

	@Autowired
	private CheckingAccountRepository repository;

	@Autowired
	private StatementRepository statementRepository;
	
	@Autowired
	private AgencyService agencyService;
	
	@Autowired
	private ClientService clientService;

	public List<CheckingAccount> getAll() {
		return repository.findAll();
	}

	public CheckingAccount getById(Long id) {
		Optional<CheckingAccount> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public CheckingAccount getAccountByClientId(Long id) {
		
			Optional<CheckingAccount> obj =  repository.findByClientId(id);
			return obj.orElseThrow(() -> new ResourceNotFoundException(id));	
	}
	
	public Double getCheckingAccountBalance(Long id) {
		Optional<CheckingAccount> obj = repository.findById(id);
		return obj.get().getBalance();
	}

	public CheckingAccount save(CheckingAccountDTO checkingAccountDTO) {
			validate(checkingAccountDTO);
			Client returnClient = clientService.findByid(checkingAccountDTO.getClientId());
			Agency returnAgency = agencyService.findById(checkingAccountDTO.getAgencyId());
			
			Client client = new Client(checkingAccountDTO.getClientId());
			Agency agency = new Agency(checkingAccountDTO.getAgencyId());
			
			CheckingAccount checkingAccount = new CheckingAccount(generateCheckingAccountNumber(), 0.0, agency, client);
			CheckingAccount returnCheckingAccount = repository.save(checkingAccount);
			
			checkingAccount.setAgency(returnAgency);
			checkingAccount.setClient(returnClient);
			
			return returnCheckingAccount;
	}

	public Boolean delete(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

	@Override
	public TransferVoucherDTO transfer(Long idSender, Long idRecipient, Double value) {
		Optional<CheckingAccount> accountSender = repository.findById(idSender);
		Optional<CheckingAccount> accountRecipient = repository.findById(idRecipient);

		if (idSender == idRecipient) {
			throw new BalanceException("You can't transfer to yourself");
		}

		if (value <= 0) {
			throw new BalanceException("Insufficient balance");
		}

		double accountSenderBalance = getCheckingAccountBalance(idSender);
		double accountRecipientBalance = getCheckingAccountBalance(idRecipient);

		if (accountSenderBalance >= value) {
			accountSender.get().setBalance(accountSenderBalance - value);
			accountRecipient.get().setBalance(accountRecipientBalance + value);
		}

		Statement statement = new Statement(value, OperationTypeEnum.TRANSFER, LocalDateTime.now(),
				accountSender.get());
		Statement statement2 = new Statement(value, OperationTypeEnum.DEPOSIT_TRANSFER, LocalDateTime.now(),
				accountRecipient.get());

		statementRepository.saveAll(Arrays.asList(statement, statement2));
		
		TransferVoucherDTO transferVoucher = new TransferVoucherDTO(statement, idSender, idRecipient);
		
		return transferVoucher;
	}

	@Override
	public WithdrawVoucherDTO withdraw(Long id, Double value) {
		if (getCheckingAccountBalance(id) < value || value <= 0) {
			throw new BalanceException("Insufficient balance");
		}
		repository.findById(id).get().setBalance(getCheckingAccountBalance(id) - value);

		CheckingAccount checkingAccout = this.getById(id);

		Statement statement = new Statement(value, OperationTypeEnum.WITHDRAW, LocalDateTime.now(), checkingAccout);

		statementRepository.save(statement);
		WithdrawVoucherDTO wv = new WithdrawVoucherDTO(statement, id);
		return wv;
	}

	@Override
	public DepositVoucherDTO deposit(Long id, Double value) {

		if (value <= 0) {
			throw new BalanceException("Insufficient deposit balance");
		}
		repository.findById(id).get().setBalance(getCheckingAccountBalance(id) + value);

		CheckingAccount checkingAccout = this.getById(id);

		Statement statement = new Statement(value, OperationTypeEnum.DEPOSIT, LocalDateTime.now(), checkingAccout);

		statementRepository.save(statement);
		
		DepositVoucherDTO depositDTO = new DepositVoucherDTO(statement, id);
		
		return depositDTO;

	}

	private void validate(CheckingAccountDTO obj) {
		if (obj.getAgencyId() == null) {
			throw new RequiredFieldException("Agency cannot be null");
		}
		if (obj.getClientId() == null) {
			throw new RequiredFieldException("Client cannot be null");
		}
	}

	private String generateCheckingAccountNumber() {
		Integer size = this.getAll().size();
		int number = size + 1;
		String checkingAccountNumber = Integer.toString(number);
		return checkingAccountNumber;
	}
}
