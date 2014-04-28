package managers.announcement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import client.login.Login;

import server.reflection.Call;

import beans.announcement.Announcement;


public class AnnouncementManager {
	
	private ArrayList<Announcement> announcementList;
	
	private int unfeedbackSum;
	
	private final String username;
	
	private final int grade;  //年级，只可见发布给该年级的公告
	
	
	/** 构造方法
	 * 
	 * @param username 用户名，一旦设定，不可改变
	 * @param grade 年级，一旦设定，不可改变
	 */
	public AnnouncementManager(String username, int grade) 
	{
		this.username = username;
		this.grade = grade;
		unfeedbackSum = 0;
		update();
	}
	
	
	/** 更新方法，更新announcementList
	 * 
	 * @return true
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.AnnouncementOperationInterface", "getAnnouncementList",
							new Class[]{int.class, String.class}, new Object[]{grade, username});
		Login.writeCall(call);
		Login.readCall();
		
		announcementList = (ArrayList<Announcement>) Login.resultCall.getResult();
		
		//update unfeedbackSum
		for(int i=0; i<announcementList.size(); i++)
		{
			//如果未回馈
			if(announcementList.get(i).getIsFeedback() == false)
			{
				unfeedbackSum++;
			}
		}
		
		return true;
	}
	
	
	/** 回馈公告
	 *  前置条件：announcement.getIsFeedback()==false
	 * @param announcement 公告
	 * @return boolean
	 */
	public boolean feedbackAnnouncement(Announcement announcement)
	{
		Call call = new Call("server.interfaces.AnnouncementOperationInterface", "feedbackAnnouncement",
							new Class[]{String.class, String.class, Calendar.class}, 
							new Object[]{username, announcement.getSenderUsername(), announcement.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		//如果操作成功
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			int index = getIndexOfAnnouncement(announcement);
			announcement.setIsFeedback(true);
			
			if(index != -1)
			{
				announcementList.set(index, announcement);
				unfeedbackSum--;
			}
			return true;
		}
		return false;
	}
	
	
	/** 获取未回馈公告总数
	 * 
	 * @return int
	 */
	public int getUnfeedbackSum()
	{
		return unfeedbackSum;
	}
	
	
	/** 获取未回馈公告列表
	 * 
	 * @return ArrayList<Announcement>
	 */
	public ArrayList<Announcement> showUnfeedbackList()
	{
		ArrayList<Announcement> unfeedbackAnnouncementList = new ArrayList<Announcement>();
		
		for(int i=0; i<announcementList.size(); i++)
		{
			Announcement announcement = announcementList.get(i);
			if(announcement.getIsFeedback() == false)
			{
				unfeedbackAnnouncementList.add(announcement);
			}
		}
		return unfeedbackAnnouncementList;
	}
	
	
	/** 获取公告列表（已排序）
	 * 
	 * @return ArrayList<Announcement>
	 */
	public ArrayList<Announcement> showList()
	{
		sortByTime();
		return announcementList;
	}
	
	public ArrayList<Announcement> getList()
	{
		return announcementList;
	}
	
	private void sortByTime()
	{
		Collections.sort(announcementList, new Comparator<Announcement>()
				{
					@Override
					public int compare(Announcement announcement1, Announcement announcement2) {
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
	
/*    public void sortBySender(){
		
	}
    
    public void sortByUnfeedback(){
		
	}*/
	
	private int getIndexOfAnnouncement(Announcement announcement)
	{
		for(int i=0; i<announcementList.size(); i++)
		{
			Announcement announcement2 = announcementList.get(i);
			
			if(announcement2.getSenderUsername().equals(announcement.getSenderUsername()) && announcement2.getTime().equals(announcement.getTime()))
				return i;
		}
		return -1;
	}

}
