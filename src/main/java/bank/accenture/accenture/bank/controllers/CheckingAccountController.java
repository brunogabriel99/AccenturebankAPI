package bank.accenture.accenture.bank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.accenture.accenture.bank.domain.CheckingAccount;
import bank.accenture.accenture.bank.domain.Statement;
import bank.accenture.accenture.bank.services.CheckingAccountService;

@RestController
@RequestMapping(value = "/checkingaccounts")
public class CheckingAccountController {

	@Autowired
	private CheckingAccountService service;
	
	@GetMapping
	public ResponseEntity<List<CheckingAccount>> getAll () {
		List<CheckingAccount> list = service.getAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CheckingAccount> findById(Long id) {
		CheckingAccount obj = service.getById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/*@PostMapping
	public ResponseEntity<CheckingAccount> insert (Long id1, Long id2) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c1.getId()).toUri();
		return ResponseEntity.created(uri).body(c1);
	}*/
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("deposito/{id}/{value}")
	public ResponseEntity<Statement> deposit(@PathVariable("id") Long id, @PathVariable("value") Double value) {
		Statement statement = service.deposit(id, value);;
		return ResponseEntity.ok().body(statement);
		
	}
	
}
