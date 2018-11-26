package box;

import java.util.stream.StreamSupport;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxUser;

public class GetUserInfo {
	public static void main(String args[]) {
		
		BoxAPIConnection api = new BoxAPIConnection("8nxvN1WfDSv5OLsFxex3oEnw9jx0JdY7");
		
		// Get Enterprise Users
		Iterable<BoxUser.Info> users = BoxUser.getAllEnterpriseUsers(api);
		StreamSupport.stream(users.spliterator(), false).forEach(System.out :: println);
		
		// insufficient permission 権限が十分ではないと言われる
		
	}
	

}
