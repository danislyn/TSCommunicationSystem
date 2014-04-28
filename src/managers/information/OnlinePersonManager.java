package managers.information;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import client.login.Login;

import server.reflection.Call;

import beans.information.OnlinePerson;


public class OnlinePersonManager {

	private ArrayList<OnlinePerson> onlinePersonList;
    
	
	/** 构造方法
	 * 
	 */
	public OnlinePersonManager() 
	{
		update();
	}
	
	
	/** 更新方法，更新Manager里的onlinePersonList
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
	
	
	/** 得到onlinePersonList
	 *  ，默认按username排序
	 * @return ArrayList<OnlinePerson>
	 */
	public ArrayList<OnlinePerson> showList()
	{
		sortByUsername();
		return onlinePersonList;
	}
	
	
    /**
     * 指定用户是否在线
     * @param username 指定用户名
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
     * 得到指定用户地理位置
     * @param username 指定用户名
     * @return int (-1不在线   1仙林   2鼓楼 )
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
									
							int larger = 0;  //第一个比第二个 （ 1表示大于  0表示等于  -1表示小于）
									
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
