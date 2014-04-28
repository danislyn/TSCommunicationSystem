package managers.information;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import client.login.Login;

import server.reflection.Call;

import beans.information.OnlinePerson;


public class OnlinePersonManager {

	private ArrayList<OnlinePerson> onlinePersonList;
    
	
	/** ���췽��
	 * 
	 */
	public OnlinePersonManager() 
	{
		update();
	}
	
	
	/** ���·���������Manager���onlinePersonList
	 * 
	 * @return true
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.InformationOperationInterface", "getOnlinePersonList", 
							new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		onlinePersonList = (ArrayList<OnlinePerson>) Login.resultCall.getResult();
		
		return true;
	}
	
	
	/** �õ�onlinePersonList
	 *  ��Ĭ�ϰ�username����
	 * @return ArrayList<OnlinePerson>
	 */
	public ArrayList<OnlinePerson> showList()
	{
		sortByUsername();
		return onlinePersonList;
	}
	
	
    /**
     * ָ���û��Ƿ�����
     * @param username ָ���û���
     * @return boolean
     */
	public boolean isUserOnline(String username)
	{
		for(int i=0; i<onlinePersonList.size(); i++)
		{
			if(onlinePersonList.get(i).getUsername().equals(username) == true)
				return true;
		}
		return false;
	}
	
	
    /**
     * �õ�ָ���û�����λ��
     * @param username ָ���û���
     * @return int (-1������   1����   2��¥ )
     */
    public int getUserLocation(String username)
    {
    	for(int i=0; i<onlinePersonList.size(); i++)
    	{
    		OnlinePerson onlinePerson = onlinePersonList.get(i);
    		
    		if(onlinePerson.getUsername().equals(username) == true)
	    		return onlinePerson.getLocation();
    	}
    	return -1;
    }
	
    
	private void sortByUsername()
	{
		Collections.sort(onlinePersonList, new Comparator<OnlinePerson>() 
				{
					@Override
					public int compare(OnlinePerson wp1, OnlinePerson wp2) {
						// TODO Auto-generated method stub
						String username1 = wp1.getUsername();
						String username2 = wp2.getUsername();
						
						if(username1.equals(username2))
							return 0;
						else
						{
							int len1 = username1.length();
							int len2 = username2.length();
									
							int larger = 0;  //��һ���ȵڶ��� �� 1��ʾ����  0��ʾ����  -1��ʾС�ڣ�
									
							for(int i=0; i<len1 && i<len2; i++)
							{
								if(username1.charAt(i) > username2.charAt(i))
								{
									larger = 1;
									break;
								}
								else if(username1.charAt(i) < username2.charAt(i))
								{
									larger = -1;
									break;
								}
								else
									;
							}
							if(larger == 0)
								larger = (len1 > len2) ? 1 : -1;
									
							return larger;
						}	
					}				
				});
	}
	
	/*private void sortByName(){
		
	}*/
	
}
