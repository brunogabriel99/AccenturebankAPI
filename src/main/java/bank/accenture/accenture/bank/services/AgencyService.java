package bank.accenture.accenture.bank.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import bank.accenture.accenture.bank.domain.Agency;
import bank.accenture.accenture.bank.exceptions.DatabaseException;
import bank.accenture.accenture.bank.exceptions.RequiredFieldException;
import bank.accenture.accenture.bank.exceptions.ResourceNotFoundException;
import bank.accenture.accenture.bank.repositories.AgencyRepository;

@Service
public class AgencyService {

	@Autowired
	private AgencyRepository repository;
	
	@Autowired
	private CheckingAccountService checkingAccountService;

	public List<Agency> getAll() {
		return repository.findAll();
	}

	public Agency findById(long id) {
		Optional<Agency> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Agency findByClientId(Long id) {
		return checkingAccountService.getAccountByClientId(id).getAgency();
	}
	
	public Agency findByAccountsId(Long id) {
		return checkingAccountService.getById(id).getAgency();
	}
	
	public void insert(Agency agency) {
		validate(agency);
		repository.save(agency);
	}

	public void delete(Long id) {

		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Agency update(long id, Agency obj) {
		validate(obj);
		try {
			Agency agency = repository.getById(id);
			Agency newAgency = updateData(agency, obj);
			return repository.save(newAgency);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Agency not found");
		}

	}

	private Agency updateData(Agency agency, Agency obj) {
		agency.setName(obj.getName());
		agency.setAdress(obj.getAdress());
		agency.setPhone(obj.getPhone());
		return agency;
	}
	
	private void validate(Agency agency) {

		if (agency.getName() == null || agency.getName().isEmpty()) {
			throw new RequiredFieldException("The field must be mandatory");
		}
		if (agency.getAdress() == null || agency.getAdress().isEmpty()) {
			throw new RequiredFieldException("The field must be mandatory");
		}

		if (agency.getPhone() == null || agency.getPhone().isEmpty()) {
			throw new RequiredFieldException("The field must be mandatory");
		}
	}
}
