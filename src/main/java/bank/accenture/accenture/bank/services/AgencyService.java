package bank.accenture.accenture.bank.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.accenture.accenture.bank.DTO.AgencyDTO;
import bank.accenture.accenture.bank.domain.Agency;
import bank.accenture.accenture.bank.mapper.AgencyMapper;
import bank.accenture.accenture.bank.repositories.AgencyRepository;
import bank.accenture.accenture.bank.services.exceptions.DatabaseException;
import bank.accenture.accenture.bank.services.exceptions.ResourceNotFoundException;

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
	
	public Agency insert(AgencyDTO obj) {
		return repository.save(AgencyMapper.INSTANCE.toAgency(obj));
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
	
	@Transactional
	public Agency update(long id, AgencyDTO obj) {
		try {
			Agency agencyFind = repository.getById(id);
			Agency newAgency = AgencyMapper.INSTANCE.toAgency(obj);
			newAgency.setId(agencyFind.getId());
			return repository.save(newAgency);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Agency not found");
		}

	}
}
