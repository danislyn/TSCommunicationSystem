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
	
	
	/** ���췽��
	 * 
	 * @param username �û�����һ�����룬���ɸı�
	 */
	public SentAnnouncementManager(String username) 
	{
		this.username = username;
		update();
	}
	
	
	/** ���·���������sentAnnouncementList
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
	
	
	/** ������
	 *  ����ᱻֱ�Ӽ��뵽�ѷ������б���
	 * @param announcement ����
	 * @return boolean
	 */
	public boolean sendAnnouncement(Announcement announcement)
	{
		Call call = new Call("server.interfaces.AnnouncementOperationInterface", "sendAnnouncement",
							new Class[]{String.class, Announcement.class}, new Object[]{username, announcement});
		Login.writeCall(call);
		Login.readCall();
		
		Calendar resultCalendar = (Calendar)Login.resultCall.getResult();
		//��������ɹ�
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
	
	
	/** �޸Ĺ���
	 *  ǰ�������������޸��ѷ������time
	 * @param announcement ����
	 * @return boolean
	 */
	public boolean reviseAnnouncement(FeedbackAnnouncement announcement)
	{
		Call call = new Call("server.interfaces.AnnouncementOperationInterface", "reviseAnnouncement",
							new Class[]{String.class, Announcement.class}, new Object[]{username, announcement});
		Login.writeCall(call);
		Login.readCall();
		
		//��������ɹ�
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update ��ô�ҵ�ԭ����index???? ����time���� 	���⣺announcement��δ��new�����п��ܲ����������list��Ķ���
			int index = getIndexOfAnnouncement(announcement);
			
			if(index != -1)
			{
				sentAnnouncementList.set(index, announcement);
			}
			return true;
		}
		
		return false;
	}
	
	
	/** ��ȡ�ѷ������б�������
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
