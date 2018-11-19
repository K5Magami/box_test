package box;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;

public class QuickStart {
	public static void main(String args[]) throws IOException {
		BoxAPIConnection api = new BoxAPIConnection("1To4qhpi3CBuSQe3S9B712cppT1Ty6ZN");
		BoxFolder rootFolder = BoxFolder.getRootFolder(api);
		
		for (BoxItem.Info itemInfo : rootFolder) {
		    System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
		}
		
		// ファイルの読み込みまで検証
		Path path = FileSystems.getDefault().getPath("resource", "sample.txt");
		BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		Stream<String> lines = br.lines();
		lines.forEach(line -> {System.out.println(line);});
		br.close();
		
		
	}
}
