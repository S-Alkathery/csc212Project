
public class ContentList { // this class is to have the text without removing the stop words
	public String content;
	public int id;

	public ContentList(String content, int id) {
		this.content = content;
		this.id = id;
	}

	public void printContent() {
		System.out.println("Document " + id + ": " + content);
	}

}
