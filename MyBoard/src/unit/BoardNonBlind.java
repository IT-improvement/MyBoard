package unit;

import dto.Board;
import model.Updateable;

public class BoardNonBlind extends Board implements Updateable {

	public BoardNonBlind(String content, String title) {
		super(content, title);
	}

}
