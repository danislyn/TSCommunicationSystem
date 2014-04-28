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
	
	
	/** ���췽��
	 * 
	 * @param username һ�����룬���ɸı�
	 */
	public InformationManager(String username) 
	{
		this.username = username;
		update();
	}
	
	
	/** ���·���������Manager���information
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
		
		//��ȡ������Ȥ��
		call = new Call("server.interfaces.InformationOperationInterface", "getHobbyDefinitionList", 
						new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		innerHobbyList = (ArrayList<String>)Login.resultCall.getResult();
		
		//��ȡ�����س���
		call = new Call("server.interfaces.InformationOperationInterface", "getAdeptnessDefinitionList", 
						new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		innerAdeptnessList = (ArrayList<String>)Login.resultCall.getResult();
		
		return true;
	}
	
	
	/** �޸ĸ�����Ϣ
	 * 
	 * @param info ��set����Information����
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
	
	
	/** �õ��ҵĸ�����Ϣ
	 * 
	 * @return Information
	 */
	public Information showInformation()
	{		
		return information;
	}
	
	
	/** �õ����˵ĸ�����Ϣ
	 * 
	 * @param otherUsername ���˵�username
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
	
	
	/** �õ����õ���Ȥ�б�
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getInnerHobbyList()
	{
		return innerHobbyList;
	}

	
	/** �õ����õ��س��б�
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getInnerAdeptnessList()
	{
		return innerAdeptnessList;
	}
	
}
