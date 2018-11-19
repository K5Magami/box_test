package box;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;

public class QuickStart {
	public static void main(String args[]) {
		System.out.println("hogehoge");
		BoxAPIConnection api = new BoxAPIConnection("1To4qhpi3CBuSQe3S9B712cppT1Ty6ZN");
		BoxFolder rootFolder = BoxFolder.getRootFolder(api);
		
		for (BoxItem.Info itemInfo : rootFolder) {
		    System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
		}
	}
}
