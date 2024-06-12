package test.vo;

public class PersonalVO {
	private String name, email;
	
	public PersonalVO() {}
	
	public PersonalVO(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
}
