package api.box.service;

import api.box.model.response.BoxFolderInfo;

public interface BoxFolderInfoService {
	
	BoxFolderInfo execute(long targetFolderId, String targetFolderName) throws Exception;

}
