package dto;

import java.text.SimpleDateFormat;

public abstract class Board {
	private String id;
	private String content;
	private String title;
	private String date;
	private SimpleDateFormat sdf;

	public Board(String id, String content, String title) {
		sdf = new SimpleDateFormat("yy년 mm월 dd일 kk:mm");
		this.id = id;
		this.content = content;
		this.title = title;
		this.date = sdf.format(System.currentTimeMillis());
	}

	public Board(String content, String title) {
		sdf = new SimpleDateFormat("yy년 mm월 dd일 kk:mm");
		this.content = content;
		this.title = title;
		this.date = sdf.format(System.currentTimeMillis());
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

}
