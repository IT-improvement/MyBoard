package main;

import java.util.*;

import unit.BoardBlind;
import unit.BoardNonBlind;
import unit.User;

public class MyBoard {

	private Map<User, ArrayList<BoardNonBlind>> list = new HashMap<>();
	private Set<BoardBlind> unknownList = new HashSet<>();

	public MyBoard() {

	}

	public void run() {

	}

	/* print */
	//print start Menu 
	private void printStart() {
		System.out.println("1)회원가입");
		System.out.println("2)로그인");
		System.out.println("3)게시글보기");
	}
	
	//print Admin Menu
	private void printAdmin() {
		System.out.println("1)유저관리");
		System.out.println("2)게시글관리");
	}
}
