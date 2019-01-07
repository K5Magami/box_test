package api.box.service;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;

import api.box.model.response.BoxFolderInfo;

public class BoxFolderCreateService {
	public BoxFolderInfo execute(long targetFolderId, String folderName) {
		BoxAPIConnection api = new BoxAPIConnection("");
		
		// 検索元のFolder情報を取得
		BoxFolder folder = new BoxFolder(api, String.valueOf(targetFolderId));
		
		// 対象フォルダーが存在しなければフォルダー作成してID取得する
		BoxFolder.Info childFolerInfo = folder.createFolder(folderName);
		
		long folderId = Long.parseLong(childFolerInfo.getID());
		
		return new BoxFolderInfo(true, folderId);
	}
}
