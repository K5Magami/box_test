package api.box.service;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;

import api.box.model.response.BoxFolderInfo;

public class BoxFolderCreateService {
	public BoxFolderInfo execute(long targetFolderId, String folderName) throws Exception {

		// アクセストークンのキャッシュ設定（多分使い終わったらdeleteするのが吉）
		IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(10);
		BoxDeveloperEditionAPIConnection api = null;

		long folderId = -1;
		try {

			BoxApiConnectionService boxApiConnectionService = new BoxApiConnectionService();
			api = boxApiConnectionService.getConnection();
			api.asUser("3560300031");

			// 検索元のFolder情報を取得
			BoxFolder folder = new BoxFolder(api, String.valueOf(targetFolderId));

			// 対象フォルダーが存在しなければフォルダー作成してID取得する
			BoxFolder.Info childFolerInfo = folder.createFolder(folderName);

			folderId = Long.parseLong(childFolerInfo.getID());
			
		} catch (Exception e) {
			throw e;
		} finally {
			if(api != null) {
				api.revokeToken();
			}
		}
		
		// folderIdが初期値のママの場合はfalseをリターン
		if (folderId == -1) {
			return new BoxFolderInfo(false, -1);
		}

		return new BoxFolderInfo(true, folderId);
	}
}
