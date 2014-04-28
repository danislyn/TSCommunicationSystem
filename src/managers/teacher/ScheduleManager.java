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
	
	
	/** ���췽��
	 * 
	 * @param username �û�����һ�����룬�����޸�
	 */
	public ScheduleManager(String username) 
	{
		this.username = username;
		update();
	}
	
	
	/** ���·���������scheduleList
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
	
	
	/** �����ճ�
	 *  �������������޸ģ�����޸ĳɿ����ݣ����൱��ɾ��
	 * @param schedule �ճ�
	 * @return boolean
	 */
	public boolean saveSchedule(Schedule schedule)
	{
		int year = schedule.getYear();
		int month = schedule.getMonth();
		int day = schedule.getDay();
		String content = schedule.getContent();
		
		int index = checkScheduleExist(year, month, day);
		
		//����ճ̲����ڣ��൱������
		if(index == -1)
		{
			//�������Ϊ��
			if(content == null || content.length() == 0)
			{
				//ʲô������
				return false;
			}
			//���ݲ�Ϊ��
			else 
			{
				//����
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
		//�ճ̴��ڣ��൱���޸�
		else 
		{
			//�������Ϊ��
			if(content == null || content.length() == 0)
			{
				//ɾ��
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
			//���ݲ�Ϊ��
			else 
			{
				//�޸�
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
	
	
	/** ɾ���ճ�
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return boolean
	 */
	public boolean deleteSchedule(int year, int month, int day)
	{
		int index = checkScheduleExist(year, month, day);
		
		//��֤���ճ̴���
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
	
	
	/** ��ȡ�ճ��б�������
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
	
	
	/** ��ȡָ����ݺ��·�����ճ�
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
							else  //������
							{
								if(i == 2)
									return 0;
							}
							
						}
						return 0;
					}
				});
	}

	/** ������list�е�index */
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
