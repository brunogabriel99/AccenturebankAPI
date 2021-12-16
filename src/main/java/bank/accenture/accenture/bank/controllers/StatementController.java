package bank.accenture.accenture.bank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.accenture.accenture.bank.domain.Statement;
import bank.accenture.accenture.bank.services.StatementService;

@RestController
@RequestMapping(value = "/statements")
public class StatementController {

	@Autowired
	private StatementService service;
	
	@GetMapping
	public ResponseEntity<List<Statement>> getAll() {
		List<Statement> statements = service.getAll();
		return ResponseEntity.ok().body(statements);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Statement> getByid(@PathVariable Long id) {
		Statement obj = service.getById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<List<Statement>> getAllByAccount(@PathVariable long id) {
		List<Statement> obj = service.getAllByAccount(id);
		return ResponseEntity.ok().body(obj);
}
}
