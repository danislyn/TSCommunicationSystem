package managers.admin;

import beans.admin.UserBaseInfo;

import java.util.ArrayList;

import server.reflection.Call;
import client.login.Login;


public class UserManager extends LogManager {
	
	private ArrayList<UserBaseInfo> userList;
	
	
	/** 构造方法
	 * 	继承了LogManager
	 * @param username
	 */
	public UserManager(String username) 
	{
		super(username);
		update();
	}
	
	
	/** 更新方法，更新userList
	 * 
	 * @return true;
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "getUserList", 
							new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		userList = (ArrayList<UserBaseInfo>) Login.resultCall.getResult();
		
		return true;
	}
	
	
	/** 重置用户密码
	 * 
	 * @param username 用户名
	 * @param password 新密码
	 * @return boolean
	 */
	public boolean resetUserPassword(String username,String password)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "resetUserPassword", 
							new Class[]{String.class, String.class}, 
							new Object[]{username, password});
		Login.writeCall(call);
		Login.readCall();
		
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			int index = getIndexOfUser(username);
			if(index != -1)
			{
				UserBaseInfo userBaseInfo = userList.get(index);
				userBaseInfo.setPassword(password);
				userList.set(index, userBaseInfo);
			}
			
			saveLog("修改了" + username + "用户的密码为" + password);
			
			return true;	
		}		
		return false;
	}
	
	public boolean resetUserPassword(ArrayList<String> usernameList, String password)
	{
		return true;
	}
	
	
	/** 修改用户
	 *  前置条件：不改username
	 * @param userBaseInfo 用户基本信息
	 * @return boolean
	 */
	public boolean reviseUser(UserBaseInfo userBaseInfo)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "reviseUser", 
							new Class[]{UserBaseInfo.class}, new Object[]{userBaseInfo});
		Login.writeCall(call);
		Login.readCall();
		
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			int index = getIndexOfUser(userBaseInfo.getUsername());			
			if(index != -1)
			{
				userList.set(index, userBaseInfo);
			}
			
			saveLog("修改了" + username + "用户的基本信息");
			
			return true;
		}
		return false;
	}
	
	
	/** 添加新用户
	 * 
	 * @param userBaseInfo 用户基本信息
	 * @return boolean
	 */
	public boolean addUser(UserBaseInfo userBaseInfo)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "addUser", 
							new Class[]{UserBaseInfo.class}, new Object[]{userBaseInfo});
		Login.writeCall(call);
		Login.readCall();

		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			userList.add(userBaseInfo);
			
			saveLog("添加了用户" + userBaseInfo.getUsername());
			
			return true;
		}
		return false;
	}
	

	/** 添加用户（批量操作）
	 * 
	 * @param newUserList 用户基本信息列表
	 * @return boolean
	 */
	public boolean addUser(ArrayList<UserBaseInfo> newUserList)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "addUser", 
							new Class[]{ArrayList.class}, new Object[]{newUserList});
		Login.writeCall(call);
		Login.readCall();

		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			int num = 0;
			for(int i=0; i<newUserList.size(); i++)
			{
				UserBaseInfo user = newUserList.get(i);
				
				//如果这个用户名以前不存在
				if(getIndexOfUser(user.getUsername()) == -1)
				{
					userList.add(user);
					num++;
				}
			}
			
			//saveLog("添加了用户" + newUserList.get(0).getUsername() + " ~ " + newUserList.get(newUserList.size()-1).getUsername());
			saveLog("添加了用户" + num + "个新用户");
			
			return true;
		}
		return false;
	}
	
	
	/** 删除用户
	 *  前置条件：username不能为管理员(authority!=5)
	 * @param username 用户名
	 * @return boolean
	 */
	public boolean deleteUser(String username)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "deleteUser", 
							new Class[]{String.class}, new Object[]{username});
		Login.writeCall(call);
		Login.readCall();
		
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			int index = getIndexOfUser(username);
			userList.remove(index);
			
			saveLog("删除了用户" + username);
			
			return true;
		}	
		return false;
	}
	
	public boolean deleteUser(ArrayList<String>usernameList)
	{
		return true;
	}
	
	
	/** 获取所有用户列表
	 * （不需排序，就按数据库中的顺序）
	 * @return ArrayList<UserBaseInfo>
	 */
	public ArrayList<UserBaseInfo> showUserList()
	{
		return userList;
	}
	
	
	/** 获取指定年级的用户列表
	 * 
	 * @param grade 年级1-4表示大一-大四，0表示老师群体
	 * @return ArrayList<UserBaseInfo>
	 */
	public ArrayList<UserBaseInfo> searchByGrade(int grade)
	{
		ArrayList<UserBaseInfo> searchList = new ArrayList<UserBaseInfo>();
		
		for(int i=0; i<userList.size(); i++)
		{
			UserBaseInfo userBaseInfo = userList.get(i);
			
			if(userBaseInfo.getGrade() == grade)
				searchList.add(userBaseInfo);
		}
		
		return searchList;
	}
	
	
	/** 搜索指定username的人
	 *  注意：username不存在时返回null
	 * @param username 搜索的用户名
	 * @return UserBaseInfo
	 */
	public UserBaseInfo searchByUsername(String username)
	{
		int index = getIndexOfUser(username);
		
		if(index != -1)
			return userList.get(index);
		else 
			return null;
	}
	
	
/*	private void sortByUsername(){
		
	}*/
	
	private int getIndexOfUser(String username)
	{
		for(int i=0; i<userList.size(); i++)
		{
			if(userList.get(i).getUsername().equals(username))
				return i;
		}
		return -1;
	}
	
	
}
