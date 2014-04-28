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
	
	
	/** ���췽��
	 * 
	 * @param username �û�����һ�����룬���ɸı�
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
		//����ȫ�ֵ�ַ�����ᾭ���仯��ֻ���ȡһ��
		Call call = new Call("server.interfaces.MailOperationInterface", "getGlobalAddressGroupList", new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		globalAddressGroupList = (ArrayList<AddressGroup>) Login.resultCall.getResult();
		
		sortByGroupName(globalAddressGroupList);
	}*/
	
	
	/** ���·���������addressGroupList
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
	
	
	/** ɾ��ĳ����
	 *  ǰ�����������鲻����
	 * @param groupName ����
	 * @return boolean
	 */
	public boolean deleteGroup(String groupName)
	{
		Call call = new Call("server.interfaces.MailOperationInterface", "deleteGroup",
							new Class[]{String.class, String.class}, new Object[]{username, groupName});
		Login.writeCall(call);
		Login.readCall();
		
		//��������ɹ�
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			for(int i=0; i<privateAddressGroupList.size(); i++)
			{
				AddressGroup addressGroup = privateAddressGroupList.get(i);
				if(addressGroup.getGroupName().equals(groupName))
				{
					privateAddressGroupList.remove(addressGroup);
					break;  //�Ƿ�������groupName��ͬ???NO
				}
			}
			
			return true;
		}
		return false;
	}
	
	
	/** ��������ӵ�ַ
	 *  �������������ڣ����¿�һ����
	 * @param groupName ����
	 * @param addressList ��ַ�б�
	 * @return boolean
	 */
	public boolean addGroupAddress(String groupName, ArrayList<Address> addressList)
	{
		//��ȡ����ַ�б��е�username
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
		
		//��������ɹ�
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			//���groupName�Ƿ��Ѵ��ڣ������ڣ���ֱ�Ӽӵ�������ȥ
			for(int i=0; i<privateAddressGroupList.size(); i++)
			{
				AddressGroup addressGroup = privateAddressGroupList.get(i);
				if(addressGroup.getGroupName().equals(groupName))
				{					
					addressGroup.addAddress(addressList);
					return true;  //��ӳɹ���ֱ�ӷ���
				}
			}
			
			//���groupName�����ڣ��¿�һ����
			AddressGroup newAddressGroup = new AddressGroup();
			newAddressGroup.setGroupName(groupName);
			newAddressGroup.setAddressList(addressList);
			
			privateAddressGroupList.add(newAddressGroup);
			return true;
		}
		
		return false;
	}
	
	
	/** ������ɾ����ַ
	 * 
	 * @param groupName ����
	 * @param addressList ��ַ�б�
	 * @return boolean
	 */
	public boolean deleteGroupAddress(String groupName, ArrayList<Address> addressList)
	{
		//��ȡ����ַ�б��е�username
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
		
		//��������ɹ�
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
	
	
	/** ��ȡ˽�е�ַ���б������򣬰������������ڰ��û�������
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
	
	
	/** ��ȡȫ�ֵ�ַ���б������򣬰������������ڰ��û�������
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
		//��������
		for(int i=0; i<addressGroupList.size(); i++)
		{
			AddressGroup addressGroup = addressGroupList.get(i);
			ArrayList<Address> addressList = addressGroup.getAddressList();
			
			//��addressList��username����
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
			
			addressGroup.setAddressList(addressList);		
		}
		
		//��������
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
									
							int larger = 0;  //��һ���ȵڶ��� �� 1��ʾ����  0��ʾ����  -1��ʾС�ڣ�
									
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
