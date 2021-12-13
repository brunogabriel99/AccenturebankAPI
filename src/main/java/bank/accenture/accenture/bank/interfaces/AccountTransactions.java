package bank.accenture.accenture.bank.interfaces;

import bank.accenture.accenture.bank.domain.Statement;

public interface AccountTransactions {

	void transfer (Long idSender, Long idRecipient, Double value);
	void withdraw (Long id, Double value);
	Statement deposit (Long id, Double value);
}
