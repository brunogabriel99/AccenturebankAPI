package bank.accenture.accenture.bank.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.accenture.accenture.bank.DTO.ClientDTO;
import bank.accenture.accenture.bank.domain.Client;
import bank.accenture.accenture.bank.mapper.ClientMapper;
import bank.accenture.accenture.bank.repositories.ClientRepository;
import bank.accenture.accenture.bank.services.exceptions.DatabaseException;
import bank.accenture.accenture.bank.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public List<Client> getAll() {
		return repository.findAll();
	}
	
	public Client findByid(Long id) {
		Optional<Client> client = repository.findById(id);
		return client.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Client insert (ClientDTO obj) {
		return repository.save(ClientMapper.INSTANCE.toClient(obj));
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
	
	public Client update(Long id, ClientDTO obj) {
		try {
		Client entity = repository.getById(id);
		Client newClient = ClientMapper.INSTANCE.toClient(obj);
		newClient.setId(entity.getId());
		return repository.save(newClient);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
}
