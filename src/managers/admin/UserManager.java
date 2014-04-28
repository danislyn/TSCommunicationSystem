package managers.admin;

import beans.admin.UserBaseInfo;

import java.util.ArrayList;

import server.reflection.Call;
import client.login.Login;


public class UserManager extends LogManager {
	
	private ArrayList<UserBaseInfo> userList;
	
	
	/** ���췽��
	 * 	�̳���LogManager
	 * @param username
	 */
	public UserManager(String username) 
	{
		super(username);
		update();
	}
	
	
	/** ���·���������userList
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
	
	
	/** �����û�����
	 * 
	 * @param username �û���
	 * @param password ������
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
			
			saveLog("�޸���" + username + "�û�������Ϊ" + password);
			
			return true;	
		}		
		return false;
	}
	
	public boolean resetUserPassword(ArrayList<String> usernameList, String password)
	{
		return true;
	}
	
	
	/** �޸��û�
	 *  ǰ������������username
	 * @param userBaseInfo �û�������Ϣ
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
			
			saveLog("�޸���" + username + "�û��Ļ�����Ϣ");
			
			return true;
		}
		return false;
	}
	
	
	/** ������û�
	 * 
	 * @param userBaseInfo �û�������Ϣ
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
			
			saveLog("������û�" + userBaseInfo.getUsername());
			
			return true;
		}
		return false;
	}
	

	/** ����û�������������
	 * 
	 * @param newUserList �û�������Ϣ�б�
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
				
				//�������û�����ǰ������
				if(getIndexOfUser(user.getUsername()) == -1)
				{
					userList.add(user);
					num++;
				}
			}
			
			//saveLog("������û�" + newUserList.get(0).getUsername() + " ~ " + newUserList.get(newUserList.size()-1).getUsername());
			saveLog("������û�" + num + "�����û�");
			
			return true;
		}
		return false;
	}
	
	
	/** ɾ���û�
	 *  ǰ��������username����Ϊ����Ա(authority!=5)
	 * @param username �û���
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
			
			saveLog("ɾ�����û�" + username);
			
			return true;
		}	
		return false;
	}
	
	public boolean deleteUser(ArrayList<String>usernameList)
	{
		return true;
	}
	
	
	/** ��ȡ�����û��б�
	 * ���������򣬾Ͱ����ݿ��е�˳��
	 * @return ArrayList<UserBaseInfo>
	 */
	public ArrayList<UserBaseInfo> showUserList()
	{
		return userList;
	}
	
	
	/** ��ȡָ���꼶���û��б�
	 * 
	 * @param grade �꼶1-4��ʾ��һ-���ģ�0��ʾ��ʦȺ��
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
	
	
	/** ����ָ��username����
	 *  ע�⣺username������ʱ����null
	 * @param username �������û���
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
