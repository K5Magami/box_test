package api.box.model;

public class BoxFolderInfo {
	private final boolean isExists;
    private final long folderId;

    public BoxFolderInfo(boolean isExists, long folderId) {
        this.isExists = isExists;
        this.folderId = folderId;
    }

    public boolean getIsExists() {
        return isExists;
    }

    public long getFolderId() {
        return folderId;
    }
}
