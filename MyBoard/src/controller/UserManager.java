package controller;

import java.util.ArrayList;

import unit.User;

public class UserManager {
	private UserManager() {

	}

	private static ArrayList<User> userList = new ArrayList<>();
	private static UserManager instance = new UserManager();

	public static UserManager getInstance() {
		return instance;
	}
}
