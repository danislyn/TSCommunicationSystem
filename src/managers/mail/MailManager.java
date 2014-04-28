package managers.mail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import client.login.Login;

import server.reflection.Call;

import beans.mail.Mail;


public class MailManager {
	
	private ArrayList<Mail> receivedMailList;
	
	private int unreadSum;
	
	private final String username;
	
	
	/** ���췽��
	 * 
	 * @param username �û�����һ���趨�������޸�
	 */
	public MailManager(String username) 
	{
		this.username = username;
		update();
	}
	
	
	/** ���·���������receivedMailList
	 * 
	 * @return true
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.MailOperationInterface", "getReceivedMailList",
							new Class[]{String.class}, new Object[]{username});
		Login.writeCall(call);
		Login.readCall();
		
		receivedMailList = (ArrayList<Mail>) Login.resultCall.getResult();
		
		//update unreadSum
		unreadSum = 0;
		for(int i=0; i<receivedMailList.size(); i++)
		{
			if(receivedMailList.get(i).getIsRead() == false)
			{
				unreadSum++;
			}
		}
		
		return true;
	}
	
	
	/** ѡ���ռ����б����ż�
	 *  ����ż���������Ѹ��ż��ӵ�SentMailManager��ȥ?��Person������
	 * @param receiverList �ռ����û����б�
	 * @param mail �ż�
	 * @return Calendar ���ż������ݿ��д��time
	 */
	public Calendar sendMail(ArrayList<String> receiverList, Mail mail)
	{
		Call call = new Call("server.interfaces.MailOperationInterface", "sendMail",
							new Class[]{String.class, ArrayList.class, Mail.class}, 
							new Object[]{username, receiverList, mail});
		Login.writeCall(call);
		Login.readCall();
		
		return (Calendar)Login.resultCall.getResult();
	}
	
	
	/** ģ�����������ż������͸���Ȥ���س���ƥ����û�
	 *  ����ż���������Ѹ��ż��ӵ�SentMailManager��ȥ?��Person������
	 * @param hobbyNumList ��Ȥ����б�
	 * @param adeptnessNumList �س�����б�
	 * @param mail �ż�
	 * @return Calendar ���ż������ݿ��д��time
	 */
	public Calendar sendMail(ArrayList<Integer> hobbyNumList, ArrayList<Integer> adeptnessNumList, Mail mail)
	{
		Call call = new Call("server.interfaces.MailOperationInterface", "sendMail",
							new Class[]{String.class, ArrayList.class, ArrayList.class, Mail.class}, 
							new Object[]{username, hobbyNumList, adeptnessNumList, mail});
		Login.writeCall(call);
		Login.readCall();
	
		return (Calendar)Login.resultCall.getResult();
	}
	
	
	/** �ظ��ż�
	 *  ��ͬ�ڷ��ż���һ����
	 * @param receiver �ռ����û���
	 * @param mail ���ż�
	 * @return boolean
	 */
	public boolean replyMail(String receiver, Mail mail)
	{
		ArrayList<String> receiverList = new ArrayList<String>();
		receiverList.add(receiver);
		
		Call call = new Call("server.interfaces.MailOperationInterface", "sendMail",
							new Class[]{String.class, ArrayList.class, Mail.class}, 
							new Object[]{username, receiverList, mail});
		Login.writeCall(call);
		Login.readCall();

		Calendar resultCalendar = (Calendar)Login.resultCall.getResult();
		
		if(resultCalendar != null)
			return true;
		else
			return false;
	}
		
	
	/** ɾ���ռ����е��ż�
	 * @param mail �ż�
	 * @return boolean
	 */
	public boolean deleteMail(Mail mail)
	{
		Call call = new Call("server.interfaces.MailOperationInterface", "deleteReceivedMail",
							new Class[]{String.class, String.class, Calendar.class}, 
							new Object[]{username, mail.getSenderUsername(), mail.getTime()});
		Login.writeCall(call);
		Login.readCall();

		//update
		if((Boolean)Login.resultCall.getResult() == true)
		{
			int index = getIndexOfMail(mail);
			
			if(index != -1)
			{
				receivedMailList.remove(index);
			}
			
			return true;
		}		
		
		return false;
	}
	
	
	/** ���ż�����mail���isRead���tue
	 * ǰ��������mail.getIsRead()==false
	 * @param mail �ż�
	 * @return boolean
	 */
	public boolean readMail(Mail mail)
	{
		Call call = new Call("server.interfaces.MailOperationInterface", "readMail",
							new Class[]{String.class, String.class, Calendar.class}, 
							new Object[]{username, mail.getSenderUsername(), mail.getTime()});
		Login.writeCall(call);
		Login.readCall();

		//update
		if((Boolean)Login.resultCall.getResult() == true)
		{
			int index = getIndexOfMail(mail);
			mail.setIsRead(true);
			
			if(index != -1)
			{						
				receivedMailList.set(index, mail);
				unreadSum--;
			}
			
			return true;
		}
		
		return false;
	}
	
	
	/** �õ�δ���ż�����
	 * 
	 * @return int
	 */
	public int getUnreadSum()
	{
		return unreadSum;
	}
	
	
	/** �õ��ռ����б�������
	 * 
	 * @return ArrayList<Mail>
	 */
	public ArrayList<Mail> showList()
	{
		sortByTime();
		return receivedMailList;
	}
	
	public ArrayList<Mail> getList()
	{
		return receivedMailList;
	}
	
	private void sortByTime()
	{
		Collections.sort(receivedMailList, new Comparator<Mail>() 
				{
					@Override
					public int compare(Mail m1, Mail m2) {
						// TODO Auto-generated method stub
						Calendar time1 = m1.getTime();
						Calendar time2 = m2.getTime();
						
						if(time1.after(time2) == true)
						{
							return -1;
						}
						else if(time1.before(time2) == true)
						{
							return 1;
						}
						else 
						{
							return 0;
						}			
					}
				});
	}
	
	/*private void sortBySender()
	{
		
	}*/

	private int getIndexOfMail(Mail mail)
	{
		for(int i=0; i<receivedMailList.size(); i++)
		{
			Mail mail2 = receivedMailList.get(i);
			
			if(mail2.getSenderUsername().equals(mail.getSenderUsername()) && mail2.getTime().equals(mail.getTime()))
				return i;
		}
		return -1;
	}
	
	
	
	public int getMailSum()
	{
		return receivedMailList.size();
	}
	
	public void setMailList(ArrayList<Mail> mailList)
	{
		this.receivedMailList = mailList;
	}
	
}
