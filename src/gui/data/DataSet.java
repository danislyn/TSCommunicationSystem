package gui.data;

import gui.main.MainFrame;

import java.util.ArrayList;
import client.login.Login;

import beans.admin.Log;
import beans.admin.UserBaseInfo;
import beans.announcement.Announcement;
import beans.announcement.FeedbackAnnouncement;
import beans.file.FileBaseInfo;
import beans.mail.Address;
import beans.mail.AddressGroup;
import beans.mail.FeedbackMail;
import beans.mail.Mail;
import beans.post.Post;


public class DataSet {
	
	public static MainFrame mainFrame;
	
	//mail
	public static ArrayList<Mail> receivedMailList;
	public static ArrayList<FeedbackMail> sentMailList;
	
	public static ArrayList<AddressGroup> privateAddressGroupList;
	public static ArrayList<AddressGroup> globalAddressGroupList;
	
	public static ArrayList<String> hobbyDefinitionList;
	public static ArrayList<String> adeptnessDefinitionList;
	///////////////////////////
	
	//announcement
	public static ArrayList<Announcement> announcementList;
	public static ArrayList<FeedbackAnnouncement> sentAnnouncementList;
	
	//post
	//public static ArrayList<Post> allPost;//所有帖子
	public static ArrayList<Post> postList1;//学生帖子
	public static ArrayList<Post> postList2;//任课老师帖子
	public static ArrayList<Post> postList3;//教务员帖子
	public static ArrayList<Post> postList4;//管理员帖子
	//////////////////////////////////
	
	//file
	public static ArrayList<FileBaseInfo> fileList;
	///////////////
	
	
	//管理员用到的
	public static ArrayList<UserBaseInfo> allUserList;
	public static ArrayList<UserBaseInfo> userList0;  //grade=0
	public static ArrayList<UserBaseInfo> userList1;  //grade=1
	public static ArrayList<UserBaseInfo> userList2;  //grade=2
	public static ArrayList<UserBaseInfo> userList3;  //grade=3
	public static ArrayList<UserBaseInfo> userList4;  //grade=4
	
	public static ArrayList<Log> logList;
	//////////////
	
	static
	{
		userList0 = new ArrayList<UserBaseInfo>();
		userList1 = new ArrayList<UserBaseInfo>();
		userList2 = new ArrayList<UserBaseInfo>();
		userList3 = new ArrayList<UserBaseInfo>();
		userList4 = new ArrayList<UserBaseInfo>();
		
		
		//只需取一次
		hobbyDefinitionList = Login.person.getHobbyList();
		adeptnessDefinitionList = Login.person.getAdeptnessList();
				
		
		updateReceivedMailList();
		updateSentMailList();
		
		updateGlobalAddressGroupList();
		updatePrivateAddressGroupList();
		
		updateAnnouncementList();
		updateSentAnnouncementList();
		updatePostList();
		
		updateFileList();
		
		/////////////
		updateAllUserList();
		updateLogList();
		
	}
	
	/**
	 * 重新取收件箱列表（只是从person里取）
	 */
	public static void updateReceivedMailList()
	{
		receivedMailList = Login.person.getReceivedMailList();
	}
	
	/**
	 * 重新取发件箱列表
	 */
	public static void updateSentMailList()
	{
		sentMailList = Login.person.getSentMailList();
	}
	
	/**
	 * 重新取全局地址簿
	 */
	public static void updateGlobalAddressGroupList()
	{	
		globalAddressGroupList = Login.person.getGlobalAddressGroupList();
	}
	
	/**
	 * 重新取私有地址簿
	 */
	public static void updatePrivateAddressGroupList()
	{
		
		privateAddressGroupList = Login.person.getPrivateAddressGroupList();
	}
	
	/**
	 * 重新取所有板块帖子列表
	 */
	public static void updatePostList()
	{
		//update
		//Login.person.updateAllBlock();
		
		postList1=Login.person.getPostList(1);
		postList2=Login.person.getPostList(2);
		postList3=Login.person.getPostList(3);
		postList4=Login.person.getPostList(4);
	}	

	/**
	 * 重新取共享文件列表
	 */
	public static void updateFileList()
	{
		fileList = Login.person.getFileList();
	}

	/**
	 * 重新取公告列表
	 */
	public static void updateAnnouncementList()
	{
		announcementList = Login.person.getAnnouncementList();
	}
	
	/**
	 * 重新取已发公告列表
	 */
	public static void updateSentAnnouncementList()
	{
		sentAnnouncementList = Login.person.getSentAnnouncementList();
	}

	/**
	 * 重新取所有用户列表
	 */
	public static void updateAllUserList()
	{
		allUserList = Login.person.getAllUserList();
		
		userList0.clear();
		userList1.clear();
		userList2.clear();
		userList3.clear();
		userList4.clear();
		
		//分类
		for(int i=0; i<allUserList.size(); i++)
		{
			UserBaseInfo userBaseInfo = allUserList.get(i);
			
			if(userBaseInfo.getGrade() == 0)
				userList0.add(userBaseInfo);
			else if(userBaseInfo.getGrade() == 1)
				userList1.add(userBaseInfo);
			else if(userBaseInfo.getGrade() ==2)
				userList2.add(userBaseInfo);
			else if(userBaseInfo.getGrade() == 3)
				userList3.add(userBaseInfo);
			else if(userBaseInfo.getGrade() == 4)
				userList4.add(userBaseInfo);
		}
	}
	
	/**
	 * 重新取工作日志列表
	 */
	public static void updateLogList()
	{
		logList = Login.person.getLogList();
	}
	
	
	
	/**
	 * 在全局地址簿中找对应组名和联系人用户名的Address对象
	 * @param groupName 组名
	 * @param username 联系人用户名
	 * @return Address （没找到时返回null）
	 */
	public static Address getGlobalAddress(String groupName, String username)
	{
		for(int i=0; i<globalAddressGroupList.size(); i++)
		{
			AddressGroup addressGroup = globalAddressGroupList.get(i);
			
			if(addressGroup.getGroupName().equals(groupName))
			{
				ArrayList<Address> addressList = addressGroup.getAddressList();
				
				for(int j=0; j<addressList.size(); j++)
				{
					Address address = addressList.get(j);
					
					if(address.getUsername().equals(username))
					{
						return address;  //找到了
					}
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 在私有地址簿中找对应组名和联系人用户名的Address对象
	 * @param groupName 组名
	 * @param username 联系人用户名
	 * @return Address （没找到时返回null）
	 */
	public static Address getPrivateAddress(String groupName, String username)
	{
		for(int i=0; i<privateAddressGroupList.size(); i++)
		{
			AddressGroup addressGroup = privateAddressGroupList.get(i);
			
			if(addressGroup.getGroupName().equals(groupName))
			{
				ArrayList<Address> addressList = addressGroup.getAddressList();
				
				for(int j=0; j<addressList.size(); j++)
				{
					Address address = addressList.get(j);
					
					if(address.getUsername().equals(username))
					{
						return address;  //找到了
					}
				}
			}
		}
		return null;
	}
	
}
