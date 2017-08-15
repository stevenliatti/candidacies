package beans;

import java.util.HashMap;

public class Answer extends Bean {
	private Long id;
	private String name;
	private String title;
	private String content;
	private String hide;

	public Answer(Long id, String name, String title, String content, String hide) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.hide = hide;
		
		answerAsMap();
	}

	private void answerAsMap() {
		map = new HashMap<>();
		map.put(formatField(nameField), name);
		map.put(formatField(answerTitleField), title);
		map.put(formatField(contentField), content);
	}
	
	@Override
	public String toString() {
		return "Answer [id=" + id + ", name=" + name + ", title=" + title + ", content=" + content + ", hide=" + hide
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHide() {
		return hide;
	}

	public void setHide(String hide) {
		this.hide = hide;
	}
}
