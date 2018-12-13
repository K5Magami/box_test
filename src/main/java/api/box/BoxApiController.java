package api.box;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.box.model.request.BoxFolderSearchConditions;
import api.box.model.response.BoxFolderInfo;

@RestController
public class BoxApiController {
	
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
	* @param folderId
	* @param folderName
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
	
}
