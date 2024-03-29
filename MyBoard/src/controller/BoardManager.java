package controller;

import java.util.ArrayList;

import dto.Board;

public class BoardManager {
	// static ArrayList<Board>
	public static ArrayList<Board> boardList = new ArrayList<>();

	private BoardManager() {

	}

	private static BoardManager instance = new BoardManager();

	public static BoardManager getInsatnce() {
		return instance;
	}
}
