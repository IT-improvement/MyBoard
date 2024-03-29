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
			printStart();
			int sel = inputNum("메뉴입력");
		}
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
	
	//print userSubmenu(4.마이페이지)
	private void printUserSubMenu() {
		System.out.println("1)회원정보");
		System.out.println("2)게시글 관리");
	}
	
	
}
