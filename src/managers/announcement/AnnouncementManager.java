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
	
	private final int grade;  //�꼶��ֻ�ɼ����������꼶�Ĺ���
	
	
	/** ���췽��
	 * 
	 * @param username �û�����һ���趨�����ɸı�
	 * @param grade �꼶��һ���趨�����ɸı�
	 */
	public AnnouncementManager(String username, int grade) 
	{
		this.username = username;
		this.grade = grade;
		unfeedbackSum = 0;
		update();
	}
	
	
	/** ���·���������announcementList
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
			//���δ����
			if(announcementList.get(i).getIsFeedback() == false)
			{
				unfeedbackSum++;
			}
		}
		
		return true;
	}
	
	
	/** ��������
	 *  ǰ��������announcement.getIsFeedback()==false
	 * @param announcement ����
	 * @return boolean
	 */
	public boolean feedbackAnnouncement(Announcement announcement)
	{
		Call call = new Call("server.interfaces.AnnouncementOperationInterface", "feedbackAnnouncement",
							new Class[]{String.class, String.class, Calendar.class}, 
							new Object[]{username, announcement.getSenderUsername(), announcement.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		//��������ɹ�
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
	
	
	/** ��ȡδ������������
	 * 
	 * @return int
	 */
	public int getUnfeedbackSum()
	{
		return unfeedbackSum;
	}
	
	
	/** ��ȡδ���������б�
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
	
	
	/** ��ȡ�����б�������
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
