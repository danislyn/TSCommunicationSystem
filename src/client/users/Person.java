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
	 * ���췽��
	 * @param username �û�����һ�����룬�����޸�
	 * @param password ����
	 * @param authority Ȩ�ޣ�һ�����룬�����޸�
	 * @param location ����λ��
	 */
    public Person(String username, String password, int authority, int location) 
    {
    	this.username = username;
    	this.password = password;
    	this.authority = authority;
    	this.location = location;
    	
    	//��ʼ��Managers
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
     * ȡ���û���
     * @return String
     */
    public String getUsername()
    {
    	return username;
    }
    
    /**
     * ȡ������
     * @return String
     */
    public String getName()
    {
    	return name;
    }
    
    /**
     * ȡ��Ȩ��
     * @return int
     */
    public int getAuthority()
    {
    	return authority;
    }
    
    /**
     * ȡ������
     * @return String
     */
    public String getPassword()
    {
    	return password;
    }
    
    
    //================================================================================= 
    /** ����InformationManager
     * 
     */
    public void updateInformationManager()
    {
    	informationManager.update();
    }
    
    /** ����OnlinePersonManager
     * 
     */
    public void updateOnlinePersonManager()
    {
    	onlinePersonManager.update();
    }
    
    /** ����TeacherInfoManager
     * 
     */
    public void updateTeacherInfoManager()
    {
    	teacherInfoManager.update();
    }
    
    /** ����MailManager
     * 
     */
    public void updateMailManager()
    {
    	mailManager.update();
    }
    
    /** ����SentMailManager
     * 
     */
    public void updateSentMailManager()
    {
    	sentMailManager.update();
    }
    
    /** ����AddressManager
     * 
     */
    public void updateAddressManager()
    {
    	addressManager.update();
    }
    
    /** ����AnnouncementManager
     * 
     */
    public void updateAnnouncementManager()
    {
    	announcementManager.update();
    }
    
    /** ����FileManager
     * 
     */
    public void updateFileManager()
    {
    	fileManager.update();
    }
 
 
    
    //��Ϣ�����=========================================================================      
    /** �޸ĵ�¼����
     *  �����ڲ����������
     * @param oldPassword ԭ����
     * @param newPassword ������
     * @param committedPassword ȷ������
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
    		//��������
    		password = newPassword;
    		return true;
    	}
    	//�޸�ʧ��
    	return false;
    }
    
    
	/** �޸ĸ�����Ϣ
	 * 
	 * @param info ��set����Information����
	 * @return boolean
	 */
    public boolean reviseInformation(Information info)
    {
    	return informationManager.reviseInformation(info);
    }
    
    
	/** �õ��ҵĸ�����Ϣ
	 * 
	 * @return Information
	 */
    public Information getMyInformation()
    {
    	return informationManager.showInformation();
    }
    
    
    /** �õ����˵ĸ�����Ϣ
	 * 
	 * @param username ���˵�username
	 * @return Information
	 */
    public Information getOtherInformation(String username)
    {
    	return informationManager.showOtherInformation(username);
    }
    
    
    /** �õ����õ���Ȥ�б�
	 * 
	 * @return ArrayList<String>
	 */
    public ArrayList<String> getHobbyList()
    {
    	return informationManager.getInnerHobbyList();
    }
    
    
    /** �õ����õ��س��б�
	 * 
	 * @return ArrayList<String>
	 */
    public ArrayList<String> getAdeptnessList()
    {
    	return informationManager.getInnerAdeptnessList();
    }
    
    
	/** �õ�onlinePersonList��������
	 *  ��Ĭ�ϰ�username����
	 * @return ArrayList<OnlinePerson>
	 */
    public ArrayList<OnlinePerson> getOnlinePersonList()
    {
    	return onlinePersonManager.showList();
    }
    
    
    /**
     * ָ���û��Ƿ�����
     * @param username ָ���û���
     * @return boolean
     */
    public boolean isUserOnline(String username)
    {
    	return onlinePersonManager.isUserOnline(username);
    }
    
    /**
     * �õ�ָ���û�����λ��
     * @param username ָ���û���
     * @return int (-1������   1����   2��¥ )
     */
    public int getUserLocation(String username)
    {
    	return onlinePersonManager.getUserLocation(username);
    }
    
    
	/** ��ȡ������ʦ��Ϣ�б���������
	 * 
	 * @return ArrayList<TeacherInfo>
	 */
    public ArrayList<TeacherInfo> getTeacherInfoList()
    {
    	return teacherInfoManager.showList();
    }
    
    
	/** ����ĳ����ʦ���ճ�
	 * �����username����list�У�����null��
	 * @param username ��ʦ�û���
	 * @return TeacherInfo
	 */
	public TeacherInfo updateTeacherInfo(String username)
	{
		return teacherInfoManager.updateSchedule(username);
	}
    
   
    //�ʼ������=========================================================================
    /** ָ���ռ��� ���ż�
     * 
     * @param addressList �ռ���Address�б�
     * @param mail �ż�
     * @return boolean
     */
/*    public boolean sendMail(ArrayList<Address> addressList, Mail mail)
    {
    	//��addressList����ȡusername��list
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
    	//������ͳɹ�
    	if(resultCalendar != null)
    	{
    		mail.setTime(resultCalendar);
    		//���ż��ӵ�SentMailManager�� 		
    		sentMailManager.addMail(mail, usernameList.size());
    		
    		return true;
    	}  	
    	return false;
    }*/
    
    /** ָ���ռ��� ���ż�
     * 
     * @param receiverUsernameList �ռ���username�б�
     * @param mail �ż�
     * @return boolean
     */
    public boolean sendMail(ArrayList<String> receiverUsernameList, Mail mail)
    {
    	Calendar resultCalendar = mailManager.sendMail(receiverUsernameList, mail);
    	//������ͳɹ�
    	if(resultCalendar != null)
    	{
    		mail.setTime(resultCalendar);
    		//���ż��ӵ�SentMailManager�� 		
    		sentMailManager.addMail(mail, receiverUsernameList.size());
    		
    		return true;
    	}  	
    	return false;
    }
        
    
    /** ģ������ ���ż�
     * 
     * @param hobbyNumList �ռ�������ϵ���Ȥ����
     * @param adeptnessNumList �ռ�������ϵ��س�����
     * @param mail �ż�
     * @return boolean
     */
    public boolean sendMail(ArrayList<Integer> hobbyNumList, ArrayList<Integer> adeptnessNumList, Mail mail)
    {
    	Calendar resultCalendar = mailManager.sendMail(hobbyNumList, adeptnessNumList, mail);
    	//������ͳɹ�
    	if(resultCalendar != null)
    	{
    		mail.setTime(resultCalendar);
    		//���ż��ӵ�SentMailManager��
    		sentMailManager.addMail(mail, 0);
    		
    		return true;
    	}
    	return false;
    }
      
    
    /** �ظ��ż�
     * ��ͬ�ڷ�һ���ż���ԭʼ�ż��ķ�����
     * @param oldSenderUsername ԭʼ�ż��ķ�����
     * @param newMail ���ż�
     * @return boolean
     */
    public boolean replyMail(String oldSenderUsername, Mail newMail)
    {
    	//����¼��SentMailManager��
    	return mailManager.replyMail(oldSenderUsername, newMail);
    }
    
    
    /** ɾ���ռ����е��ż�
     * 
     * @param receivedMail �ռ����е��ż�
     * @return boolean
     */
    public boolean deleteReceivedMail(Mail receivedMail)
    {
    	return mailManager.deleteMail(receivedMail);
    }
    
    
    /** ���ռ����е��ż�
     *  ÿ�ε���ռ����е��ż�ʱ�����ô˷�����Or ֻ�ڵ��δ���ż�ʱ���ã���
     * @param receivedMail �ռ����е��ż�
     * @return boolean
     */
    public boolean readReceivedMail(Mail receivedMail)
    {
    	//ȷ�����ż�δ����
    	if(receivedMail.getIsRead() == false)
    	{
    		return mailManager.readMail(receivedMail);
    	}
    	return true;
    }
    
    
    /** �õ��ռ�����δ���ż�����
     * 
     * @return int
     */
    public int getUnreadReceivedMailSum()
    {
    	return mailManager.getUnreadSum();
    }
    
    
    /** �õ��ռ����б�������
     * 
     * @return ArrayList<Mail>
     */
    public ArrayList<Mail> getReceivedMailList()
    {
    	return mailManager.showList();
    }
    
    
    /** �õ�����������������ż�����ϸ������Ϣ���������б��ռ����б�
     * 
     * @param sentFeedbackMail �ѷ��Ĳ�����������ż�
     * @return FeedbackMail
     */
    public FeedbackMail getFeedbackMailDetail(FeedbackMail sentFeedbackMail)
    {
    	//������ż�����������ģ�ֱ�ӷ��ظ��ż�
    	if(sentFeedbackMail.getIsNeedFeedback() == false)
    	{
    		return sentFeedbackMail;
    	}
    	
    	return sentMailManager.getFeedbackDetail(sentFeedbackMail);
    }
    
    
    /** ɾ���������е��ż�
     * 
     * @param sentMail �������е��ż�
     * @return boolean
     */
    public boolean deleteSentMail(FeedbackMail sentMail)
    {
    	return sentMailManager.deleteMail(sentMail);
    }
    
    
    /** �õ��������б�������
     * 
     * @return rrayList<FeedbackMail>
     */
    public ArrayList<FeedbackMail> getSentMailList()
    {
    	return sentMailManager.showList();
    }
    
    
    /**
     * ȡ���ռ����ż��������û�������ʾ�Ƚϣ�
     * @return int
     */
    public int getReceivedMailSum()
    {
    	return mailManager.getMailSum();
    }
    
    
    /**
     * �����ռ����б��û�������ʾ��
     * @param mailList ���µ��ռ����б�
     */
    public void setReceivedMailList(ArrayList<Mail> mailList)
    {
    	mailManager.setMailList(mailList);
    }
    
    
    //===========================================================
    /** ɾ��ĳ����
     *  ǰ�����������鲻����
     * @param groupName ����
     * @return boolean
     */
    public boolean deleteAddressGroup(String groupName)
    {
    	return addressManager.deleteGroup(groupName);
    }
    
    
    /** ������ɾ����ַ�б�
     *  ǰ����������������
     * @param groupName ����
     * @param addressList ��ַ�б�
     * @return boolean
     */
    public boolean deleteAddressInGroup(String groupName, ArrayList<Address> addressList)
    {
    	return addressManager.deleteGroupAddress(groupName, addressList);
    }
    
    
	/** ��������ӵ�ַ
	 *  �������������ڣ����¿�һ����
	 * @param groupName ����
	 * @param addressList ��ַ�б�
	 * @return boolean
	 */
    public boolean addAddressInGroup(String groupName, ArrayList<Address> addressList)
    {
    	return addressManager.addGroupAddress(groupName, addressList);
    }
    
    
    /** ��ȡ˽�е�ַ���б�������
     * 
     * @return ArrayList<AddressGroup>
     */
    public ArrayList<AddressGroup> getPrivateAddressGroupList()
    {
    	return addressManager.showPrivateList();
    }
    
    
    /** ��ȡȫ�ֵ�ַ���б�������
     * 
     * @return ArrayList<AddressGroup>
     */
    public ArrayList<AddressGroup> getGlobalAddressGroupList()
    {
    	return addressManager.getGlobalList();
    }
    
    
    //���������==============================================================================
    /** ��������
     *  ���ѶԹ����Ƿ�����������걸�Լ�飩
     *  @param announcement ����
     *  @return boolean
     */
    public boolean feedbackAnnouncement(Announcement announcement)
    {
    	//����ù����ѻ�����ֱ�ӷ���true
    	if(announcement.getIsFeedback() == true)
    	{
    		return true;
    	}
    	
    	return announcementManager.feedbackAnnouncement(announcement);
    }
    
    
    /** ��ȡδ������������
     * 
     * @return int
     */
    public int getUnfeedbackAnnouncementSum()
    {
    	return announcementManager.getUnfeedbackSum();
    }
    
    
    /** ��ȡδ���������б�
     * 
     * @return ArrayList<Announcement>
     */
    public ArrayList<Announcement> getUnfeedbackAnnouncementList()
    {
    	return announcementManager.showUnfeedbackList();
    }
    
    
    /** ��ȡ���пɼ������б�������
     * 
     * @return ArrayList<Announcement>
     */
    public ArrayList<Announcement> getAnnouncementList()
    {
    	return announcementManager.showList();
    }
    
    
    //���������==============================================================================
    /** �������а��
	 * 
	 */
	public void updateAllBlock()
	{
		postManager.updateAllBlock();
	}
	
	/** ����ָ�����
	 * 
	 * @param tag �����
	 */
	public void updateOneBlock(int tag)
	{
		postManager.updateOneBlock(tag);
	}
	
	
	/** �Ķ�����
	 *  �������+1��
	 * @param tag �����
	 * @param post ����
	 * @return boolean�������ģ�
	 */
	public boolean readPost(int tag, Post post)
	{
		return postManager.readPost(tag, post);
	}
	
	
	/** ���������ۣ�
	 * 
	 * @param tag �����
	 * @param post ����
	 * @param comment ����
	 * @return boolean
	 */
	public boolean replyPost(int tag, Post post, Comment comment)
	{
		return postManager.replyPost(tag, post, comment);
	}
	
	
	/** ����
	 * 
	 * @param post ����
	 * @return boolean
	 */
	public boolean sendPost(Post post)
	{
		return postManager.sendPost(post);
	}
    
    
	/** �޸��Լ���������
	 *  ǰ��������post���޸�time
	 * @param post �޸ĺ������
	 * @return boolean
	 */
	public boolean revisePost(Post post)
	{
		return postManager.revisePost(post);
	}
	
	
	/** ��ȡָ�����������б�������
	 * 
	 * @param tag �����
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> getPostList(int tag)
	{
		return postManager.showList(tag);
	}
    
    
	/** ��ȡĳ�����ָ������������б�
	 * 
	 * @param tag �����
	 * @param category ���
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchByCategory(int tag, String category)
	{
		return postManager.searchByCategory(tag, category);
	}
    
	/** ��ȡ���а����ָ������������б�
	 * 
	 * @param category ���
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchGlobalByCategory(String category)
	{
		return postManager.searchGlobalByCategory(category);
	}
    
	
	/** ��ȡĳ�����ָ�������������������б�
	 * 
	 * @param tag �����
	 * @param senderName ��������������name��
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchBySenderName(int tag, String senderName)
	{
		return postManager.searchBySenderName(tag, senderName);
	}
	
	/** ��ȡ���а����ָ�������������������б�
	 * 
	 * @param senderName ��������������name��
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchGlobalBySenderName(String senderName)
	{
		return postManager.searchGlobalBySenderName(senderName);
	}
    
	
	//�ļ������=====================================================================
	/** �ϴ��ļ�
	 * 
	 * @return boolean
	 */
	public boolean uploadFile()
	{
		return fileManager.uploadFile();
	}
	
	
	/** �����ļ�
	 * 
	 * @param fileBaseInfo �ļ���Ϣ
	 * @return boolean
	 */
	public boolean downloadFile(FileBaseInfo fileBaseInfo)
	{
		return fileManager.downloadFile(fileBaseInfo);
	}
	
	
	/** ��ȡ�����ļ���Ϣ�б���������
	 * 
	 * @return ArrayList<FileBaseInfo>
	 */
	public ArrayList<FileBaseInfo> getFileList()
	{
		return fileManager.showList();
	}

	
	
    //��Ȩ����==============================================================================
    /** Teacher��Ȩ���� */
	public void updateSentAnnouncementManager(){}
    
    /** Teacher��Ȩ���� */
    public boolean sendAnnouncement(Announcement announcement){return false;}
	
    /** Teacher��Ȩ���� */
	public boolean reviseAnnouncement(FeedbackAnnouncement announcement){return false;}
	
    /** Teacher��Ȩ���� */
	public ArrayList<FeedbackAnnouncement> getSentAnnouncementList(){return (new ArrayList<FeedbackAnnouncement>());}
	
	
	
	/** Teacher��Ȩ���� */
	public void updateScheduleManager(){}
	
	/** Teacher��Ȩ���� */
	public boolean saveSchedule(Schedule schedule){return false;}
	
	/** Teacher��Ȩ���� */
	public boolean deleteSchedule(int year, int month, int day){return false;}
	
	/** Teacher��Ȩ���� */
	public ArrayList<Schedule> getAllScheduleList(){return (new ArrayList<Schedule>());}
	
	/** Teacher��Ȩ���� */
	public ArrayList<Schedule> getScheduleListByYearAndMonth(int year, int month){return (new ArrayList<Schedule>());}
	
	
	
	/** AdminTeacher��Ȩ���� */
	public void updateUserManager(){}
	
	/** AdminTeacher��Ȩ���� */
	public boolean resetUserPassword(String username, String passowrd){return false;}
	
	/** AdminTeacher��Ȩ���� */
	public boolean reviseUser(UserBaseInfo userBaseInfo){return false;}
	
	/** AdminTeacher��Ȩ���� */
	public boolean addUser(UserBaseInfo userBaseInfo){return false;}
	
	/** AdminTeacher��Ȩ���� */
	public boolean addUser(ArrayList<UserBaseInfo> userList){return false;}
	
	/** AdminTeacher��Ȩ���� */
	public boolean deleteUser(UserBaseInfo userBaseInfo){return false;}
	
	/** AdminTeacher��Ȩ���� */
	public ArrayList<UserBaseInfo> getAllUserList(){return (new ArrayList<UserBaseInfo>());}
	
	/** AdminTeacher��Ȩ���� */
	public ArrayList<UserBaseInfo> getUserListByGrade(int grade){return (new ArrayList<UserBaseInfo>());}
	
	/** AdminTeacher��Ȩ���� */
	public UserBaseInfo getOneUser(String username){return (new UserBaseInfo());}
	
	
	
	/** AdminTeacher��Ȩ���� */
	public void updateSystemManager(){}
	
	/** AdminTeacher��Ȩ���� */
	public boolean makePostTop(int tag, Post post){return false;}
	
	/** AdminTeacher��Ȩ���� */
	public boolean cancelPostTop(int tag, Post post){return false;}
	
	/** AdminTeacher��Ȩ���� */
	public boolean deletePost(int tag, Post post){return false;}
	
	/** AdminTeacher��Ȩ���� */
	public boolean deleteComment(int tag, Post post, Comment comment){return false;}
	
	/** AdminTeacher��Ȩ���� */
	public boolean deleteAnnouncement(Announcement announcement){return false;}
	
	/** AdminTeacher��Ȩ���� */
	public ArrayList<Announcement> getAllAnnouncementList(){return (new ArrayList<Announcement>());}
	
	
	
	/** AdminTeacher��Ȩ���� */
	public ArrayList<Log> getLogList(){return (new ArrayList<Log>());}
	
	
	
	/** MajorTeacher��Ȩ���� */
	public ArrayList<Information> searchSuitableStudent(ArrayList<Integer> adeptnessNumList){return (new ArrayList<Information>());}
	
    
}
