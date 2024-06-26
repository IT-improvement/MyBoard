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

	public boolean checkUser(String id, String pw) {
		for (User user : userList)
			if (user.getId().equals(id))
				if (user.getPw().equals(pw))
					return true;

		return false;
	}

	public User selectUser(String id) {
		User result = null;
		for (User user : userList)
			if (user.getId().equals(id))
				result = user;
		return result;
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	/* U(update) */
	public User updateUser(User user, String data, boolean type) {
		User target = user;
		userList.remove(target);
		if (type) {
			target.setPw(data);
		} else {
			target.setName(data);
		}
		userList.add(target);
		return target;
	}

	/* D(delete) */
	public void deleteUser(User user) {
		userList.remove(user);
	}
}
