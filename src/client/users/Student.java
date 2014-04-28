package client.users;

public class Student extends Person {

	
	/** 
	 * 构造方法
	 * @param username 用户名，一旦传入，不可修改
	 * @param password 密码
	 * @param authority 权限，一旦传入，不可修改
	 * @param location 地理位置
	 */
	public Student(String username, String password, int authority, int location)
	{
		super(username, password, authority, location);
	}
	
	
	
}
