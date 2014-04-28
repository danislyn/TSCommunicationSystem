package client.users;

import java.util.ArrayList;

import client.login.Login;

import server.reflection.Call;

import beans.information.Information;

public class MajorTeacher extends Teacher {

	
	/**
	 * ���췽��
	 * @param username
	 * @param password
	 * @param authority
	 * @param location
	 */
	public MajorTeacher(String username, String password, int authority,int location) 
	{
		super(username, password, authority, location);
		
	}
	
	
	/** �����ʺ�����Ŀ��ѧ��
	 * 
	 * @param adeptnessNumList Ҫ���س�����б�
	 * @return ArrayList<Information>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Information> searchSuitableStudent(ArrayList<Integer> adeptnessNumList)
	{
		ArrayList<Information> resultList = new ArrayList<Information>();
		
		Call call = new Call("server.interfaces.InformationOperationInterface", "getSuitableStudentList", 
							new Class[]{String.class, ArrayList.class}, new Object[]{username, adeptnessNumList});
		Login.writeCall(call);
		Login.readCall();
		
		resultList = (ArrayList<Information>) Login.resultCall.getResult();
		
		return resultList;
	}
	
}
