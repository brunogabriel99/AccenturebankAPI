package bank.accenture.accenture.bank.enums;

public enum OperationTypeEnum {

	WITHDRAW(1, "WITHDRAW"),
	DEPOSIT(2, "DEPOSIT"),
	TRANSFER(3, "TRANSFER"),
	DEPOSIT_TRANSFER(4, "DEPOSIT_TRANSFER");
	
	private int cod;
	private String description;
	
	private OperationTypeEnum(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public static OperationTypeEnum toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (OperationTypeEnum x : OperationTypeEnum.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid id" + cod);
	}
}
