package bank.accenture.accenture.bank.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class AgencyDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@NotBlank(message = "name's field required")
	private String name;
	
	@NotBlank(message = "adress's field required")
	private String adress;
	
	@NotBlank(message = "phone's field required")
	private String phone;
	
	public AgencyDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
