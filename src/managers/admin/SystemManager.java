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
	
	
	/** 构造方法
	 * 
	 * @param username 管理员用户名，一旦传入，不可修改
	 */
	public SystemManager(String username) 
	{
		super(username);
		update();
	}
	
	
	/** 更新方法，更新allAnnouncementList
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
	
	
	/** 将帖子置顶
	 * 
	 * @param tag 板块标号
	 * @param post 帖子
	 * @return boolean
	 */
	public boolean makePostTop(int tag, Post post)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "makePostTop", 
							new Class[]{int.class, String.class, Calendar.class},
							new Object[]{tag, post.getSenderUsername(), post.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		saveLog("置顶了" + post.getSenderUsername() + "的帖子" + post.getTitle());

		return (Boolean)Login.resultCall.getResult();
	}
	
	
	/** 取消帖子置顶
	 * 
	 * @param tag 板块标号
	 * @param post 帖子
	 * @return boolean
	 */
	public boolean cancelPostTop(int tag, Post post)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "cancelPostTop", 
							new Class[]{int.class, String.class, Calendar.class},
							new Object[]{tag, post.getSenderUsername(), post.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		saveLog("取消置顶了" + post.getSenderUsername() + "的帖子" + post.getTitle());

		return (Boolean)Login.resultCall.getResult();
	}
	
	
	/** 删除帖子
	 * 
	 * @param tag 板块标号
	 * @param post 帖子
	 * @return boolean
	 */
	public boolean deletePost(int tag, Post post)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "deletePost", 
							new Class[]{int.class, String.class, Calendar.class},
							new Object[]{tag, post.getSenderUsername(), post.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		//如果删除成功了
		if((Boolean)Login.resultCall.getResult() == true)
		{
			saveLog("删除了" + post.getSenderUsername() + "的帖子" + post.getTitle());
			
			//通知行政人员
			Mail mail = new Mail();
			mail.setSenderUsername(username);
			mail.setTitle("删帖举报");
			mail.setContent("ID：" + post.getSenderUsername() + "    姓名：" + post.getSenderName());
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
	
	
	/** 删除某个帖子里的某条评论
	 * 
	 * @param tag 板块标号
	 * @param post 帖子
	 * @param comment 评论
	 * @return boolean
	 */
	public boolean deleteComment(int tag, Post post, Comment comment)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "deleteComment", 
							new Class[]{int.class, String.class, Calendar.class, String.class, Calendar.class},
							new Object[]{tag, post.getSenderUsername(), post.getTime(), comment.getSenderUsername(), comment.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		saveLog("删除了" + comment.getSenderUsername() + "对帖子" + post.getTitle() + "的评论");

		return (Boolean)Login.resultCall.getResult();
	}
	
	
	/** 删除公告
	 * 
	 * @param announcement 公告
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
			
			saveLog("删除了" + announcement.getSenderUsername() + "的公告" + announcement.getTitle());
			
			return true;
		}
		return false;
	}
	
	
	/** 获取所有公告列表（不需排序）
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
