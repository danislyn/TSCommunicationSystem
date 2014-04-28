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
	
	
	/** ���·����� ����teacherInfoList
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
	
	
	/** ����ĳ����ʦ���ճ�
	 * �����username����list�У�����null��
	 * @param username ��ʦ�û���
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
	
	
	/** ��ȡ������ʦ��Ϣ�б���������
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

