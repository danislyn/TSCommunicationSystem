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
	
	private LogManager logManager;  //只用户看log


	/** 
	 * 构造方法
	 * @param username 用户名，一旦传入，不可修改
	 * @param password 密码
	 * @param authority 权限，一旦传入，不可修改
	 * @param location 地理位置
	 */
	public AdminTeacher(String username, String password, int authority, int location) 
	{
		super(username, password, authority, location);
		
		userManager = new UserManager(username);
		systemManager = new SystemManager(username);
		logManager = new LogManager(username);
	}
	
	
	/** 更新UserManager
	 * 
	 */
	public void updateUserManager()
	{
		userManager.update();
	}
	
	/** 更新SystemManager
	 * 
	 */
	public void updateSystemManager()
	{
		systemManager.update();
	}
	
	
	//=================================================================
	/** 重置用户密码
	 * 
	 * @param username 用户名
	 * @param password 新密码
	 * @return boolean
	 */
	public boolean resetUserPassword(String username, String password)
	{
		return userManager.resetUserPassword(username, password);
	}
	
	
	/** 修改用户
	 *  前置条件：不改username
	 * @param userBaseInfo 用户基本信息
	 * @return boolean
	 */
	public boolean reviseUser(UserBaseInfo userBaseInfo)
	{
		return userManager.reviseUser(userBaseInfo);
	}
	
	
	/** 添加新用户
	 * 
	 * @param userBaseInfo 用户基本信息
	 * @return boolean
	 */
	public boolean addUser(UserBaseInfo userBaseInfo)
	{
		return userManager.addUser(userBaseInfo);
	}
	
	
	/** 添加用户（批量操作）
	 * 
	 * @param userList 用户基本信息列表
	 * @return boolean
	 */
	public boolean addUser(ArrayList<UserBaseInfo> userList)
	{
		return userManager.addUser(userList);
	}
	
	
	/** 删除用户
	 * 
	 * @param userBaseInfo 要删的用户
	 * @return boolean
	 */
	public boolean deleteUser(UserBaseInfo userBaseInfo)
	{
		//如果为管理员，不能删
		if(userBaseInfo.getAuthority() == 5)
			return false;
		
		return userManager.deleteUser(userBaseInfo.getUsername());
	}
	
	
	/** 获取所有用户列表
	 * 
	 * @return ArrayList<UserBaseInfo>
	 */
	public ArrayList<UserBaseInfo> getAllUserList()
	{
		return userManager.showUserList();
	}
	
	
	/** 获取指定年级的用户列表
	 * 
	 * @param grade 年级1-4表示大一-大四，0表示老师群体
	 * @return ArrayList<UserBaseInfo>
	 */
	public ArrayList<UserBaseInfo> getUserListByGrade(int grade)
	{
		return userManager.searchByGrade(grade);
	}
	
	
	/** 获取指定username的人
	 *  注意：username不存在时返回null
	 * @param username 搜索的用户名
	 * @return UserBaseInfo
	 */
	public UserBaseInfo getOneUser(String username)
	{
		return userManager.searchByUsername(username);
	}
	
	
	//=================================================================
	/** 将帖子置顶
	 * 
	 * @param tag 板块标号
	 * @param post 帖子
	 * @return boolean
	 */
	public boolean makePostTop(int tag, Post post)
	{
		return systemManager.makePostTop(tag, post);
	}
	
	
	/** 取消帖子置顶
	 * 
	 * @param tag 板块标号
	 * @param post 帖子
	 * @return boolean
	 */
	public boolean cancelPostTop(int tag, Post post)
	{
		return systemManager.cancelPostTop(tag, post);
	}
	
	
	/** 删除帖子
	 * 
	 * @param tag 板块标号
	 * @param post 帖子
	 * @return boolean
	 */
	public boolean deletePost(int tag, Post post)
	{
		return systemManager.deletePost(tag, post);
	}
	
	
	/** 删除某个帖子里的某条评论
	 * 
	 * @param tag 板块标号
	 * @param post 帖子
	 * @param comment 评论
	 * @return boolean
	 */
	public boolean deleteComment(int tag, Post post, Comment comment)
	{
		return systemManager.deleteComment(tag, post, comment);
	}
	
	
	/** 删除公告
	 * 
	 * @param announcement 公告
	 * @return boolean
	 */
	public boolean deleteAnnouncement(Announcement announcement)
	{
		return systemManager.deleteAnnouncement(announcement);
	}
	
	
	/** 获取所有公告列表（不需排序）
	 * 
	 * @return ArrayList<Announcement>
	 */
	public ArrayList<Announcement> getAllAnnouncementList()
	{
		return systemManager.showAnnouncementList();
	}
	
	
	//================================================================
	/** 获取所有日志列表（已更新，已排序）
	 * 
	 * @return ArrayList<Log>
	 */
	public ArrayList<Log> getLogList()
	{
		return logManager.showLogList();
	}
	
	
}
