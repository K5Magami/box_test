package api.box.model;

public class BoxFileInfo {
	
	private final long id;
    private final String content;

    public BoxFileInfo(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
