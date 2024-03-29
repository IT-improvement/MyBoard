package unit;

import dto.Person;
import model.Adminable;

public class Admin extends Person implements Adminable {

	public Admin() {
		super("admin", "1234");
	}

}
