package managers.admin;

import beans.admin.Log;

import java.util.ArrayList;

import client.login.Login;

import server.reflection.Call;


public class LogManager {
	
	private ArrayList<Log> logList; 
	
	protected final String username;
	
	
	/** ���췽��
	 * 
	 * @param username ����Ա�û�����һ�����룬�����޸�
	 */
	public LogManager(String username) 
	{
		this.username = username;
		update();
	}
	
	
	/** ���·���������logList
	 * 
	 * @return true;
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "getLogList", 
							new Class[]{String.class}, new Object[]{username});
		Login.writeCall(call);
		Login.readCall();
		
		logList = (ArrayList<Log>) Login.resultCall.getResult();
		
		//sort list��ת
		for(int i=0; i<logList.size()/2; i++)
		{
			Log tempLog = logList.get(i);
			logList.set(i, logList.get(logList.size()-1-i));
			logList.set(logList.size()-1-i, tempLog);
		}
		
		return true;
	}
	
	
	/** ��¼������־
	 *  ��Manager���logList�����£�
	 * @param description ������־
	 * @return boolean
	 */
	public boolean saveLog(String description)
	{
		Call call = new Call("server.interfaces.AdminOperationInterface", "addLog", 
							new Class[]{String.class, String.class}, 
							new Object[]{username, description});
		Login.writeCall(call);
		Login.readCall();
		
		return (Boolean)Login.resultCall.getResult();
	}
	
	
	/** ��ȡ������־�б��Ѹ��£�������
	 * 
	 * @return ArrayList<Log>
	 */
	public ArrayList<Log> showLogList()
	{
		update();
		return logList;
	}
	
	/** ��ȡ������־�б������£�
	 * 
	 * @return ArrayList<Log>
	 */
	public ArrayList<Log> getLogList()
	{
		return logList;
	}
	
/*	private void sortByTime(){
		
	}*/
	
}
