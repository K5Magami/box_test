package box;

import java.io.FileReader;
import java.io.Reader;
import java.util.stream.StreamSupport;

import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;
import com.box.sdk.BoxItem.Info;

import api.box.model.response.BoxFolderInfo;

public class BoxFolderInfoService {
	public BoxFolderInfo execute(long targetFolderId, String targetFolderName) {
		
		
		// アクセストークンのキャッシュ設定（多分使い終わったらdeleteするのが吉）
		IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(10);
		
		Reader reader = null;
		BoxConfig boxConfig = null;
		
		try {
			reader = new FileReader("resource/config.json");
	        boxConfig = BoxConfig.readFrom(reader);
		} catch (Exception e) {
			// TODO: handle exception
			
		}

        System.out.println(boxConfig.getClientId());
        System.out.println(boxConfig.getClientSecret());
        System.out.println(boxConfig.getEnterpriseId());
        System.out.println(boxConfig.getJWTEncryptionPreferences());

        BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(
                boxConfig, accessTokenCache);
        api.asUser("3560300031");

		// 検索元のFolder情報を取得
		BoxFolder folder = new BoxFolder(api, String.valueOf(targetFolderId));

		// 指定したフォルダー名と一致するフォルダーを取得する
		String searchResult = StreamSupport.stream(folder.spliterator(), false)
				.filter(info -> info.getName().equals(targetFolderName) && info.getType().equals("folder")).map(Info::getID)
				.findFirst().orElse("");

		// Boxフォルダーの有無
		boolean isExists = !searchResult.isEmpty();
		// BoxフォルダーのIDをint型に変換する.(存在しない場合は-1)
		long folderId = isExists ? Long.valueOf(searchResult) : -1;

		api.revokeToken();
		return new BoxFolderInfo(isExists, folderId);
	}

}
