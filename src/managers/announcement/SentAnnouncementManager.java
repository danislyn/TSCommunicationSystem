package managers.announcement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import server.reflection.Call;
import client.login.Login;

import beans.announcement.Announcement;
import beans.announcement.FeedbackAnnouncement;


public class SentAnnouncementManager {
	
	private ArrayList<FeedbackAnnouncement> sentAnnouncementList;
	
	private final String username;
	
	
	/** 构造方法
	 * 
	 * @param username 用户名，一旦传入，不可改变
	 */
	public SentAnnouncementManager(String username) 
	{
		this.username = username;
		update();
	}
	
	
	/** 更新方法，更新sentAnnouncementList
	 * 
	 * @return true
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.AnnouncementOperationInterface", "getFeedbackAnnouncementList",
							new Class[]{String.class}, new Object[]{username});
		Login.writeCall(call);
		Login.readCall();
		
		sentAnnouncementList = (ArrayList<FeedbackAnnouncement>) Login.resultCall.getResult();
		
		return true;
	}
	
	
	/** 发公告
	 *  公告会被直接加入到已发公告列表中
	 * @param announcement 公告
	 * @return boolean
	 */
	public boolean sendAnnouncement(Announcement announcement)
	{
		Call call = new Call("server.interfaces.AnnouncementOperationInterface", "sendAnnouncement",
							new Class[]{String.class, Announcement.class}, new Object[]{username, announcement});
		Login.writeCall(call);
		Login.readCall();
		
		Calendar resultCalendar = (Calendar)Login.resultCall.getResult();
		//如果操作成功
		if(resultCalendar != null)
		{
			//update
			announcement.setTime(resultCalendar);
			
			FeedbackAnnouncement feedbackAnnouncement = new FeedbackAnnouncement();
			
			feedbackAnnouncement.setSenderUsername(announcement.getSenderUsername());
			feedbackAnnouncement.setSenderName(announcement.getSenderName());
			feedbackAnnouncement.setTitle(announcement.getTitle());
			feedbackAnnouncement.setTime(announcement.getTime());
			feedbackAnnouncement.setCategory(announcement.getCategory());
			feedbackAnnouncement.setGrade(announcement.getGrade());
			feedbackAnnouncement.setContent(announcement.getContent());
			
			sentAnnouncementList.add(feedbackAnnouncement);
			return true;
		}
		
		return false;
	}
	
	
	/** 修改公告
	 *  前置条件：不能修改已发公告的time
	 * @param announcement 公告
	 * @return boolean
	 */
	public boolean reviseAnnouncement(FeedbackAnnouncement announcement)
	{
		Call call = new Call("server.interfaces.AnnouncementOperationInterface", "reviseAnnouncement",
							new Class[]{String.class, Announcement.class}, new Object[]{username, announcement});
		Login.writeCall(call);
		Login.readCall();
		
		//如果操作成功
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update 怎么找到原来的index???? 假设time不变 	问题：announcement从未被new过，有可能参数对象就是list里的对象
			int index = getIndexOfAnnouncement(announcement);
			
			if(index != -1)
			{
				sentAnnouncementList.set(index, announcement);
			}
			return true;
		}
		
		return false;
	}
	
	
	/** 获取已发公告列表（已排序）
	 * 
	 * @return ArrayList<FeedbackAnnouncement>
	 */
	public ArrayList<FeedbackAnnouncement> showList()
	{
		sortByTime();
		return sentAnnouncementList;
	}
	
	public ArrayList<FeedbackAnnouncement> getList()
	{
		return sentAnnouncementList;
	}
	
	private void sortByTime()
	{
		Collections.sort(sentAnnouncementList, new Comparator<FeedbackAnnouncement>()
				{
					@Override
					public int compare(FeedbackAnnouncement announcement1, FeedbackAnnouncement announcement2) {
						// TODO Auto-generated method stub
						Calendar time1 = announcement1.getTime();
						Calendar time2 = announcement2.getTime();
						
						if(time1.after(time2) == true)
						{
							return -1;
						}
						else if(time1.before(time2) == true)
						{
							return 1;
						}
						else 
						{
							return 0;
						}			
					}
				});		
	}
	
/*	private void sortByFeedbackSum(){
		
	}*/
	
	private int getIndexOfAnnouncement(FeedbackAnnouncement announcement)
	{
		for(int i=0; i<sentAnnouncementList.size(); i++)
		{
			FeedbackAnnouncement announcement2 = sentAnnouncementList.get(i);
			
			if(announcement2.getSenderUsername().equals(announcement.getSenderUsername()) && announcement2.getTime().equals(announcement.getTime()))
				return i;
		}
		return -1;
	}

}
