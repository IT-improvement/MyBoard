package main;

import java.util.*;

import controller.BoardManager;
import controller.UserManager;
import dto.Person;
import unit.Admin;
import unit.BoardBlind;
import unit.BoardNonBlind;
import unit.User;

public class MyBoard {

	private Scanner scan = new Scanner(System.in);

	private Map<User, ArrayList<BoardNonBlind>> list;
	public static Set<BoardBlind> unknownList;
	private Person user;
	private UserManager userManager;
	private BoardManager boardManager;
	private Admin admin;

	private int userCount;
	private int boardCount;

	public MyBoard() {
		userManager = UserManager.getInstance();
		boardManager = BoardManager.getInsatnce();

		list = new HashMap<>();
		unknownList = new HashSet<>();

		admin = new Admin();
		userCount = boardCount = 0;
		userCount++;
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
			start(sel);
			if (user != null) {
				if (user.isPower())
					admin();
				else
					user();
			}
		}
	}

	/* check */
	private void printCheck() {
		System.out.println("list: " + list);
		System.out.println("log: " + user);
		System.out.println("user수: " + userCount);
		System.out.println("board수: " + boardCount);
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
		System.out.println("1)로그아웃");
		System.out.println("2)유저관리");
		System.out.println("3)게시글관리");
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
		System.out.println("1)회원정보관리");
		System.out.println("2)게시글 관리");
	}

	// print userInfo
	private void printUserInfo() {
		System.out.println("1)회원정보보기");
		System.out.println("2)비밀번호 변경");
		System.out.println("3)이름 변경");
	}

	// print eachUserInfo
	private void printEachUserInfo() {
		User target = (User) user;
		System.out.print("아이디: " + target);
		System.out.println(" 이름: " + target.getName());
	}

	private void userBoardList() {
		int i=1;
		for (BoardNonBlind board : BoardManager.boardList) {
			System.out.println((i++)+")제목: "+board.getTitle()+" 작성일: " +board.getDate());
		}
	}

	/* menu Mehthod */
	// start menu
	private void start(int sel) {
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

	private void adminMenu(int sel) {
		switch (sel) {
		case 1:
			logOut();
			break;
		case 2:
			break;
		case 3:
			break;
		default:
			break;
		}
	}

	// userMenu
	private void userMenu(int sel) {
		switch (sel) {
		case 1:
			signOut();
			break;
		case 2:
			logOut();
			break;
		case 3:
			addBoard();
			break;
		case 4:
			myPage();
			break;
		default:
			break;
		}
	}

	// myPage subMenu
	private void userSubMenu(int sel) {
		switch (sel) {
		case 1:
			userInfo();
			break;
		case 2:
			userBoard();
			break;
		default:
			break;
		}
	}

	// userInfo subMenu
	private void userInfoSubMenu(int sel) {
		switch (sel) {
		case 1:
			printEachUserInfo();
			break;
		case 2:
			changePw();
			break;
		case 3:
			changName();
			break;
		default:
			break;
		}
	}

	// logOut
	private void logOut() {
		user = null;
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
		userCount++;
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
		if (id.equals(admin.getId()) && pw.equals(admin.pw)) {
			this.user = admin;
			return;
		}
		if (!userManager.checkUser(id, pw)) {
			System.err.println("없는아이디거나 비밀번호가 일치하지 않습니다.");
			return;
		}
		this.user = userManager.selectUser(id);
		BoardManager.boardList = list.get((User) user);

	}

	// addBoard
	private void addBoard() {
		String title = inputString("제목");
		String content = inputString("내용");
		User target = (User) user;
		boardManager.addBoard(target.getId(), title, content);
		list.replace((User) user, BoardManager.boardList);
		boardCount++;
	}

	/* user Method */
	// user Method
	private void user() {
		while (true) {
			printUser();
			int sel = inputNum("사용자메뉴 선택");
			userMenu(sel);
			if (user == null)
				break;
		}
	}

	// signOut Method
	private void signOut() {
		String pw = inputString("Pw");
		if (!userManager.checkUser(user.getId(), pw)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		deleteUser();
		userCount--;
		logOut();
	}

	// delete User
	private void deleteUser() {
		User target = (User) user;
		userManager.deleteUser(target);
		list.remove(target);
	}

	// myPage Method
	private void myPage() {
		printUserSubMenu();
		int sel = inputNum("마이페이지메뉴");
		userSubMenu(sel);
	}

	// userInfo Method
	private void userInfo() {
		printUserInfo();
		int sel = inputNum("회원정보메뉴");
		userInfoSubMenu(sel);
	}

	// change password Method
	private void changePw() {
		String oldPw = inputString("현재비밀번호");
		if (!userManager.checkUser(user.getId(), oldPw)) {
			System.err.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		String pw = inputString("새비밀번호");
		String pwCheck = inputString("한번더 입력");
		if (!pw.equals(pwCheck)) {
			System.err.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		changUser(pw, true);
	}

	// change name Method
	private void changName() {
		String name = inputString("이름");
		changUser(name, false);
	}

	// changUser Method
	private void changUser(String data, boolean type) {
		User oldDate = (User) user;
		list.remove(oldDate);
		user = userManager.updateUser(oldDate, data, type);
		list.put((User) user, BoardManager.boardList);
	}

	// user Board Method
	private void userBoard() {
		userBoardList();
		int sel = inputNum("삭제할 게시글 선택");
	}

	/* admin Method */
	// amdin Method
	private void admin() {
		while (true) {
			printAdmin();
			int sel = inputNum("관리자메뉴 선택");
			adminMenu(sel);
			if (user == null)
				break;
		}
	}
}
