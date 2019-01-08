package api.box.service.impl;

import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem.Info;

import api.box.model.response.BoxFolderInfo;
import api.box.service.BoxFolderInfoService;

@Service
public class BoxFolderInfoServiceImpl implements BoxFolderInfoService{
	@Override
	public BoxFolderInfo execute(long targetFolderId, String targetFolderName) throws Exception {
		
		BoxDeveloperEditionAPIConnection api = null;
		String searchResult = "";
		
		// TODO apiのオブジェクトに関してはDIするように設定したい
		try {
			BoxApiConnectionService boxApiConnectionService = new BoxApiConnectionService();
	        api = boxApiConnectionService.getConnection();
	        api.asUser("3560300031");
	        
	        // 検索元のFolder情報を取得
	        BoxFolder folder = new BoxFolder(api, String.valueOf(targetFolderId));
	        
	        // 指定したフォルダー名と一致するフォルダーを取得する
	        searchResult = StreamSupport.stream(folder.spliterator(), false)
	        		.filter(info -> info.getName().equals(targetFolderName) && info.getType().equals("folder")).map(Info::getID)
	        		.findFirst().orElse("");
		} catch (Exception e) {
			throw e;
		} finally {
			if(api != null) {
				api.revokeToken();
			}
		}

		// Boxフォルダーの有無
		boolean isExists = !searchResult.isEmpty();
		// BoxフォルダーのIDをint型に変換する.(存在しない場合は-1)
		long folderId = isExists ? Long.valueOf(searchResult) : -1;

		return new BoxFolderInfo(isExists, folderId);
	}

}
