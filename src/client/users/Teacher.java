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
	 * 构造方法
	 * @param username 用户名，一旦传入，不可修改
	 * @param password 密码
	 * @param authority 权限，一旦传入，不可修改
	 * @param location 地理位置
	 */
	public Teacher(String username, String password, int authority, int location) 
	{
		super(username, password, authority, location);
		
		sentAnnouncementManager = new SentAnnouncementManager(username);
		scheduleManager = new ScheduleManager(username);
	}
	
	
	//================================================================================= 
	/** 更新SentAnnouncementManager
	 * 
	 */
	public void updateSentAnnouncementManager()
	{
		sentAnnouncementManager.update();
	}
	
	/** 更新ScheduleManager
	 * 
	 */
	public void updateScheduleManager()
	{
		scheduleManager.update();
	}
	
	
	//================================================================================= 
	/** 发公告
	 * 
	 * @param announcement 公告
	 * @return boolean
	 */
	public boolean sendAnnouncement(Announcement announcement)
	{
		return sentAnnouncementManager.sendAnnouncement(announcement);
	}
	
	
	/** 修改公告
	 *  前置条件：参数对象是new出来的，并且time等于原来的time
	 * @param announcement 修改后的公告
	 * @return boolean
	 */
	public boolean reviseAnnouncement(FeedbackAnnouncement announcement)
	{
		return sentAnnouncementManager.reviseAnnouncement(announcement);
	}
	
	
	/** 获取已发公告列表（已排序） */
	public ArrayList<FeedbackAnnouncement> getSentAnnouncementList()
	{
		return sentAnnouncementManager.showList();
	}
	
	
	//================================================================================= 
	/** 保存日程（点击保存按钮时调用）
	 *  包括了新增和修改，如果修改成空内容，那相当于删除
	 * @param schedule 日程
	 * @return boolean
	 */
	public boolean saveSchedule(Schedule schedule)
	{
		return scheduleManager.saveSchedule(schedule);
	}
	
	
	/** 删除日程（点击删除按钮时调用）
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
	
	
	/** 获取我的所有日程列表（已排序）
	 * 
	 * @return ArrayList<Schedule>
	 */
	public ArrayList<Schedule> getAllScheduleList()
	{
		return scheduleManager.showList();
	}
	
	
	/** 获取我某年某月的日程列表（已排序）
	 * 
	 * @return ArrayList<Schedule>
	 */
	public ArrayList<Schedule> getScheduleListByYearAndMonth(int year, int month)
	{
		return scheduleManager.searchByYearAndMonth(year, month);
	}
	
	

}
