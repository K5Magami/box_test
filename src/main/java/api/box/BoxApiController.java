package api.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import api.box.model.response.BoxFileInfo;
import api.box.model.response.BoxFolderInfo;
import api.box.service.BoxFolderInfoService;
import api.box.service.impl.BoxFileCreateService;
import api.box.service.impl.BoxFileUpdateService;
import api.box.service.impl.BoxFolderCreateService;
import api.box.service.impl.BoxFolderInfoServiceImpl;

@RestController
public class BoxApiController {
	
	private BoxFolderInfoService boxFolderInfoService;
	
	@Autowired
	public void setBoxFolderInfoService(BoxFolderInfoService boxFolderInfoService) {
		this.boxFolderInfoService = boxFolderInfoService;
	}
	
	/**
	* フォルダ情報取得
	* @param folderId
	* @param folderName
	 * @throws Exception 
	*/
	@RequestMapping(value = "/folder", method = RequestMethod.GET)
	public BoxFolderInfo getFolderInfoController(
			@RequestParam(value="folderId", defaultValue="") String folderId,
			@RequestParam(value="folderName", defaultValue="") String folderName 
	) throws Exception {
		
		// Boxフォルダ情報取得用サービスクラスを設定
		// TODO BoxFolderInfoServiceはDIする形にしたい
//		BoxFolderInfoServiceImpl service = new BoxFolderInfoServiceImpl();
		
		
//		return service.execute(Long.valueOf(folderId), folderName);
		return boxFolderInfoService.execute(Long.valueOf(folderId), folderName);
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
    )throws Exception  {
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
