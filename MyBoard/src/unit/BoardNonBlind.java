package unit;

import dto.Board;
import model.Updateable;

public class BoardNonBlind extends Board implements Updateable {

	public BoardNonBlind(String id, String title, String content, String date){
		super(id, title, content, date);
	}

	@Override
	public void update(String title, String content) {

	}

}
