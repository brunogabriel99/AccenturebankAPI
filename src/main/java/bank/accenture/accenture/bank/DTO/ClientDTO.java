package bank.accenture.accenture.bank.DTO;

import javax.validation.constraints.NotBlank;

public class ClientDTO {

	@NotBlank(message = "name's field required")
	private String name;
	
	@NotBlank(message = "CPF's field required")
	private String cpf;
	
	@NotBlank(message = "phone's field required")
	private String phone;
	
	public ClientDTO() {
	}

	public ClientDTO(String name,String cpf, String phone) {
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
