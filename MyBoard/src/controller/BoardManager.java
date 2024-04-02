package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dto.Board;
import main.MyBoard;
import unit.BoardBlind;
import unit.BoardNonBlind;

public class BoardManager {
	// static ArrayList<Board>
	public static ArrayList<BoardNonBlind> boardList = new ArrayList<>();
	private SimpleDateFormat sdf;

	private BoardManager() {
		sdf = new SimpleDateFormat("yy년 mm월 dd일 kk:mm");
	}

	private static BoardManager instance = new BoardManager();

	public static BoardManager getInsatnce() {
		return instance;
	}

	/* C(insert) */
	public void addBoard(String id, String title, String content) {
		String date = sdf.format(System.currentTimeMillis());
		BoardNonBlind board = new BoardNonBlind(id, title, content, date);
		boardList.add(board);
	};

	public void addBoard(String title, String content) {
		String adj[] = { "배고픈", "졸린", "화난", "잘생긴" };
		String names[] = { "라이언", "어피치", "제이지", "춘식이" };
		String name = "";
		String date = sdf.format(System.currentTimeMillis());

		int rNum = (int) (Math.random() * 4);
		name += adj[rNum];
		rNum = (int) (Math.random() * 4);
		name += " " + names[rNum];
		BoardBlind board = new BoardBlind(name, title, content, date);
		MyBoard.unknownList.add(board);
	}

	/* U(update) */
	public void updateBoard(Board board, String content) {
		boardList.remove(board);
		board.setContent(content);
		String date = sdf.format(System.currentTimeMillis());
		board.setDate(date);
		BoardNonBlind result = (BoardNonBlind) board;
		boardList.add(result);
	}

}
