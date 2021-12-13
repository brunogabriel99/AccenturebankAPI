package bank.accenture.accenture.bank.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import bank.accenture.accenture.bank.domain.Client;
import bank.accenture.accenture.bank.exceptions.DatabaseException;
import bank.accenture.accenture.bank.exceptions.InvalidCpfException;
import bank.accenture.accenture.bank.exceptions.RequiredFieldException;
import bank.accenture.accenture.bank.exceptions.ResourceNotFoundException;
import bank.accenture.accenture.bank.repositories.ClientRepository;
import bank.accenture.accenture.bank.util.CpfValidator;

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
	
	public void insert (Client client) {
		validate(client);
		repository.save(client);
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
	
	public Client update(Long id, Client obj) {
		try {
		Client entity = repository.getById(id);
		Client newClient = updateData(entity, obj);
		return repository.save(newClient);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private Client updateData(Client client, Client obj) {
		client.setName(obj.getName());
		client.setCpf(obj.getCpf());
		client.setPhone(obj.getPhone());
		return client;
	}
	
	private void validate(Client client) {
		String cpf = client.getCpf();
			
			if (client.getName() == null || client.getName().isEmpty()) {
				throw new RequiredFieldException("The field name must be mandatory");
			}
			if (client.getCpf() == null || client.getCpf().isEmpty()) {
				throw new RequiredFieldException("The field cpf must be mandatory");
			}
			if (!CpfValidator.isCPF(cpf)) {
				throw new InvalidCpfException("Invalid CPF");
			}
			if (client.getPhone() == null || client.getPhone().isEmpty()) {
				throw new RequiredFieldException("The field phone must be mandatory");
			}

		}
}
