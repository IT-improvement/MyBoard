package controller;

public class UserManager {
	private UserManager() {

	}

	private static UserManager instance = new UserManager();

	public static UserManager getInstance() {
		return instance;
	}
}
