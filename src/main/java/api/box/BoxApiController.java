package api.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import api.box.model.request.BoxFolderSearchConditions;
import api.box.model.response.BoxFileInfo;
import api.box.model.response.BoxFolderInfo;
import api.box.storage.StorageService;

@RestController
public class BoxApiController {
	
	private final StorageService storageService;
	
	@Autowired
	public BoxApiController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	/**
	* フォルダ情報取得
	* @param folderId
	* @param folderName
	* @author Captain America
	* 
	*/
	@RequestMapping(value = "/folder", method = RequestMethod.GET)
	public BoxFolderInfo getFolderInfoController(
			@RequestParam(value="folderId", defaultValue="") String folderId,
			@RequestParam(value="folderName", defaultValue="") String folderName 
	) {
		// TODO Box連携させるサービスクラスを実装させる
		boolean isExists = !folderId.isEmpty() && !folderName.isEmpty() ? true : false;
		
		return new BoxFolderInfo(isExists, 100000);
	}
	
	/**
	* フォルダ作成
	* @param BoxFolderSearchConditions
	* @author Captain America
	* 
	*/
	@RequestMapping(value = "/folder", method = RequestMethod.POST)
	public BoxFolderInfo mkFolderController(
			@RequestBody BoxFolderSearchConditions searchConditions
    ) {
		// TODO Box連携させるサービスクラスを実装させる
		boolean isExists = !searchConditions.getFolderId().isEmpty() && !searchConditions.getFolderName().isEmpty() ? true : false;
		
		return new BoxFolderInfo(isExists, 100000);
	}
	
	/**
	* ファイル情報取得
	* @param folderId
	* @param folderName
	* @author Captain America
	* 
	*/
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public BoxFileInfo getFileInfoController(
			@RequestParam(value="folderId", defaultValue="") String folderId,
			@RequestParam(value="folderName", defaultValue="") String fileName 
	) {
		// TODO Box連携させるサービスクラスを実装させる
		boolean isExists = !folderId.isEmpty() && !fileName.isEmpty() ? true : false;
		
		return new BoxFileInfo(isExists, 222222);
		
	}
	
	/**
	* ファイル新規登録
	* @param folderId
	* @param folderName
	* @author Captain America
	* 
	*/
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public BoxFileInfo uploadFileController(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
		
		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
		
		return new BoxFileInfo(true, 10000);

	}
	
//	@ExceptionHandler(StorageFileNotFoundException.class)
//    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//        return ResponseEntity.notFound().build();
//    }
	
}
