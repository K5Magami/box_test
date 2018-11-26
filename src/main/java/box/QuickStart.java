package box;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;

public class QuickStart {
	public static void main(String args[]) throws IOException {
		// 有効期間が限られているDeveloper token
		BoxAPIConnection api = new BoxAPIConnection("");
		BoxFolder rootFolder = BoxFolder.getRootFolder(api);

		for (BoxItem.Info itemInfo : rootFolder) {
		    System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
		}
//
//		// ファイルの読み込みまで検証
//		Path path = FileSystems.getDefault().getPath("resource", "sample.txt");
//		BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
//		Stream<String> lines = br.lines();
//		lines.forEach(line -> {System.out.println(line);});
//		br.close();


		 //It is a best practice to use an access token cache to prevent unneeded requests to Box for access tokens.
        //For production applications it is recommended to use a distributed cache like Memcached or Redis, and to
        //implement IAccessTokenCache to store and retrieve access tokens appropriately for your environment.

		// アクセストークンのキャッシュ設定（多分使い終わったらdeleteするのが吉）
//		IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(1);
//
//        Reader reader = new FileReader("resource/config.json");
//        BoxConfig boxConfig = BoxConfig.readFrom(reader);
//        System.out.println(boxConfig.getClientId());
//        System.out.println(boxConfig.getClientSecret());
//        System.out.println(boxConfig.getEnterpriseId());
//        System.out.println(boxConfig.getJWTEncryptionPreferences());
//
//        BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(
//                boxConfig, accessTokenCache);
//
//		BoxFolder rootFolder = BoxFolder.getRootFolder(api);
//
//		for (BoxItem.Info itemInfo : rootFolder) {
//		    System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
//		}
//
//		api.revokeToken();

	}
}
