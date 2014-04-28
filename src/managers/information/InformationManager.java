package managers.information;

import java.util.ArrayList;

import client.login.Login;
import server.reflection.Call;
import beans.information.Information;


public class InformationManager {
	
	private Information information;
	
	private final String username;
	
	private ArrayList<String> innerHobbyList;
	private ArrayList<String> innerAdeptnessList;
	
	
	/** 构造方法
	 * 
	 * @param username 一旦传入，不可改变
	 */
	public InformationManager(String username) 
	{
		this.username = username;
		update();
	}
	
	
	/** 更新方法，更新Manager里的information
	 * 
	 * @return true
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.InformationOperationInterface", "getPersonalInformation", 
							new Class[]{String.class}, new Object[]{username});
		Login.writeCall(call);
		Login.readCall();
		
		information = (Information) Login.resultCall.getResult();
		
		//获取内置兴趣表
		call = new Call("server.interfaces.InformationOperationInterface", "getHobbyDefinitionList", 
						new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		innerHobbyList = (ArrayList<String>)Login.resultCall.getResult();
		
		//获取内置特长表
		call = new Call("server.interfaces.InformationOperationInterface", "getAdeptnessDefinitionList", 
						new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		innerAdeptnessList = (ArrayList<String>)Login.resultCall.getResult();
		
		return true;
	}
	
	
	/** 修改个人信息
	 * 
	 * @param info 已set过的Information对象
	 * @return boolean
	 */
	public boolean reviseInformation(Information info)
	{
		Call call = new Call("server.interfaces.InformationOperationInterface", "revisePersonalInformation", 
							new Class[]{String.class, Information.class}, new Object[]{username, info});
		Login.writeCall(call);
		Login.readCall();
		
		//update
		information = info;
		
		return (Boolean)Login.resultCall.getResult();
	}
	
	
	/** 得到我的个人信息
	 * 
	 * @return Information
	 */
	public Information showInformation()
	{		
		return information;
	}
	
	
	/** 得到别人的个人信息
	 * 
	 * @param otherUsername 别人的username
	 * @return Information
	 */
	public Information showOtherInformation(String otherUsername)
	{
		Call call = new Call("server.interfaces.InformationOperationInterface", "getPersonalInformation", 
							new Class[]{String.class}, new Object[]{otherUsername});
		Login.writeCall(call);
		Login.readCall();
		
		return (Information)Login.resultCall.getResult();
	}
	
	
	/** 得到内置的兴趣列表
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getInnerHobbyList()
	{
		return innerHobbyList;
	}

	
	/** 得到内置的特长列表
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getInnerAdeptnessList()
	{
		return innerAdeptnessList;
	}
	
}
