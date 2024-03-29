package controller;

import java.util.ArrayList;

import unit.User;

public class UserManager {
	private UserManager() {

	}

	private ArrayList<User> userList = new ArrayList<>();
	private static UserManager instance = new UserManager();

	public static UserManager getInstance() {
		return instance;
	}

	/* C(insert) */
	public void addUser(User user) {
		userList.add(user);
	}

	/* R(select) */
	public String idCheck(String id) {
		for (User user : userList)
			if (user.getId().equals(id))
				return "";
		return id;
	}
}
