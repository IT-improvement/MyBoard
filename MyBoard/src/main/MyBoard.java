package main;

import java.util.*;

import controller.UserManager;
import unit.BoardBlind;
import unit.BoardNonBlind;
import unit.User;

public class MyBoard {

	private Scanner scan = new Scanner(System.in);

	private Map<User, ArrayList<BoardNonBlind>> list = new HashMap<>();
	private Set<BoardBlind> unknownList = new HashSet<>();
	private User user;
	private UserManager userManager;

	public MyBoard() {
		userManager = UserManager.getInstance();
	}

	private int inputNum(String message) {
		int num = -1;
		try {
			System.out.print(message + " : ");
			String temp = scan.next();
			num = Integer.parseInt(temp);
		} catch (Exception e) {
			System.err.println("숫자만 입력하세요");
		}
		return num;
	}

	private String inputString(String message) {
		System.out.print(message + " : ");
		return scan.next();
	}

	public void run() {
		while (true) {
			printCheck();
			printStart();
			int sel = inputNum("메뉴입력");
			Start(sel);
		}
	}

	/* check */
	private void printCheck() {
		System.out.println("list: " + list);
		System.out.println("log: " + user);
	}

	/* print */
	// print start Menu
	private void printStart() {
		System.out.println("1)회원가입");
		System.out.println("2)로그인");
		System.out.println("3)게시글보기");
	}

	// print Admin Menu
	private void printAdmin() {
		System.out.println("1)유저관리");
		System.out.println("2)게시글관리");
	}

	// print User Menu
	private void printUser() {
		System.out.println("1)회원탈퇴");
		System.out.println("2)로그아웃");
		System.out.println("3)게시글작성");
		System.out.println("4)마이페이지");
	}

	// print userSubmenu(4.마이페이지)
	private void printUserSubMenu() {
		System.out.println("1)회원정보");
		System.out.println("2)게시글 관리");
	}

	/* menu Mehthod */
	// start menu
	private void Start(int sel) {
		switch (sel) {
		case 1:
			signUp();
			break;
		case 2:
			logIn();
			break;
		case 3:
			break;
		default:
			break;
		}
	}

	// userMenu
	private void User(int sel) {
		switch (sel) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
		}
	}

	/* start method */
	// sighUp Method
	private void signUp() {
		String id = inputString("ID");
		id = userManager.idCheck(id);
		if (id.equals("")) {
			System.err.println("있는아이디입니다.");
			return;
		}

		String name = inputString("name");
		String pw = inputString("pw");
		String pwCheck = inputString("pwCheck");
		if (!pw.equals(pwCheck)) {
			System.err.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		addUser(id, pwCheck, name);
	}

	// addUser Method
	private void addUser(String id, String pw, String name) {
		User user = new User(id, pw, name);
		userManager.addUser(user); // UserManager class-> add of userList
		list.put(user, new ArrayList<BoardNonBlind>()); // add of list
	}

	// login Method
	private void logIn() {
		String id = inputString("ID");
		String pw = inputString("PW");

		if (!userManager.checkLogin(id, pw)) {
			System.err.println("없는아이디거나 비밀번호가 일치하지 않습니다.");
		}
		this.user = userManager.selectUser(id);
	}
}
