package api.box.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.web.multipart.MultipartFile;

/**
* ファイルアップロードのためのinterface
* https://spring.io/guides/gs/uploading-files/
* 
*/
public interface StorageService {
	void init();
	
	void store(MultipartFile file);
	
	Stream<Path> loadAll();
	
	Resource loadAsResource(String filename);
	
	void deleteAll();

}
