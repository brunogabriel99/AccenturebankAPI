package bank.accenture.accenture.bank.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import bank.accenture.accenture.bank.DTO.AgencyDTO;
import bank.accenture.accenture.bank.domain.Agency;
import bank.accenture.accenture.bank.services.AgencyService;

@RestController
@RequestMapping(value = "/agencies")
public class AgencyController {

	@Autowired
	private AgencyService service;
	
	@GetMapping
	public ResponseEntity<List<Agency>> findAll() {
		List<Agency> list = service.getAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Agency> findById(@PathVariable Long id) {
		Agency c = service.findById(id);
		return ResponseEntity.ok().body(c);
	}
	@GetMapping(value = "/clients/{id}")
	public ResponseEntity<Agency> findByClientId(@PathVariable Long id) {
		Agency c = service.findByClientId(id);
		return ResponseEntity.ok().body(c);
	}
	
	@GetMapping(value = "/accounts/{id}")
	public ResponseEntity<Agency> findByAccountsId(@PathVariable Long id) {
		Agency c = service.findByAccountsId(id);
		return ResponseEntity.ok().body(c);
	}
	
	@PostMapping
	public ResponseEntity<Agency> insert(@RequestBody @Valid AgencyDTO obj) {
		Agency agency = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(agency.getId()).toUri();
		return ResponseEntity.created(uri).body(agency);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Agency> update(@PathVariable Long id, @RequestBody @Valid AgencyDTO obj) {
		Agency agency = service.update(id, obj);
		return ResponseEntity.ok().body(agency);
	}
}
