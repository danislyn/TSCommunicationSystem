package managers.admin;

import beans.announcement.Announcement;
import beans.mail.Mail;
import beans.post.Comment;
import beans.post.Post;

import java.util.ArrayList;
import java.util.Calendar;

import server.reflection.Call;
import client.login.Login;


public class SystemManager extends LogManager {
	
	private ArrayList<Announcement> allAnnouncementList;
	
	
	/** ���췽��
	 * 
	 * @param username ����Ա�û�����һ�����룬�����޸�
	 */
	public SystemManager(String username) 
	{
		super(username);
		update();
	}
	
	
	/** ���·���������allAnnouncementList
	 * 
	 * @return true
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "getAllAnnouncementList", 
							new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		allAnnouncementList = (ArrayList<Announcement>) Login.resultCall.getResult();
		
		return true;
	}
	
	
	/** �������ö�
	 * 
	 * @param tag �����
	 * @param post ����
	 * @return boolean
	 */
	public boolean makePostTop(int tag, Post post)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "makePostTop", 
							new Class[]{int.class, String.class, Calendar.class},
							new Object[]{tag, post.getSenderUsername(), post.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		saveLog("�ö���" + post.getSenderUsername() + "������" + post.getTitle());

		return (Boolean)Login.resultCall.getResult();
	}
	
	
	/** ȡ�������ö�
	 * 
	 * @param tag �����
	 * @param post ����
	 * @return boolean
	 */
	public boolean cancelPostTop(int tag, Post post)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "cancelPostTop", 
							new Class[]{int.class, String.class, Calendar.class},
							new Object[]{tag, post.getSenderUsername(), post.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		saveLog("ȡ���ö���" + post.getSenderUsername() + "������" + post.getTitle());

		return (Boolean)Login.resultCall.getResult();
	}
	
	
	/** ɾ������
	 * 
	 * @param tag �����
	 * @param post ����
	 * @return boolean
	 */
	public boolean deletePost(int tag, Post post)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "deletePost", 
							new Class[]{int.class, String.class, Calendar.class},
							new Object[]{tag, post.getSenderUsername(), post.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		//���ɾ���ɹ���
		if((Boolean)Login.resultCall.getResult() == true)
		{
			saveLog("ɾ����" + post.getSenderUsername() + "������" + post.getTitle());
			
			//֪ͨ������Ա
			Mail mail = new Mail();
			mail.setSenderUsername(username);
			mail.setTitle("ɾ���ٱ�");
			mail.setContent("ID��" + post.getSenderUsername() + "    ������" + post.getSenderName());
			mail.setIsNeedFeedback(false);
			
			ArrayList<String> receiverList = new ArrayList<String>();
			receiverList.add("004");  //????????

			call = new Call("server.interfaces.MailOperationInterface", "sendMail",
							new Class[]{String.class, ArrayList.class, Mail.class}, 
							new Object[]{username, receiverList, mail});
			Login.writeCall(call);
			Login.readCall();
			
			return true;
		}	
		return false;
	}
	
	
/*	public ArrayList<Post> showSuspiciousPost(int tag){
		return null;
	}*/
	
	
	/** ɾ��ĳ���������ĳ������
	 * 
	 * @param tag �����
	 * @param post ����
	 * @param comment ����
	 * @return boolean
	 */
	public boolean deleteComment(int tag, Post post, Comment comment)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "deleteComment", 
							new Class[]{int.class, String.class, Calendar.class, String.class, Calendar.class},
							new Object[]{tag, post.getSenderUsername(), post.getTime(), comment.getSenderUsername(), comment.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		saveLog("ɾ����" + comment.getSenderUsername() + "������" + post.getTitle() + "������");

		return (Boolean)Login.resultCall.getResult();
	}
	
	
	/** ɾ������
	 * 
	 * @param announcement ����
	 * @return boolean
	 */
	public boolean deleteAnnouncement(Announcement announcement)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "deleteAnnouncement", 
							new Class[]{String.class, Calendar.class},
							new Object[]{announcement.getSenderUsername(), announcement.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			int index = getIndexOfAnnouncement(announcement);
			
			if(index != -1)
			{
				allAnnouncementList.remove(index);
			}
			
			saveLog("ɾ����" + announcement.getSenderUsername() + "�Ĺ���" + announcement.getTitle());
			
			return true;
		}
		return false;
	}
	
	
	/** ��ȡ���й����б���������
	 * 
	 * @return ArrayList<Announcement>
	 */
	public ArrayList<Announcement> showAnnouncementList()
	{
		return allAnnouncementList;
	}
	
/*	private void sortByTime(){
		
	}*/
	
	private int getIndexOfAnnouncement(Announcement announcement)
	{
		for(int i=0; i<allAnnouncementList.size(); i++)
		{
			Announcement announcement2 = allAnnouncementList.get(i);
			
			if(announcement2.getSenderUsername().equals(announcement.getSenderUsername()) && announcement2.getTime().equals(announcement.getTime()))
				return i;
		}
		return -1;
	}

}
