package bank.accenture.accenture.bank.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import bank.accenture.accenture.bank.DTO.CheckingAccountDTO;
import bank.accenture.accenture.bank.domain.CheckingAccount;
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
	public ResponseEntity<CheckingAccount> findById(@PathVariable Long id) {
		CheckingAccount obj = service.getById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<CheckingAccount> insert (@RequestBody CheckingAccountDTO obj) {
		CheckingAccount checkingAccount = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(checkingAccount);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("deposit/{id}/{value}")
	public ResponseEntity<Boolean> deposit(@PathVariable("id") Long id, @PathVariable("value") Double value) {
		Boolean isDeposited = service.deposit(id, value);;
		return ResponseEntity.ok().body(isDeposited);
		
	}
	
	@PutMapping("withdraw/{id}/{value}")
	public ResponseEntity<Boolean> withdraw(@PathVariable("id") long id, @PathVariable("value") double value) {

		service.getById(id);

		Boolean isWithdraw = service.withdraw(id, value);
		return ResponseEntity.ok().body(isWithdraw);

	}
	
	@PutMapping("/transfer/{idSender}/{value}/{idDestiny}")
	public ResponseEntity<Boolean> transfer(@PathVariable("idSender") long idSender,@PathVariable("idDestiny") long idDestiny, @PathVariable("value") double value) {
		service.getById(idSender);

		Boolean transfer = service.transfer(idSender, idDestiny, value);
		return ResponseEntity.ok().body(transfer);

	}
	
	@GetMapping("balance/{id}")
	public ResponseEntity<Double> saldo(@PathVariable("id") long id) {
		service.getById(id);

		double balance = service.getCheckingAccountBalance(id);
		return ResponseEntity.ok().body(balance);

	}
	
}
