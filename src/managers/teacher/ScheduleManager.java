package managers.teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import client.login.Login;

import server.reflection.Call;

import beans.teacher.Schedule;


public class ScheduleManager {
	
	private ArrayList<Schedule> scheduleList;
	
	private final String username;
	
	
	/** 构造方法
	 * 
	 * @param username 用户名，一旦传入，不可修改
	 */
	public ScheduleManager(String username) 
	{
		this.username = username;
		update();
	}
	
	
	/** 更新方法，更新scheduleList
	 * 
	 * @return true;
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.TeacherOperationInterface", "getScheduleList",
							new Class[]{String.class}, new Object[]{username});
		Login.writeCall(call);
		Login.readCall();
		
		scheduleList = (ArrayList<Schedule>) Login.resultCall.getResult();
		
		return true;
	}
	
	
	/** 保存日程
	 *  包括了新增和修改，如果修改成空内容，那相当于删除
	 * @param schedule 日程
	 * @return boolean
	 */
	public boolean saveSchedule(Schedule schedule)
	{
		int year = schedule.getYear();
		int month = schedule.getMonth();
		int day = schedule.getDay();
		String content = schedule.getContent();
		
		int index = checkScheduleExist(year, month, day);
		
		//如果日程不存在，相当于新增
		if(index == -1)
		{
			//如果内容为空
			if(content == null || content.length() == 0)
			{
				//什么都不干
				return false;
			}
			//内容不为空
			else 
			{
				//新增
				Call call = new Call("server.interfaces.TeacherOperationInterface", "addSchedule",
									new Class[]{String.class, Schedule.class}, new Object[]{username, schedule});
				Login.writeCall(call);
				Login.readCall();
				
				if((Boolean)Login.resultCall.getResult() == true)
				{
					//update
					scheduleList.add(schedule);				
					return true;
				}
				return false;		
			}
			
		}
		//日程存在，相当于修改
		else 
		{
			//如果内容为空
			if(content == null || content.length() == 0)
			{
				//删除
				Call call = new Call("server.interfaces.TeacherOperationInterface", "deleteSchedule",
									new Class[]{String.class, int.class, int.class, int.class}, 
									new Object[]{username, year, month, day});
				
				Login.writeCall(call);
				Login.readCall();
				
				if((Boolean)Login.resultCall.getResult() == true)
				{
					//update					
					scheduleList.remove(index);
					return true;
				}
				return false;	
			}
			//内容不为空
			else 
			{
				//修改
				Call call = new Call("server.interfaces.TeacherOperationInterface", "reviseSchedule",
									new Class[]{String.class, Schedule.class}, new Object[]{username, schedule});
				Login.writeCall(call);
				Login.readCall();
				
				if((Boolean)Login.resultCall.getResult() == true)
				{
					//update
					scheduleList.set(index, schedule);
					return true;
				}
				return false;
			}
			
		}
		
	}
	
	
	/** 删除日程
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return boolean
	 */
	public boolean deleteSchedule(int year, int month, int day)
	{
		int index = checkScheduleExist(year, month, day);
		
		//保证改日程存在
		if(index == -1)
			return false;
		
		Call call = new Call("server.interfaces.TeacherOperationInterface", "deleteSchedule",
							new Class[]{String.class, int.class, int.class, int.class}, 
							new Object[]{username, year, month, day});
		Login.writeCall(call);
		Login.readCall();
		
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			scheduleList.remove(index);
			return true;
		}
		return false;
	}
	
	
	/** 获取日程列表（已排序）
	 * 
	 * @return ArrayList<Schedule>
	 */
	public ArrayList<Schedule> showList()
	{
		sortByTime(scheduleList);
		return scheduleList;
	}
	
	public ArrayList<Schedule> getList()
	{
		return scheduleList;
	}
	
	
	/** 获取指定年份和月份里的日程
	 * 
	 * @param year
	 * @param month
	 * @return ArrayList<Schedule>
	 */
	public ArrayList<Schedule> searchByYearAndMonth(int year, int month)
	{
		ArrayList<Schedule> searchList = new ArrayList<Schedule>();
		
		for(int i=0; i<scheduleList.size(); i++)
		{
			Schedule schedule = scheduleList.get(i);
			
			if(schedule.getYear() == year && schedule.getMonth() == month)
			{
				searchList.add(schedule);
			}
		}
		
		sortByTime(searchList);
		return searchList;
	}
	
		
	private void sortByTime(ArrayList<Schedule> schedules)
	{
		Collections.sort(schedules, new Comparator<Schedule>()
				{
					@Override
					public int compare(Schedule schedule1, Schedule schedule2) {
						// TODO Auto-generated method stub
						
						int[] time1 = new int[]{schedule1.getYear(), schedule1.getMonth(), schedule1.getDay()};
						int[] time2 = new int[]{schedule2.getYear(), schedule2.getMonth(), schedule2.getDay()};
						
						for(int i=0; i<3; i++)
						{
							if(time1[i] > time2[i])
								return 1;
							else if(time1[i] < time2[i])
								return -1;
							else  //如果相等
							{
								if(i == 2)
									return 0;
							}
							
						}
						return 0;
					}
				});
	}

	/** 返回在list中的index */
	private int checkScheduleExist(int year, int month, int day)
	{
		for(int i=0; i<scheduleList.size(); i++)
		{
			Schedule schedule = scheduleList.get(i);
			
			if(year == schedule.getYear() && month == schedule.getMonth() && day == schedule.getDay())
			{
				return i;
			}		
		}
		return -1;
	}
	
	
}
