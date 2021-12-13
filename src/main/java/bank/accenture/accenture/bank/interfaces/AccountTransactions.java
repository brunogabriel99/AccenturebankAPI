package bank.accenture.accenture.bank.interfaces;

public interface AccountTransactions {

	void transfer (Long id, Double value);
	void withdraw (Double value);
	void deposit (Double value);
}
