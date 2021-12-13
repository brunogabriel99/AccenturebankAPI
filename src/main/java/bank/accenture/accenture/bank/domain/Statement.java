package bank.accenture.accenture.bank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import bank.accenture.accenture.bank.enums.OperationTypeEnum;

@Entity
public class Statement implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double OperationValue;
	
	private OperationTypeEnum operationType;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
	private LocalDateTime movimentDate;
	
	@ManyToOne
	@JoinColumn(name = "account_checking_id")
	private CheckingAccount checkingAccount;
	
	public Statement() {
	}
	
	public Statement(Double operationValue, OperationTypeEnum operationType, LocalDateTime movimentDate,
			CheckingAccount checkingAccount) {
		OperationValue = operationValue;
		this.operationType = operationType;
		this.movimentDate = movimentDate;
		this.checkingAccount = checkingAccount;
	}



	public Statement(Long id, Double operationValue, OperationTypeEnum operationType, LocalDateTime movimentDate,
			CheckingAccount checkingAccount) {
		this.id = id;
		OperationValue = operationValue;
		this.operationType = operationType;
		this.movimentDate = movimentDate;
		this.checkingAccount = checkingAccount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getOperationValue() {
		return OperationValue;
	}

	public void setOperationValue(Double operationValue) {
		OperationValue = operationValue;
	}

	public OperationTypeEnum getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationTypeEnum operationType) {
		this.operationType = operationType;
	}

	public LocalDateTime getMovimentDate() {
		return movimentDate;
	}

	public void setMovimentDate(LocalDateTime movimentDate) {
		this.movimentDate = movimentDate;
	}

	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}

	public void setCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Statement other = (Statement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
