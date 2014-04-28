package managers.admin;

import beans.admin.Log;

import java.util.ArrayList;

import client.login.Login;

import server.reflection.Call;


public class LogManager {
	
	private ArrayList<Log> logList; 
	
	protected final String username;
	
	
	/** 构造方法
	 * 
	 * @param username 管理员用户名，一旦传入，不可修改
	 */
	public LogManager(String username) 
	{
		this.username = username;
		update();
	}
	
	
	/** 更新方法，更新logList
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
		
		//sort list反转
		for(int i=0; i<logList.size()/2; i++)
		{
			Log tempLog = logList.get(i);
			logList.set(i, logList.get(logList.size()-1-i));
			logList.set(logList.size()-1-i, tempLog);
		}
		
		return true;
	}
	
	
	/** 记录工作日志
	 *  （Manager里的logList不更新）
	 * @param description 操作日志
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
	
	
	/** 获取所有日志列表（已更新，已排序）
	 * 
	 * @return ArrayList<Log>
	 */
	public ArrayList<Log> showLogList()
	{
		update();
		return logList;
	}
	
	/** 获取所有日志列表（不更新）
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
