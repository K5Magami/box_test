package api.box.service.impl;

import java.util.Optional;
import java.util.stream.StreamSupport;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem.Info;

public class GetFolderPath {
	public static void main(String[] args) {
		BoxAPIConnection api = new BoxAPIConnection("");
		
		// 検索元のFolder情報を取得
		BoxFolder folder = new BoxFolder(api, "58828304698");
		// folderかfileの判定はどうすればいい→getType()使う
		StreamSupport.stream(folder.spliterator(), false).forEach(info -> System.out.format("[%s] %s : %s\n", info.getID(), info.getName(), info.getType()));
		
		// 指定したフォルダー名と一致するフォルダーを取得する
		String targetName = "フォルダー２";
		String targetId = StreamSupport.stream(folder.spliterator(), false).filter(info -> info.getName().equals(targetName) && info.getType().equals("folder"))
										.map(Info :: getID).findFirst().orElse("");
		
		
		if (!targetId.isEmpty()) {
			return ;
		}
		
		// 対象フォルダーが存在しなければフォルダー作成してID取得する
		BoxFolder.Info childFolerInfo = folder.createFolder(targetName);
		System.out.format("%s : %s \n",childFolerInfo.getID(),childFolerInfo.getName());
		
		
//		BoxFile file = new BoxFile(api, "353376558332");
//		System.out.format("%s:%s¥n", file.getID(),file.getInfo("size"));
		
		
		
		
	}

}
