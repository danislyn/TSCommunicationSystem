package client.users;

import java.util.ArrayList;

import beans.admin.Log;
import beans.admin.UserBaseInfo;
import beans.announcement.Announcement;
import beans.post.Comment;
import beans.post.Post;
import managers.admin.LogManager;
import managers.admin.SystemManager;
import managers.admin.UserManager;

public class AdminTeacher extends Teacher {

	//private LabManager labManager;
	
	private SystemManager systemManager;
	
	private UserManager userManager;
	
	private LogManager logManager;  //ֻ�û���log


	/** 
	 * ���췽��
	 * @param username �û�����һ�����룬�����޸�
	 * @param password ����
	 * @param authority Ȩ�ޣ�һ�����룬�����޸�
	 * @param location ����λ��
	 */
	public AdminTeacher(String username, String password, int authority, int location) 
	{
		super(username, password, authority, location);
		
		userManager = new UserManager(username);
		systemManager = new SystemManager(username);
		logManager = new LogManager(username);
	}
	
	
	/** ����UserManager
	 * 
	 */
	public void updateUserManager()
	{
		userManager.update();
	}
	
	/** ����SystemManager
	 * 
	 */
	public void updateSystemManager()
	{
		systemManager.update();
	}
	
	
	//=================================================================
	/** �����û�����
	 * 
	 * @param username �û���
	 * @param password ������
	 * @return boolean
	 */
	public boolean resetUserPassword(String username, String password)
	{
		return userManager.resetUserPassword(username, password);
	}
	
	
	/** �޸��û�
	 *  ǰ������������username
	 * @param userBaseInfo �û�������Ϣ
	 * @return boolean
	 */
	public boolean reviseUser(UserBaseInfo userBaseInfo)
	{
		return userManager.reviseUser(userBaseInfo);
	}
	
	
	/** ������û�
	 * 
	 * @param userBaseInfo �û�������Ϣ
	 * @return boolean
	 */
	public boolean addUser(UserBaseInfo userBaseInfo)
	{
		return userManager.addUser(userBaseInfo);
	}
	
	
	/** ����û�������������
	 * 
	 * @param userList �û�������Ϣ�б�
	 * @return boolean
	 */
	public boolean addUser(ArrayList<UserBaseInfo> userList)
	{
		return userManager.addUser(userList);
	}
	
	
	/** ɾ���û�
	 * 
	 * @param userBaseInfo Ҫɾ���û�
	 * @return boolean
	 */
	public boolean deleteUser(UserBaseInfo userBaseInfo)
	{
		//���Ϊ����Ա������ɾ
		if(userBaseInfo.getAuthority() == 5)
			return false;
		
		return userManager.deleteUser(userBaseInfo.getUsername());
	}
	
	
	/** ��ȡ�����û��б�
	 * 
	 * @return ArrayList<UserBaseInfo>
	 */
	public ArrayList<UserBaseInfo> getAllUserList()
	{
		return userManager.showUserList();
	}
	
	
	/** ��ȡָ���꼶���û��б�
	 * 
	 * @param grade �꼶1-4��ʾ��һ-���ģ�0��ʾ��ʦȺ��
	 * @return ArrayList<UserBaseInfo>
	 */
	public ArrayList<UserBaseInfo> getUserListByGrade(int grade)
	{
		return userManager.searchByGrade(grade);
	}
	
	
	/** ��ȡָ��username����
	 *  ע�⣺username������ʱ����null
	 * @param username �������û���
	 * @return UserBaseInfo
	 */
	public UserBaseInfo getOneUser(String username)
	{
		return userManager.searchByUsername(username);
	}
	
	
	//=================================================================
	/** �������ö�
	 * 
	 * @param tag �����
	 * @param post ����
	 * @return boolean
	 */
	public boolean makePostTop(int tag, Post post)
	{
		return systemManager.makePostTop(tag, post);
	}
	
	
	/** ȡ�������ö�
	 * 
	 * @param tag �����
	 * @param post ����
	 * @return boolean
	 */
	public boolean cancelPostTop(int tag, Post post)
	{
		return systemManager.cancelPostTop(tag, post);
	}
	
	
	/** ɾ������
	 * 
	 * @param tag �����
	 * @param post ����
	 * @return boolean
	 */
	public boolean deletePost(int tag, Post post)
	{
		return systemManager.deletePost(tag, post);
	}
	
	
	/** ɾ��ĳ���������ĳ������
	 * 
	 * @param tag �����
	 * @param post ����
	 * @param comment ����
	 * @return boolean
	 */
	public boolean deleteComment(int tag, Post post, Comment comment)
	{
		return systemManager.deleteComment(tag, post, comment);
	}
	
	
	/** ɾ������
	 * 
	 * @param announcement ����
	 * @return boolean
	 */
	public boolean deleteAnnouncement(Announcement announcement)
	{
		return systemManager.deleteAnnouncement(announcement);
	}
	
	
	/** ��ȡ���й����б���������
	 * 
	 * @return ArrayList<Announcement>
	 */
	public ArrayList<Announcement> getAllAnnouncementList()
	{
		return systemManager.showAnnouncementList();
	}
	
	
	//================================================================
	/** ��ȡ������־�б��Ѹ��£�������
	 * 
	 * @return ArrayList<Log>
	 */
	public ArrayList<Log> getLogList()
	{
		return logManager.showLogList();
	}
	
	
}
