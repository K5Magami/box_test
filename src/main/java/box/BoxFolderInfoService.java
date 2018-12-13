package box;

import java.util.stream.StreamSupport;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem.Info;

import api.box.model.response.BoxFolderInfo;

public class BoxFolderInfoService {
	public BoxFolderInfo execute(long targetFolderId, String targetFolderName) {
		BoxAPIConnection api = new BoxAPIConnection("");
		
		// 検索元のFolder情報を取得
		BoxFolder folder = new BoxFolder(api, String.valueOf(targetFolderId));

		// 指定したフォルダー名と一致するフォルダーを取得する
//		String targetName = "フォルダー２";
		String searchResult = StreamSupport.stream(folder.spliterator(), false)
				.filter(info -> info.getName().equals(targetFolderName) && info.getType().equals("folder")).map(Info::getID)
				.findFirst().orElse("");

		// Boxフォルダーの有無
		boolean isExists = !searchResult.isEmpty();
		// BoxフォルダーのIDをint型に変換する.(存在しない場合は-1)
		long folderId = isExists ? Long.valueOf(searchResult) : -1;
		
		return new BoxFolderInfo(isExists, folderId);
	}

}
