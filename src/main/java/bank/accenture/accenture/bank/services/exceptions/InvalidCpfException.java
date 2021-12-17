package bank.accenture.accenture.bank.services.exceptions;

public class InvalidCpfException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidCpfException(String msg) {
		super(msg);
	}

	
}