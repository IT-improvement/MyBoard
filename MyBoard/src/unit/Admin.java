package unit;

import dto.Person;
import model.Adminable;

public class Admin extends Person implements Adminable {

	public Admin(String id, String pw) {
		super(id, pw);
	}

}
