package bank.accenture.accenture.bank.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import bank.accenture.accenture.bank.domain.CheckingAccount;

public class CheckingAccountDTO {

	private Long id;
	
	@JsonProperty("agency")
	private Long agencyId;
	
	@JsonProperty("client")
	private Long clientId;

	public CheckingAccountDTO() {
	}
	
	public CheckingAccountDTO(CheckingAccount obj) {
		this.id = obj.getId();
		this.agencyId = obj.getAgency().getId();
		this.clientId = obj.getClient().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
}
