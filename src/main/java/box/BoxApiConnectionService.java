package box;

import java.io.FileReader;
import java.io.Reader;

import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;

public class BoxApiConnectionService {
	IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(10);
	
	public BoxDeveloperEditionAPIConnection getConnection() throws Exception {
		
		BoxDeveloperEditionAPIConnection api = null;
		
		try {
			Reader reader = new FileReader("resource/config.json");
	        BoxConfig boxConfig = BoxConfig.readFrom(reader);
	        api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig, accessTokenCache);
		} catch (Exception e) {
			throw e;
		}
		
		return api;
	}

}
