package api.box.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxItem.Info;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;

public class UploadFile {
	public static void main(String args[]) throws IOException{

		BoxAPIConnection api = new BoxAPIConnection("");
		BoxFolder rootFolder = BoxFolder.getRootFolder(api);

		for (BoxItem.Info itemInfo : rootFolder) {
			System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
		}


		// Box内のフォルダの指定
		BoxFolder localFolder = new BoxFolder(api, "58828304698");
		for(BoxItem.Info itemInfo : localFolder) {
			System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
		}

		// Box内の対象フォルダにファイルが存在するかを判定
		String existFileId = isExistFile("sample.txt" , localFolder);
		System.out.println(existFileId);

		File file =	new File("resource/sample.txt");

		try(FileInputStream fileInputStream = new FileInputStream(file)){

			// Boxに対象フォルダが存在するならば更新して、なければ新規登録する
			if(existFileId.isEmpty()) {
				BoxFile.Info newFileInfo = localFolder.uploadFile(fileInputStream, "sample.txt");
			}else {
				BoxFile boxFile = new BoxFile(api, existFileId);
				boxFile.uploadNewVersion(fileInputStream);

			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
//			api.revokeToken();
		}


	}


	private static String isExistFile(String fileName, BoxFolder targetFolder) {

		Stream<Info> boxFileStream = StreamSupport.stream(targetFolder.spliterator(), false);
		return boxFileStream.filter(file -> file.getName().equals(fileName)).map(Info :: getID).findFirst().orElse("");

	}



}
