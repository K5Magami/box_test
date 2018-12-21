package box;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;

import api.box.model.response.BoxFileInfo;


public class BoxFileCreateService {
	public BoxFileInfo execute(MultipartFile file, String fileName, long targetFolderId) throws Exception {
		
		BoxAPIConnection api = new BoxAPIConnection("");
		
		// 検索元のFolder情報を取得
		BoxFolder folder = new BoxFolder(api, String.valueOf(targetFolderId));
		
		// MultiparFileの名前変更
		BoxFile.Info newFileInfo = null;
		File convFile = this.convert(file);
		try(FileInputStream fileInputStream = new FileInputStream(convFile)) {
			newFileInfo = folder.uploadFile(fileInputStream, fileName);
		} catch (Exception e) {
			throw e;
		} finally {
			convFile.delete();
		}
		
		Long fileId = Long.valueOf(newFileInfo.getID());
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

}


