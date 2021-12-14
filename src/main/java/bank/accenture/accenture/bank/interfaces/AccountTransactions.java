package bank.accenture.accenture.bank.interfaces;

public interface AccountTransactions {

	Boolean transfer (Long idSender, Long idRecipient, Double value);
	Boolean withdraw (Long id, Double value);
	Boolean deposit (Long id, Double value);
}
