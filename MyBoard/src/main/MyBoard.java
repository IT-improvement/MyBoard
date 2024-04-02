package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import controller.BoardManager;
import controller.UserManager;
import dto.Board;
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
			// printCheck();
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
		System.out.println(unknownList);
	}

	/* print */
	// print start Menu
	private void printStart() {
		System.out.println("1)회원가입");
		System.out.println("2)로그인");
		System.out.println("3)게시글보기");
		System.out.println("4)게시글작성");
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

	// print userBoardList
	private void userBoardList() {
		int i = 1;
		for (BoardNonBlind board : BoardManager.boardList) {
			System.out.println((i++) + ")제목: " + board.getTitle() + " 작성일: " + board.getDate());
		}
	}

	// print userBoardInfo
	private void userBoardInfo(int sel) {
		Board board = BoardManager.boardList.get(sel);
		System.out.println(board);
	}

	// print userBoard Menu
	private void userBoardMenu() {
		System.out.println("1)게시글 수정");
		System.out.println("2)게시글 삭제");
		System.out.println("*)나가기");
	}

	// print not anonyMity Board List
	private void printBoardNonBlind() {
		System.out.println("=====실명게시판======");
		int i = 0;
		Set<User> ketSet = list.keySet();
		for (User user : ketSet) {
			if (user.getId().equals("admin"))
				continue;
			ArrayList<BoardNonBlind> boards = list.get(user);
			for (BoardNonBlind board : boards) {
				System.out.println((++i) + ")제목: " + board.getTitle() + " 글쓴이: " + board.getId());
			}
		}
		System.out.println("=====총 " + i + "개======");
	}

	// print anonyMity Board List
	private void printBoardBlind() {
		System.out.println("=====익명게시판======");
		int i = 0;
		for (BoardBlind board : unknownList) {
			System.out.println((++i) + ")제목: " + board.getTitle() + " 글쓴이: " + board.getId());
		}
		System.out.println("=====총 " + i + "개======");
	}

	// print all Board List
	private void printAllBoard() {
		printBoardNonBlind();
		printBoardBlind();
	}

	// print select all board guide message
	private void printAllBoardGuide() {
		System.out.println("보고싶은 게시글 테마 먼저 입력(실명 / 익명)후\n" + "번호 입력 ");
		System.out.println("뒤로가기: 0번");
	}

	// print manager User
	private void printManageUser() {
		ArrayList<User> userList = userManager.getUserList();

		int i = 0;
		for (User user : userList) {
			System.out.println((++i) + ")아이디: " + user.getId());
		}
	}

	private void printdeleteUser() {
		System.out.println("1)삭제");
		System.out.println("*)뒤로가기");
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
			allBoard();
			break;
		case 4:
			createBoard();
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
			manageUser();
			break;
		case 3:
			manageBoard();
			break;
		case 4:
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

	private void userBoardSubMenu(int sel, int idx) {
		switch (sel) {
		case 1:
			userEditBoard(idx);
			break;
		case 2:
			userDeleteBoard(idx);
			break;
		default:
			break;
		}
	}

	private void managedeleteUser(int sel, User user) {
		switch (sel) {
		case 1:
			deleteUser(user);
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

	// create blind board
	private void createBoard() {
		String title = inputString("제목입력");
		String content = inputString("내용입력");
		boardManager.addBoard(title, content);
	}

	// allBoard method
	private void allBoard() {
		printAllBoard();
		printAllBoardGuide();
		String type = inputString("종류입력");
		if (type.equals("0")) {
			return;
		}
		int index = inputNum("번호 입력") - 1;
		printBoardInfo(type, index);
	}

	private void printBoardInfo(String type, int index) {
		Board target = null;
		if (type.equals("실명")) {
			int i = 0;
			Set<User> ketSet = list.keySet();
			for (User user : ketSet) {
				if (user.getId().equals("admin"))
					continue;
				ArrayList<BoardNonBlind> boards = list.get(user);
				for (BoardNonBlind board : boards) {
					if (i == index)
						target = board;
					++i;
				}
			}
		} else if (type.equals("익명")) {
			int i = 0;
			for (BoardBlind board : unknownList) {
				if (i == index)
					target = board;
				i++;
			}
		}
		if (target == null) {
			return;
		}
		System.out.println(target);
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

	// addBoard
	private void addBoard() {
		String title = inputString("제목");
		String content = inputString("내용");
		User target = (User) user;
		boardManager.addBoard(target.getId(), title, content);
		updateUserBoard();
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
		int idx = inputNum("게시글 선택") - 1;
		userBoardInfo(idx);
		userBoardMenu();
		int sel = inputNum("메뉴 선텍");
		userBoardSubMenu(sel, idx);
	}

	private void userEditBoard(int idx) {
		Board board = BoardManager.boardList.get(idx);
		System.out.println("제목: " + board.getTitle());
		String content = inputString("내용입력");
		boardManager.updateBoard(board, content);
		updateUserBoard();
	}

	private void userDeleteBoard(int idx) {
		BoardManager.boardList.remove(idx);
		updateUserBoard();
	}

	private void updateUserBoard() {
		list.replace((User) user, BoardManager.boardList);
		boardCount++;
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

	// print manager User
	private User printTargetUser(int sel) {
		ArrayList<User> userList = userManager.getUserList();
		int i = 0;
		for (User user : userList) {
			++i;
			if (i == sel)
				return user;
		}
		return null;
	}

	// manage User
	private void manageUser() {
		printManageUser();
		int sel = inputNum("보고싶은 번호 입력(뒤로가기:0)");
		if (sel == 0)
			return;
		User user = printTargetUser(sel);
		if (user == null) {
			System.out.println("없는번호입니다.");
			return;
		}
		String result = "아이디: " + user.getId();
		result += "\n비밀번호: " + user.getPw();
		result += "\n이름: " + user.getName();
		System.out.println(result);
		printdeleteUser();
		int menu = inputNum("메뉴선택");
		managedeleteUser(menu, user);
	}

	// deleteUser
	private void deleteUser(User user) {
		list.remove(user);
		userManager.deleteUser(user);
	}

	// manage
	private void manageBoard() {
		printAllBoard();
	}
}
