package client.users;

import java.util.ArrayList;

import beans.announcement.Announcement;
import beans.announcement.FeedbackAnnouncement;
import beans.teacher.Schedule;
import managers.announcement.SentAnnouncementManager;
import managers.teacher.ScheduleManager;

public class Teacher extends Person {

	private SentAnnouncementManager sentAnnouncementManager;
	
	private ScheduleManager scheduleManager;
	
	
	/** 
	 * ���췽��
	 * @param username �û�����һ�����룬�����޸�
	 * @param password ����
	 * @param authority Ȩ�ޣ�һ�����룬�����޸�
	 * @param location ����λ��
	 */
	public Teacher(String username, String password, int authority, int location) 
	{
		super(username, password, authority, location);
		
		sentAnnouncementManager = new SentAnnouncementManager(username);
		scheduleManager = new ScheduleManager(username);
	}
	
	
	//================================================================================= 
	/** ����SentAnnouncementManager
	 * 
	 */
	public void updateSentAnnouncementManager()
	{
		sentAnnouncementManager.update();
	}
	
	/** ����ScheduleManager
	 * 
	 */
	public void updateScheduleManager()
	{
		scheduleManager.update();
	}
	
	
	//================================================================================= 
	/** ������
	 * 
	 * @param announcement ����
	 * @return boolean
	 */
	public boolean sendAnnouncement(Announcement announcement)
	{
		return sentAnnouncementManager.sendAnnouncement(announcement);
	}
	
	
	/** �޸Ĺ���
	 *  ǰ������������������new�����ģ�����time����ԭ����time
	 * @param announcement �޸ĺ�Ĺ���
	 * @return boolean
	 */
	public boolean reviseAnnouncement(FeedbackAnnouncement announcement)
	{
		return sentAnnouncementManager.reviseAnnouncement(announcement);
	}
	
	
	/** ��ȡ�ѷ������б������� */
	public ArrayList<FeedbackAnnouncement> getSentAnnouncementList()
	{
		return sentAnnouncementManager.showList();
	}
	
	
	//================================================================================= 
	/** �����ճ̣�������水ťʱ���ã�
	 *  �������������޸ģ�����޸ĳɿ����ݣ����൱��ɾ��
	 * @param schedule �ճ�
	 * @return boolean
	 */
	public boolean saveSchedule(Schedule schedule)
	{
		return scheduleManager.saveSchedule(schedule);
	}
	
	
	/** ɾ���ճ̣����ɾ����ťʱ���ã�
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return boolean
	 */
	public boolean deleteSchedule(int year, int month, int day)
	{
		return scheduleManager.deleteSchedule(year, month, day);
	}
	
	
	/** ��ȡ�ҵ������ճ��б�������
	 * 
	 * @return ArrayList<Schedule>
	 */
	public ArrayList<Schedule> getAllScheduleList()
	{
		return scheduleManager.showList();
	}
	
	
	/** ��ȡ��ĳ��ĳ�µ��ճ��б�������
	 * 
	 * @return ArrayList<Schedule>
	 */
	public ArrayList<Schedule> getScheduleListByYearAndMonth(int year, int month)
	{
		return scheduleManager.searchByYearAndMonth(year, month);
	}
	
	

}
