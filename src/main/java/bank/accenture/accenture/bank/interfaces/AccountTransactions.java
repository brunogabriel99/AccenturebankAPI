package bank.accenture.accenture.bank.interfaces;

import bank.accenture.accenture.bank.domain.Statement;

public interface AccountTransactions {

	Boolean transfer (Long idSender, Long idRecipient, Double value);
	Boolean withdraw (Long id, Double value);
	Boolean deposit (Long id, Double value);
}
