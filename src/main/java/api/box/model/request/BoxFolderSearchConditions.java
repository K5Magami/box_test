package api.box.model.request;


public class BoxFolderSearchConditions {
	private String folderId;
	private String folderName;
	
	BoxFolderSearchConditions(String folderId, String folderName) {
		this.folderId = folderId;
		this.folderName = folderName;
		
	}
	
	public String getFolderId() {
		return folderId;
	}
	
	public String getFolderName() {
		return folderName;
	}
}
