package api.box.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;

import api.box.model.response.BoxFileInfo;

public class BoxFileCreateService {
	public BoxFileInfo execute(MultipartFile file, String fileName, long targetFolderId) throws Exception {

		BoxDeveloperEditionAPIConnection api = null;
		long fileId = -1;
		FileInputStream fileInputStream = null;
		File convFile = null;

		try {
			
			BoxApiConnectionService boxApiConnectionService = new BoxApiConnectionService();
	        api = boxApiConnectionService.getConnection();
	        api.asUser("3560300031");
			
			// 検索元のFolder情報を取得
			BoxFolder folder = new BoxFolder(api, String.valueOf(targetFolderId));

			// MultiparFileの名前変更
			BoxFile.Info newFileInfo = null;
			convFile = this.convert(file);
			
			
			fileInputStream = new FileInputStream(convFile);
			newFileInfo = folder.uploadFile(fileInputStream, fileName);
			fileId = Long.valueOf(newFileInfo.getID());

		} catch (Exception e) {
			throw e;
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
			if (convFile != null) {
				convFile.delete();
			}
		}

		// fileIdが初期値の場合はfalseをリターンする
		if (fileId == -1) {
			return new BoxFileInfo(false, -1);
		}

		return new BoxFileInfo(true, fileId);
	}

	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

}
