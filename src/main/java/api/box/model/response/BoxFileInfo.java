package api.box.model.response;

public class BoxFileInfo {
	
	private final boolean isExists;
    private final long FileId;

    public BoxFileInfo(boolean isExists, long FileId) {
        this.isExists = isExists;
        this.FileId = FileId;
    }

    public boolean getIsExists() {
        return isExists;
    }

    public long getFileId() {
        return FileId;
    }

}
