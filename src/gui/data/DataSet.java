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
	//public static ArrayList<Post> allPost;//��������
	public static ArrayList<Post> postList1;//ѧ������
	public static ArrayList<Post> postList2;//�ο���ʦ����
	public static ArrayList<Post> postList3;//����Ա����
	public static ArrayList<Post> postList4;//����Ա����
	//////////////////////////////////
	
	//file
	public static ArrayList<FileBaseInfo> fileList;
	///////////////
	
	
	//����Ա�õ���
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
		
		
		//ֻ��ȡһ��
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
	 * ����ȡ�ռ����б�ֻ�Ǵ�person��ȡ��
	 */
	public static void updateReceivedMailList()
	{
		receivedMailList = Login.person.getReceivedMailList();
	}
	
	/**
	 * ����ȡ�������б�
	 */
	public static void updateSentMailList()
	{
		sentMailList = Login.person.getSentMailList();
	}
	
	/**
	 * ����ȡȫ�ֵ�ַ��
	 */
	public static void updateGlobalAddressGroupList()
	{	
		globalAddressGroupList = Login.person.getGlobalAddressGroupList();
	}
	
	/**
	 * ����ȡ˽�е�ַ��
	 */
	public static void updatePrivateAddressGroupList()
	{
		
		privateAddressGroupList = Login.person.getPrivateAddressGroupList();
	}
	
	/**
	 * ����ȡ���а�������б�
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
	 * ����ȡ�����ļ��б�
	 */
	public static void updateFileList()
	{
		fileList = Login.person.getFileList();
	}

	/**
	 * ����ȡ�����б�
	 */
	public static void updateAnnouncementList()
	{
		announcementList = Login.person.getAnnouncementList();
	}
	
	/**
	 * ����ȡ�ѷ������б�
	 */
	public static void updateSentAnnouncementList()
	{
		sentAnnouncementList = Login.person.getSentAnnouncementList();
	}

	/**
	 * ����ȡ�����û��б�
	 */
	public static void updateAllUserList()
	{
		allUserList = Login.person.getAllUserList();
		
		userList0.clear();
		userList1.clear();
		userList2.clear();
		userList3.clear();
		userList4.clear();
		
		//����
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
	 * ����ȡ������־�б�
	 */
	public static void updateLogList()
	{
		logList = Login.person.getLogList();
	}
	
	
	
	/**
	 * ��ȫ�ֵ�ַ�����Ҷ�Ӧ��������ϵ���û�����Address����
	 * @param groupName ����
	 * @param username ��ϵ���û���
	 * @return Address ��û�ҵ�ʱ����null��
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
						return address;  //�ҵ���
					}
				}
			}
		}
		return null;
	}
	
	
	/**
	 * ��˽�е�ַ�����Ҷ�Ӧ��������ϵ���û�����Address����
	 * @param groupName ����
	 * @param username ��ϵ���û���
	 * @return Address ��û�ҵ�ʱ����null��
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
						return address;  //�ҵ���
					}
				}
			}
		}
		return null;
	}
	
}
