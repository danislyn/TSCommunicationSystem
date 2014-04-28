package managers.information;

import java.util.ArrayList;

import client.login.Login;

import server.reflection.Call;

import beans.information.TeacherInfo;
import beans.teacher.Schedule;


public class  TeacherInfoManager {
	
	private ArrayList<TeacherInfo> teacherInfoList;
	
	
	public TeacherInfoManager() 
	{
		update();
	}
	
	
	/** 更新方法， 更新teacherInfoList
	 * 
	 * @return true
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.InformationOperationInterface", "getAllTeacherInfoList",
							new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		teacherInfoList = (ArrayList<TeacherInfo>) Login.resultCall.getResult();
		
		return true;
	}
	
	
	/** 更新某个老师的日程
	 * （如果username不在list中，返回null）
	 * @param username 老师用户名
	 * @return TeacherInfo
	 */
	@SuppressWarnings("unchecked")
	public TeacherInfo updateSchedule(String username)
	{
		Call call = new Call("server.interfaces.TeacherOperationInterface", "getScheduleList",
							new Class[]{String.class}, new Object[]{username});
		Login.writeCall(call);
		Login.readCall();
		
		ArrayList<Schedule> schedules = (ArrayList<Schedule>) Login.resultCall.getResult();
		
		//update
		int index = getIndexOfTeacher(username);
		
		if(index != -1)
		{
			//update
			TeacherInfo teacherInfo = teacherInfoList.get(index);
			teacherInfo.setScheduleList(schedules);
			teacherInfoList.set(index, teacherInfo);
			
			return teacherInfo;
		}
		
		return null;
	}
	
	
	/** 获取所有老师信息列表（不需排序）
	 * 
	 * @return ArrayList<TeacherInfo>
	 */
	public ArrayList<TeacherInfo> showList()
	{
		return teacherInfoList;
	}
	
/*	private void sortByName()
	{
		
	}*/
	
	private int getIndexOfTeacher(String username)
	{
		for(int i=0; i<teacherInfoList.size(); i++)
		{
			if(teacherInfoList.get(i).getInformation().getUsername().equals(username) == true)
				return i;
		}
		return -1;
	}
	
}

