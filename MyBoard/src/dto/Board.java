package dto;

import java.text.SimpleDateFormat;

public abstract class Board {
	private String id;
	private String content;
	private String title;
	private String date;

	public Board(String id, String title, String content, String date) {
		this.id = id;
		this.content = content;
		this.title = title;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format("제목: %s\n" + "글쓴이: %s\n" + "작성시간: %s\n" + "내용\n" + "%s", title, id, date, content);
	}
}
