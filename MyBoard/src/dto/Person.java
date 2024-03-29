package dto;

import model.Adminable;

public abstract class Person {
	private String id;
	public String pw;
	private boolean power;

	public Person(String id, String pw) {
		this.id = id;
		this.pw = pw;
		power = this instanceof Adminable ? true : false;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return id;
	}
}
