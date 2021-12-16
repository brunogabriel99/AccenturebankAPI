package bank.accenture.accenture.bank.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import bank.accenture.accenture.bank.domain.Statement;
import bank.accenture.accenture.bank.enums.OperationTypeEnum;

public class TransferVoucherDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	

	private Long id;
	
	private OperationTypeEnum operationType;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
	private LocalDateTime movimentDate;
	
	private Double operationValue;
	
	private Long accountSenderId;
	
	private Long accountDestinyId;
	
	public TransferVoucherDTO() {	}
	
	public TransferVoucherDTO(Statement st, Long accountSenderId,Long accountDestinyId) {
		this.id = st.getId();
		this.operationValue = st.getOperationValue();
		this.operationType = OperationTypeEnum.TRANSFER;
		this.movimentDate = st.getMovimentDate();
		this.accountSenderId = accountSenderId;
		this.accountDestinyId = accountDestinyId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getOperationValue() {
		return operationValue;
	}

	public void setOperationValue(Double operationValue) {
		this.operationValue = operationValue;
	}

	public Long getAccountSenderId() {
		return accountSenderId;
	}

	public void setAccountSenderId(Long accountSenderId) {
		this.accountSenderId = accountSenderId;
	}

	public Long getAccountDestinyId() {
		return accountDestinyId;
	}

	public void setAccountDestinyId(Long accountDestinyId) {
		this.accountDestinyId = accountDestinyId;
	}
	
}
