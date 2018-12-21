package api.box;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import api.box.model.response.BoxFileInfo;
import api.box.model.response.BoxFolderInfo;
import box.BoxFileCreateService;
import box.BoxFileUpdateService;
import box.BoxFolderCreateService;
import box.BoxFolderInfoService;

@RestController
public class BoxApiController {
	
	/**
	* フォルダ情報取得
	* @param folderId
	* @param folderName
	*/
	@RequestMapping(value = "/folder", method = RequestMethod.GET)
	public BoxFolderInfo getFolderInfoController(
			@RequestParam(value="folderId", defaultValue="") String folderId,
			@RequestParam(value="folderName", defaultValue="") String folderName 
	) {
		
		// Boxフォルダ情報取得用サービスクラスを設定
		// TODO BoxFolderInfoServiceはDIする形にしたい
		BoxFolderInfoService service = new BoxFolderInfoService();
		
		return service.execute(Long.valueOf(folderId), folderName);
	}
	
	/**
	* フォルダ作成
	* @param folderId
	* @param folderName
	*/
	@RequestMapping(value = "/folder", method = RequestMethod.POST)
	public BoxFolderInfo mkFolderController(
			@RequestParam(value="folderId", defaultValue="") String folderId,
			@RequestParam(value="folderName", defaultValue="") String folderName 
    ) {
		// Boxフォルダ情報作成用サービスクラスのインスタンス作成
		BoxFolderCreateService service = new BoxFolderCreateService();
		
		return service.execute(Long.valueOf(folderId), folderName);
	}
	
	/**
	* 新規ファイル作成
	* @param file
	* @param fileName
	* @param folderID
	 * @throws Exception 
	*/
	@RequestMapping(value = "/file", method = RequestMethod.POST, consumes = "multipart/form-data")
	public BoxFileInfo createFileController(
			@RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName,
            @RequestParam("folderId") long folderId
	) throws Exception {

		BoxFileCreateService service = new BoxFileCreateService();
		
		return service.execute(file, fileName, folderId);
	}
	
	/**
	* ファイル更新
	* @param file
	* @param fileName
	* @param folderID
	 * @throws Exception 
	*/
	@RequestMapping(value = "/file", method = RequestMethod.PUT, consumes = "multipart/form-data")
	public BoxFileInfo updateFileController(
			@RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName,
            @RequestParam("folderId") long folderId            
	) throws Exception {
		
		BoxFileUpdateService service = new BoxFileUpdateService();
		
		return service.execute(file, fileName, folderId);
		
	}
	
	
	
}
