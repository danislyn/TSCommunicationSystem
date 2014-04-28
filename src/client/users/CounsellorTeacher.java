package client.users;


public class CounsellorTeacher extends Teacher {

	//private ScoreManager soreManager;
	
	//private LocalSaveManager localSaveManager;


	/** 
	 * 构造方法
	 * @param username 用户名，一旦传入，不可修改
	 * @param password 密码
	 * @param authority 权限，一旦传入，不可修改
	 * @param location 地理位置
	 */
	public CounsellorTeacher(String username, String password, int authority, int location) 
	{
		super(username, password, authority, location);
		
	}
	
	
}
