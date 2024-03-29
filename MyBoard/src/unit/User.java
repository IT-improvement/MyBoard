package unit;

import dto.Person;

public class User extends Person {
	private String name;

	public User(String id, String pw, String name) {
		super(id, pw);
		this.name = name;
	}

	public String getPw() {
		return super.pw;
	}

	public void setPw(String pw) {
		super.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
