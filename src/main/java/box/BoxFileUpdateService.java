package box;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.web.multipart.MultipartFile;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem.Info;

import api.box.model.response.BoxFileInfo;

public class BoxFileUpdateService {
	public BoxFileInfo execute(MultipartFile file, String fileName, long targetFolderId) throws Exception {

		BoxAPIConnection api = new BoxAPIConnection("");

		// 検索元のFolder情報を取得
		BoxFolder folder = new BoxFolder(api, String.valueOf(targetFolderId));
		
		// 対象フォルダーから既存のファイル情報を取得
		String existFileId = this.isExistFile(fileName, folder);
		if (existFileId.isEmpty()) {
			return new BoxFileInfo(false, -1);
		}

		// MultiparFileをFileに型変換
		File convFile = this.convert(file);
		
		// Boxファイル更新
		try (FileInputStream fileInputStream = new FileInputStream(convFile)) {
			BoxFile boxFile = new BoxFile(api, existFileId);
			boxFile.uploadNewVersion(fileInputStream);
		} catch (Exception e) {
			throw e;
		} finally {
			convFile.delete();
		}

		Long fileId = Long.valueOf(existFileId);
		return new BoxFileInfo(true, fileId);
	}

	public File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
	
	private String isExistFile(String fileName, BoxFolder targetFolder) {

		Stream<Info> boxFileStream = StreamSupport.stream(targetFolder.spliterator(), false);
		return boxFileStream.filter(file -> file.getName().equals(fileName)).map(Info :: getID).findFirst().orElse("");

	}

}
