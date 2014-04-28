package managers.mail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import server.reflection.Call;
import client.login.Login;

import beans.mail.Address;
import beans.mail.AddressGroup;


public class AddressManager {
	
	private ArrayList<AddressGroup> privateAddressGroupList;
	
	private ArrayList<AddressGroup> globalAddressGroupList;
	
	private final String username;
	
	
	/** 构造方法
	 * 
	 * @param username 用户名，一旦传入，不可改变
	 */
	public AddressManager(String username) 
	{
		this.username = username;
		update();
		
		//initGlobalList();
	}
	
/*	@SuppressWarnings("unchecked")
	private void initGlobalList()
	{
		//假设全局地址簿不会经常变化，只需获取一次
		Call call = new Call("server.interfaces.MailOperationInterface", "getGlobalAddressGroupList", new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		globalAddressGroupList = (ArrayList<AddressGroup>) Login.resultCall.getResult();
		
		sortByGroupName(globalAddressGroupList);
	}*/
	
	
	/** 更新方法，更新addressGroupList
	 * 
	 * @return true
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.MailOperationInterface", "getPrivateAddressGroupList",
							new Class[]{String.class}, new Object[]{username});
		Login.writeCall(call);
		Login.readCall();
		
		privateAddressGroupList = (ArrayList<AddressGroup>) Login.resultCall.getResult();
		
		return true;
	}
	
	
	/** 删除某分组
	 *  前置条件：分组不重名
	 * @param groupName 组名
	 * @return boolean
	 */
	public boolean deleteGroup(String groupName)
	{
		Call call = new Call("server.interfaces.MailOperationInterface", "deleteGroup",
							new Class[]{String.class, String.class}, new Object[]{username, groupName});
		Login.writeCall(call);
		Login.readCall();
		
		//如果操作成功
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			for(int i=0; i<privateAddressGroupList.size(); i++)
			{
				AddressGroup addressGroup = privateAddressGroupList.get(i);
				if(addressGroup.getGroupName().equals(groupName))
				{
					privateAddressGroupList.remove(addressGroup);
					break;  //是否有两组groupName相同???NO
				}
			}
			
			return true;
		}
		return false;
	}
	
	
	/** 向组中添加地址
	 *  若该组名不存在，则新开一个组
	 * @param groupName 组名
	 * @param addressList 地址列表
	 * @return boolean
	 */
	public boolean addGroupAddress(String groupName, ArrayList<Address> addressList)
	{
		//提取出地址列表中的username
		ArrayList<String> contactUsernameList = new ArrayList<String>();
		for(int i=0; i<addressList.size(); i++)
		{
			contactUsernameList.add(addressList.get(i).getUsername());
		}
		
		Call call = new Call("server.interfaces.MailOperationInterface", "addAddress",
							new Class[]{String.class, String.class, ArrayList.class}, 
							new Object[]{username, groupName, contactUsernameList});
		Login.writeCall(call);
		Login.readCall();
		
		//如果操作成功
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			//检查groupName是否已存在，若存在，则直接加到那组中去
			for(int i=0; i<privateAddressGroupList.size(); i++)
			{
				AddressGroup addressGroup = privateAddressGroupList.get(i);
				if(addressGroup.getGroupName().equals(groupName))
				{					
					addressGroup.addAddress(addressList);
					return true;  //添加成功，直接返回
				}
			}
			
			//如果groupName不存在，新开一个组
			AddressGroup newAddressGroup = new AddressGroup();
			newAddressGroup.setGroupName(groupName);
			newAddressGroup.setAddressList(addressList);
			
			privateAddressGroupList.add(newAddressGroup);
			return true;
		}
		
		return false;
	}
	
	
	/** 从组中删除地址
	 * 
	 * @param groupName 组名
	 * @param addressList 地址列表
	 * @return boolean
	 */
	public boolean deleteGroupAddress(String groupName, ArrayList<Address> addressList)
	{
		//提取出地址列表中的username
		ArrayList<String> contactUsernameList = new ArrayList<String>();
		for(int i=0; i<addressList.size(); i++)
		{
			contactUsernameList.add(addressList.get(i).getUsername());
		}
		
		Call call = new Call("server.interfaces.MailOperationInterface", "deleteAddress",
							new Class[]{String.class, String.class, ArrayList.class}, 
							new Object[]{username, groupName, contactUsernameList});
		Login.writeCall(call);
		Login.readCall();
		
		//如果操作成功
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			for(int i=0; i<privateAddressGroupList.size(); i++)
			{
				AddressGroup addressGroup = privateAddressGroupList.get(i);
				if(addressGroup.getGroupName().equals(groupName))
				{
					addressGroup.deleteAddress(addressList);
				}
			}
			
			return true;
		}
		return false;
	}
	
	
	/** 获取私有地址簿列表（已排序，按组名排序，组内按用户名排序）
	 * 
	 * @return ArrayList<AddressGroup>
	 */
	public ArrayList<AddressGroup> showPrivateList()
	{
		sortByGroupName(privateAddressGroupList);
		return privateAddressGroupList;
	}
	
	public ArrayList<AddressGroup> getPrivateList()
	{
		return privateAddressGroupList;
	}
	
	
	/** 获取全局地址簿列表（已排序，按组名排序，组内按用户名排序）
	 * 
	 * @return ArrayList<AddressGroup>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<AddressGroup> getGlobalList()
	{
		Call call = new Call("server.interfaces.MailOperationInterface", "getGlobalAddressGroupList", new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		globalAddressGroupList = (ArrayList<AddressGroup>) Login.resultCall.getResult();
		
		sortByGroupName(globalAddressGroupList);
		
		return globalAddressGroupList;
	}

	
	private void sortByGroupName(ArrayList<AddressGroup> addressGroupList)
	{
		//组内排序
		for(int i=0; i<addressGroupList.size(); i++)
		{
			AddressGroup addressGroup = addressGroupList.get(i);
			ArrayList<Address> addressList = addressGroup.getAddressList();
			
			//对addressList按username增序
			Collections.sort(addressList, new Comparator<Address>() 
					{
						@Override
						public int compare(Address address1, Address address2) {
							// TODO Auto-generated method stub
							String username1 = address1.getUsername();
							String username2 = address2.getUsername();
							
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
			
			addressGroup.setAddressList(addressList);		
		}
		
		//组外排序
		Collections.sort(addressGroupList, new Comparator<AddressGroup>()
				{
					@Override
					public int compare(AddressGroup addressGroup1, AddressGroup addressGroup2) {
						// TODO Auto-generated method stub
						String groupName1 = addressGroup1.getGroupName();
						String groupName2 = addressGroup2.getGroupName();
						
						if(groupName1.equals(groupName2))
							return 0;
						else
						{					
							int len1 = groupName1.length();
							int len2 = groupName2.length();
									
							int larger = 0;  //第一个比第二个 （ 1表示大于  0表示等于  -1表示小于）
									
							for(int i=0; i<len1 && i<len2; i++)
							{
								if(groupName1.charAt(i) > groupName2.charAt(i))
								{
									larger = 1;
									break;
								}
								else if(groupName1.charAt(i) < groupName2.charAt(i))
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
	
	//private int getIndexOf
	
}
