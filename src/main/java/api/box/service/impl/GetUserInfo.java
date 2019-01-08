package api.box.service.impl;

import java.util.stream.StreamSupport;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxUser;

public class GetUserInfo {
	public static void main(String args[]) {

		BoxAPIConnection api = new BoxAPIConnection("");

		// Get Enterprise Users
		Iterable<BoxUser.Info> users = BoxUser.getAllEnterpriseUsers(api);
		StreamSupport.stream(users.spliterator(), false).forEach(System.out :: println);

		// insufficient permission 権限が十�?ではな�?と�?われ�?

	}


}
