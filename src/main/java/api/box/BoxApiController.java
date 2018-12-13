package api.box;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.box.model.BoxFolderInfo;

@RestController
public class BoxApiController {
	
	@RequestMapping(value = "/folder", method = RequestMethod.GET)
	public BoxFolderInfo getFolderInfo(
			@RequestParam(value="folderId", defaultValue="") String folderId,
			@RequestParam(value="folderName", defaultValue="") String folderName 
	) {
		// TODO Box連携させるサービスクラスを実装させる
		boolean isExists = !folderId.isEmpty() && !folderName.isEmpty() ? true : false;
		
		return new BoxFolderInfo(isExists, 100000);
	}
}
