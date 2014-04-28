package client.users;

import java.util.ArrayList;
import java.util.Calendar;

import beans.admin.Log;
import beans.admin.UserBaseInfo;
import beans.announcement.Announcement;
import beans.announcement.FeedbackAnnouncement;
import beans.file.FileBaseInfo;
import beans.information.Information;
import beans.information.OnlinePerson;
import beans.information.TeacherInfo;
import beans.mail.Address;
import beans.mail.AddressGroup;
import beans.mail.FeedbackMail;
import beans.mail.Mail;
import beans.post.Comment;
import beans.post.Post;
import beans.teacher.Schedule;

import managers.announcement.AnnouncementManager;
import managers.file.FileManager;
import managers.information.InformationManager;
import managers.information.OnlinePersonManager;
import managers.information.PasswordManager;
import managers.information.TeacherInfoManager;
import managers.mail.AddressManager;
import managers.mail.MailManager;
import managers.mail.SentMailManager;
import managers.post.PostManager;


public class Person {
	
	protected final String username;
	
	protected String password;
	
	protected final int authority;
	
	protected int location;
	
	protected final String name;
	
	protected final int grade;
	
	
	protected InformationManager informationManager;
	
	protected PasswordManager passwordManager;
	
    protected OnlinePersonManager onlinePersonManager;
	
	protected TeacherInfoManager teacherInfoManager;	
	
	protected MailManager mailManager;
	
	protected SentMailManager sentMailManager;
	
	protected AddressManager addressManager;
	
	protected PostManager postManager;
	
	protected AnnouncementManager announcementManager;
	
	protected FileManager fileManager;
    
	
    
	/** 
	 * 构造方法
	 * @param username 用户名，一旦传入，不可修改
	 * @param password 密码
	 * @param authority 权限，一旦传入，不可修改
	 * @param location 地理位置
	 */
    public Person(String username, String password, int authority, int location) 
    {
    	this.username = username;
    	this.password = password;
    	this.authority = authority;
    	this.location = location;
    	
    	//初始化Managers
    	informationManager = new InformationManager(this.username);
    	
    	this.name = informationManager.showInformation().getName();
    	this.grade = informationManager.showInformation().getGrade();
    	
    	passwordManager =  new PasswordManager();
    	onlinePersonManager = new OnlinePersonManager();
    	mailManager = new MailManager(this.username);
    	sentMailManager = new SentMailManager(this.username);
    	addressManager = new AddressManager(this.username);
    	announcementManager = new AnnouncementManager(this.username, grade);
    	postManager = new PostManager(this.username, this.authority);
    	
    	fileManager = new FileManager(this.username);
    	teacherInfoManager = new TeacherInfoManager();
    }
    
    
    /**
     * 取出用户名
     * @return String
     */
    public String getUsername()
    {
    	return username;
    }
    
    /**
     * 取出姓名
     * @return String
     */
    public String getName()
    {
    	return name;
    }
    
    /**
     * 取出权限
     * @return int
     */
    public int getAuthority()
    {
    	return authority;
    }
    
    /**
     * 取出密码
     * @return String
     */
    public String getPassword()
    {
    	return password;
    }
    
    
    //================================================================================= 
    /** 更新InformationManager
     * 
     */
    public void updateInformationManager()
    {
    	informationManager.update();
    }
    
    /** 更新OnlinePersonManager
     * 
     */
    public void updateOnlinePersonManager()
    {
    	onlinePersonManager.update();
    }
    
    /** 更新TeacherInfoManager
     * 
     */
    public void updateTeacherInfoManager()
    {
    	teacherInfoManager.update();
    }
    
    /** 更新MailManager
     * 
     */
    public void updateMailManager()
    {
    	mailManager.update();
    }
    
    /** 更新SentMailManager
     * 
     */
    public void updateSentMailManager()
    {
    	sentMailManager.update();
    }
    
    /** 更新AddressManager
     * 
     */
    public void updateAddressManager()
    {
    	addressManager.update();
    }
    
    /** 更新AnnouncementManager
     * 
     */
    public void updateAnnouncementManager()
    {
    	announcementManager.update();
    }
    
    /** 更新FileManager
     * 
     */
    public void updateFileManager()
    {
    	fileManager.update();
    }
 
 
    
    //信息类操作=========================================================================      
    /** 修改登录密码
     *  方法内部检验过参数
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param committedPassword 确认密码
     * @return boolean
     */
    public boolean revisePassword(String oldPassword, String newPassword, String committedPassword)
    {
    	if(oldPassword.equals(password) == false)
    	{
    		return false;
    	}
    	if(newPassword.equals(committedPassword) ==  false)
    	{
    		return false;
    	}
    	
    	if(passwordManager.revisePassword(username, newPassword) ==  true)
    	{
    		//更新密码
    		password = newPassword;
    		return true;
    	}
    	//修改失败
    	return false;
    }
    
    
	/** 修改个人信息
	 * 
	 * @param info 已set过的Information对象
	 * @return boolean
	 */
    public boolean reviseInformation(Information info)
    {
    	return informationManager.reviseInformation(info);
    }
    
    
	/** 得到我的个人信息
	 * 
	 * @return Information
	 */
    public Information getMyInformation()
    {
    	return informationManager.showInformation();
    }
    
    
    /** 得到别人的个人信息
	 * 
	 * @param username 别人的username
	 * @return Information
	 */
    public Information getOtherInformation(String username)
    {
    	return informationManager.showOtherInformation(username);
    }
    
    
    /** 得到内置的兴趣列表
	 * 
	 * @return ArrayList<String>
	 */
    public ArrayList<String> getHobbyList()
    {
    	return informationManager.getInnerHobbyList();
    }
    
    
    /** 得到内置的特长列表
	 * 
	 * @return ArrayList<String>
	 */
    public ArrayList<String> getAdeptnessList()
    {
    	return informationManager.getInnerAdeptnessList();
    }
    
    
	/** 得到onlinePersonList（已排序）
	 *  ，默认按username排序
	 * @return ArrayList<OnlinePerson>
	 */
    public ArrayList<OnlinePerson> getOnlinePersonList()
    {
    	return onlinePersonManager.showList();
    }
    
    
    /**
     * 指定用户是否在线
     * @param username 指定用户名
     * @return boolean
     */
    public boolean isUserOnline(String username)
    {
    	return onlinePersonManager.isUserOnline(username);
    }
    
    /**
     * 得到指定用户地理位置
     * @param username 指定用户名
     * @return int (-1不在线   1仙林   2鼓楼 )
     */
    public int getUserLocation(String username)
    {
    	return onlinePersonManager.getUserLocation(username);
    }
    
    
	/** 获取所有老师信息列表（不需排序）
	 * 
	 * @return ArrayList<TeacherInfo>
	 */
    public ArrayList<TeacherInfo> getTeacherInfoList()
    {
    	return teacherInfoManager.showList();
    }
    
    
	/** 更新某个老师的日程
	 * （如果username不在list中，返回null）
	 * @param username 老师用户名
	 * @return TeacherInfo
	 */
	public TeacherInfo updateTeacherInfo(String username)
	{
		return teacherInfoManager.updateSchedule(username);
	}
    
   
    //邮件类操作=========================================================================
    /** 指定收件人 发信件
     * 
     * @param addressList 收件人Address列表
     * @param mail 信件
     * @return boolean
     */
/*    public boolean sendMail(ArrayList<Address> addressList, Mail mail)
    {
    	//从addressList中提取username的list
    	ArrayList<String> usernameList = new ArrayList<String>();
    	for(int i=0; i<addressList.size(); i++)
    	{
    		String receiverUsername = addressList.get(i).getUsername();
    		if(receiverUsername != null)
    		{
    			usernameList.add(receiverUsername);
    		}
    	}
    	
    	Calendar resultCalendar = mailManager.sendMail(usernameList, mail);
    	//如果发送成功
    	if(resultCalendar != null)
    	{
    		mail.setTime(resultCalendar);
    		//把信件加到SentMailManager中 		
    		sentMailManager.addMail(mail, usernameList.size());
    		
    		return true;
    	}  	
    	return false;
    }*/
    
    /** 指定收件人 发信件
     * 
     * @param receiverUsernameList 收件人username列表
     * @param mail 信件
     * @return boolean
     */
    public boolean sendMail(ArrayList<String> receiverUsernameList, Mail mail)
    {
    	Calendar resultCalendar = mailManager.sendMail(receiverUsernameList, mail);
    	//如果发送成功
    	if(resultCalendar != null)
    	{
    		mail.setTime(resultCalendar);
    		//把信件加到SentMailManager中 		
    		sentMailManager.addMail(mail, receiverUsernameList.size());
    		
    		return true;
    	}  	
    	return false;
    }
        
    
    /** 模糊条件 发信件
     * 
     * @param hobbyNumList 收件人需符合的兴趣条件
     * @param adeptnessNumList 收件人需符合的特长条件
     * @param mail 信件
     * @return boolean
     */
    public boolean sendMail(ArrayList<Integer> hobbyNumList, ArrayList<Integer> adeptnessNumList, Mail mail)
    {
    	Calendar resultCalendar = mailManager.sendMail(hobbyNumList, adeptnessNumList, mail);
    	//如果发送成功
    	if(resultCalendar != null)
    	{
    		mail.setTime(resultCalendar);
    		//把信件加到SentMailManager中
    		sentMailManager.addMail(mail, 0);
    		
    		return true;
    	}
    	return false;
    }
      
    
    /** 回复信件
     * 等同于发一封信件给原始信件的发件人
     * @param oldSenderUsername 原始信件的发件人
     * @param newMail 新信件
     * @return boolean
     */
    public boolean replyMail(String oldSenderUsername, Mail newMail)
    {
    	//不记录到SentMailManager中
    	return mailManager.replyMail(oldSenderUsername, newMail);
    }
    
    
    /** 删除收件箱中的信件
     * 
     * @param receivedMail 收件箱中的信件
     * @return boolean
     */
    public boolean deleteReceivedMail(Mail receivedMail)
    {
    	return mailManager.deleteMail(receivedMail);
    }
    
    
    /** 读收件箱中的信件
     *  每次点击收件箱中的信件时都调用此方法（Or 只在点击未读信件时调用？）
     * @param receivedMail 收件箱中的信件
     * @return boolean
     */
    public boolean readReceivedMail(Mail receivedMail)
    {
    	//确保该信件未读过
    	if(receivedMail.getIsRead() == false)
    	{
    		return mailManager.readMail(receivedMail);
    	}
    	return true;
    }
    
    
    /** 得到收件箱中未读信件总数
     * 
     * @return int
     */
    public int getUnreadReceivedMailSum()
    {
    	return mailManager.getUnreadSum();
    }
    
    
    /** 得到收件箱列表（已排序）
     * 
     * @return ArrayList<Mail>
     */
    public ArrayList<Mail> getReceivedMailList()
    {
    	return mailManager.showList();
    }
    
    
    /** 得到发件箱中需回馈的信件的详细反馈信息（回馈人列表及收件人列表）
     * 
     * @param sentFeedbackMail 已发的并且需回馈的信件
     * @return FeedbackMail
     */
    public FeedbackMail getFeedbackMailDetail(FeedbackMail sentFeedbackMail)
    {
    	//如果该信件不是需回馈的，直接返回该信件
    	if(sentFeedbackMail.getIsNeedFeedback() == false)
    	{
    		return sentFeedbackMail;
    	}
    	
    	return sentMailManager.getFeedbackDetail(sentFeedbackMail);
    }
    
    
    /** 删除发件箱中的信件
     * 
     * @param sentMail 发件箱中的信件
     * @return boolean
     */
    public boolean deleteSentMail(FeedbackMail sentMail)
    {
    	return sentMailManager.deleteMail(sentMail);
    }
    
    
    /** 得到发件箱列表（已排序）
     * 
     * @return rrayList<FeedbackMail>
     */
    public ArrayList<FeedbackMail> getSentMailList()
    {
    	return sentMailManager.showList();
    }
    
    
    /**
     * 取出收件箱信件总数（用户来信提示比较）
     * @return int
     */
    public int getReceivedMailSum()
    {
    	return mailManager.getMailSum();
    }
    
    
    /**
     * 重设收件箱列表（用户来信提示）
     * @param mailList 最新的收件箱列表
     */
    public void setReceivedMailList(ArrayList<Mail> mailList)
    {
    	mailManager.setMailList(mailList);
    }
    
    
    //===========================================================
    /** 删除某分组
     *  前置条件：分组不重名
     * @param groupName 组名
     * @return boolean
     */
    public boolean deleteAddressGroup(String groupName)
    {
    	return addressManager.deleteGroup(groupName);
    }
    
    
    /** 从组中删除地址列表
     *  前置条件：组名存在
     * @param groupName 组名
     * @param addressList 地址列表
     * @return boolean
     */
    public boolean deleteAddressInGroup(String groupName, ArrayList<Address> addressList)
    {
    	return addressManager.deleteGroupAddress(groupName, addressList);
    }
    
    
	/** 向组中添加地址
	 *  若该组名不存在，则新开一个组
	 * @param groupName 组名
	 * @param addressList 地址列表
	 * @return boolean
	 */
    public boolean addAddressInGroup(String groupName, ArrayList<Address> addressList)
    {
    	return addressManager.addGroupAddress(groupName, addressList);
    }
    
    
    /** 获取私有地址簿列表（已排序）
     * 
     * @return ArrayList<AddressGroup>
     */
    public ArrayList<AddressGroup> getPrivateAddressGroupList()
    {
    	return addressManager.showPrivateList();
    }
    
    
    /** 获取全局地址簿列表（已排序）
     * 
     * @return ArrayList<AddressGroup>
     */
    public ArrayList<AddressGroup> getGlobalAddressGroupList()
    {
    	return addressManager.getGlobalList();
    }
    
    
    //公告类操作==============================================================================
    /** 回馈公告
     *  （已对公告是否回馈过做了完备性检查）
     *  @param announcement 公告
     *  @return boolean
     */
    public boolean feedbackAnnouncement(Announcement announcement)
    {
    	//如果该公告已回馈，直接返回true
    	if(announcement.getIsFeedback() == true)
    	{
    		return true;
    	}
    	
    	return announcementManager.feedbackAnnouncement(announcement);
    }
    
    
    /** 获取未回馈公告总数
     * 
     * @return int
     */
    public int getUnfeedbackAnnouncementSum()
    {
    	return announcementManager.getUnfeedbackSum();
    }
    
    
    /** 获取未回馈公告列表
     * 
     * @return ArrayList<Announcement>
     */
    public ArrayList<Announcement> getUnfeedbackAnnouncementList()
    {
    	return announcementManager.showUnfeedbackList();
    }
    
    
    /** 获取所有可见公告列表（已排序）
     * 
     * @return ArrayList<Announcement>
     */
    public ArrayList<Announcement> getAnnouncementList()
    {
    	return announcementManager.showList();
    }
    
    
    //帖子类操作==============================================================================
    /** 更新所有板块
	 * 
	 */
	public void updateAllBlock()
	{
		postManager.updateAllBlock();
	}
	
	/** 更新指定板块
	 * 
	 * @param tag 板块标号
	 */
	public void updateOneBlock(int tag)
	{
		postManager.updateOneBlock(tag);
	}
	
	
	/** 阅读帖子
	 *  （浏览量+1）
	 * @param tag 板块标号
	 * @param post 帖子
	 * @return boolean（不关心）
	 */
	public boolean readPost(int tag, Post post)
	{
		return postManager.readPost(tag, post);
	}
	
	
	/** 回帖（评论）
	 * 
	 * @param tag 板块标号
	 * @param post 帖子
	 * @param comment 评论
	 * @return boolean
	 */
	public boolean replyPost(int tag, Post post, Comment comment)
	{
		return postManager.replyPost(tag, post, comment);
	}
	
	
	/** 发帖
	 * 
	 * @param post 帖子
	 * @return boolean
	 */
	public boolean sendPost(Post post)
	{
		return postManager.sendPost(post);
	}
    
    
	/** 修改自己发的帖子
	 *  前置条件：post不修改time
	 * @param post 修改后的帖子
	 * @return boolean
	 */
	public boolean revisePost(Post post)
	{
		return postManager.revisePost(post);
	}
	
	
	/** 获取指定板块的帖子列表（已排序）
	 * 
	 * @param tag 板块标号
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> getPostList(int tag)
	{
		return postManager.showList(tag);
	}
    
    
	/** 获取某板块中指定分类的帖子列表
	 * 
	 * @param tag 板块标号
	 * @param category 类别
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchByCategory(int tag, String category)
	{
		return postManager.searchByCategory(tag, category);
	}
    
	/** 获取所有板块中指定分类的帖子列表
	 * 
	 * @param category 类别
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchGlobalByCategory(String category)
	{
		return postManager.searchGlobalByCategory(category);
	}
    
	
	/** 获取某板块中指定发帖人姓名的帖子列表
	 * 
	 * @param tag 板块标号
	 * @param senderName 发帖人姓名（是name）
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchBySenderName(int tag, String senderName)
	{
		return postManager.searchBySenderName(tag, senderName);
	}
	
	/** 获取所有板块中指定发帖人姓名的帖子列表
	 * 
	 * @param senderName 发帖人姓名（是name）
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchGlobalBySenderName(String senderName)
	{
		return postManager.searchGlobalBySenderName(senderName);
	}
    
	
	//文件类操作=====================================================================
	/** 上传文件
	 * 
	 * @return boolean
	 */
	public boolean uploadFile()
	{
		return fileManager.uploadFile();
	}
	
	
	/** 下载文件
	 * 
	 * @param fileBaseInfo 文件信息
	 * @return boolean
	 */
	public boolean downloadFile(FileBaseInfo fileBaseInfo)
	{
		return fileManager.downloadFile(fileBaseInfo);
	}
	
	
	/** 获取所有文件信息列表（不需排序）
	 * 
	 * @return ArrayList<FileBaseInfo>
	 */
	public ArrayList<FileBaseInfo> getFileList()
	{
		return fileManager.showList();
	}

	
	
    //特权操作==============================================================================
    /** Teacher特权操作 */
	public void updateSentAnnouncementManager(){}
    
    /** Teacher特权操作 */
    public boolean sendAnnouncement(Announcement announcement){return false;}
	
    /** Teacher特权操作 */
	public boolean reviseAnnouncement(FeedbackAnnouncement announcement){return false;}
	
    /** Teacher特权操作 */
	public ArrayList<FeedbackAnnouncement> getSentAnnouncementList(){return (new ArrayList<FeedbackAnnouncement>());}
	
	
	
	/** Teacher特权操作 */
	public void updateScheduleManager(){}
	
	/** Teacher特权操作 */
	public boolean saveSchedule(Schedule schedule){return false;}
	
	/** Teacher特权操作 */
	public boolean deleteSchedule(int year, int month, int day){return false;}
	
	/** Teacher特权操作 */
	public ArrayList<Schedule> getAllScheduleList(){return (new ArrayList<Schedule>());}
	
	/** Teacher特权操作 */
	public ArrayList<Schedule> getScheduleListByYearAndMonth(int year, int month){return (new ArrayList<Schedule>());}
	
	
	
	/** AdminTeacher特权操作 */
	public void updateUserManager(){}
	
	/** AdminTeacher特权操作 */
	public boolean resetUserPassword(String username, String passowrd){return false;}
	
	/** AdminTeacher特权操作 */
	public boolean reviseUser(UserBaseInfo userBaseInfo){return false;}
	
	/** AdminTeacher特权操作 */
	public boolean addUser(UserBaseInfo userBaseInfo){return false;}
	
	/** AdminTeacher特权操作 */
	public boolean addUser(ArrayList<UserBaseInfo> userList){return false;}
	
	/** AdminTeacher特权操作 */
	public boolean deleteUser(UserBaseInfo userBaseInfo){return false;}
	
	/** AdminTeacher特权操作 */
	public ArrayList<UserBaseInfo> getAllUserList(){return (new ArrayList<UserBaseInfo>());}
	
	/** AdminTeacher特权操作 */
	public ArrayList<UserBaseInfo> getUserListByGrade(int grade){return (new ArrayList<UserBaseInfo>());}
	
	/** AdminTeacher特权操作 */
	public UserBaseInfo getOneUser(String username){return (new UserBaseInfo());}
	
	
	
	/** AdminTeacher特权操作 */
	public void updateSystemManager(){}
	
	/** AdminTeacher特权操作 */
	public boolean makePostTop(int tag, Post post){return false;}
	
	/** AdminTeacher特权操作 */
	public boolean cancelPostTop(int tag, Post post){return false;}
	
	/** AdminTeacher特权操作 */
	public boolean deletePost(int tag, Post post){return false;}
	
	/** AdminTeacher特权操作 */
	public boolean deleteComment(int tag, Post post, Comment comment){return false;}
	
	/** AdminTeacher特权操作 */
	public boolean deleteAnnouncement(Announcement announcement){return false;}
	
	/** AdminTeacher特权操作 */
	public ArrayList<Announcement> getAllAnnouncementList(){return (new ArrayList<Announcement>());}
	
	
	
	/** AdminTeacher特权操作 */
	public ArrayList<Log> getLogList(){return (new ArrayList<Log>());}
	
	
	
	/** MajorTeacher特权操作 */
	public ArrayList<Information> searchSuitableStudent(ArrayList<Integer> adeptnessNumList){return (new ArrayList<Information>());}
	
    
}
