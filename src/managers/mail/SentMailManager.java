package managers.mail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import server.reflection.Call;
import client.login.Login;

import beans.mail.FeedbackMail;
import beans.mail.Mail;


public class SentMailManager {
	
	private ArrayList<FeedbackMail> sentMailList; 
	
    private final String username;
    
    
    /** ���췽��
     * 
     * @param username �û�����һ�����룬���ɸı�
     */
    public SentMailManager(String username) 
    {
    	this.username = username;
    	update();
	}
    
    
    /** ���·���������sentMailList
     * 
     * @return true
     */
    @SuppressWarnings("unchecked")
	public boolean update()
    {
		Call call = new Call("server.interfaces.MailOperationInterface", "getSentMailList",
							new Class[]{String.class}, new Object[]{username});
		Login.writeCall(call);
		Login.readCall();

		sentMailList = (ArrayList<FeedbackMail>) Login.resultCall.getResult();
		
		return true;
    }
    
    
    /** ��ѯFeedbackMail��ϸ��Ϣ��feedbackList, receiverList��
     *  ǰ��������feedbackMail.getIsNeedFeedback()==true
     * @param feedbackMail ���ѯ��FeedbackMail����
     * @return FeedbackMail
     */
    public FeedbackMail getFeedbackDetail(FeedbackMail feedbackMail)
    {
    	Call call = new Call("server.interfaces.MailOperationInterface", "getFeedbackMail",  //?????????
							new Class[]{FeedbackMail.class}, new Object[]{feedbackMail});
		Login.writeCall(call);
		Login.readCall();
		
		FeedbackMail detailFeedbackMail = (FeedbackMail) Login.resultCall.getResult();
		
		//update
		int index = getIndexOfMail(feedbackMail);
		
		if(index != -1)
		{
			sentMailList.set(index, detailFeedbackMail);
		}
		
		return detailFeedbackMail;
    }
    
    
    /** ����һ���ż��󣬽��ż����뵽SentMailManager��
     * 
     * @param mail �շ����ż�
     * @return true
     */
    public boolean addMail(Mail mail, int receiverSum)
    {
    	FeedbackMail feedbackMail = new FeedbackMail();
    	
    	feedbackMail.setSenderUsername(mail.getSenderUsername());
    	feedbackMail.setSenderName(mail.getSenderName());
    	feedbackMail.setTitle(mail.getTitle());
    	feedbackMail.setTime(mail.getTime());
    	feedbackMail.setContent(mail.getContent());
    	feedbackMail.setIsRead(mail.getIsRead());
    	feedbackMail.setIsNeedFeedback(mail.getIsNeedFeedback());
    	
    	feedbackMail.setFeedbackSum(0);
    	feedbackMail.setReceiverSum(receiverSum);
    	
    	sentMailList.add(feedbackMail);
    	return true;
    }
    
    
    /** ɾ���������е��ż�
     * 
     * @param mail �ż����ѷ����ż���
     * @return boolean
     */
    public boolean deleteMail(FeedbackMail mail)
    {
    	Call call = new Call("server.interfaces.MailOperationInterface", "deleteSentMail",
							new Class[]{String.class, Calendar.class}, 
							new Object[]{username, mail.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		//update
		if((Boolean)Login.resultCall.getResult() == true)
		{
			int index = getIndexOfMail(mail);
			
			if(index != -1)
			{
				sentMailList.remove(index);
			}
			return true;
		}		

		return false;
    }
    
    
    /** �õ��������б�������
     * 
     * @return ArrayList<FeedbackMail>
     */
    public ArrayList<FeedbackMail> showList()
    {
    	sortByTime();
    	return sentMailList;
    }
    
    public ArrayList<FeedbackMail> getList()
    {
    	return sentMailList;
    }
    
    private void sortByTime()
    {
		Collections.sort(sentMailList, new Comparator<FeedbackMail>() 
				{
					@Override
					public int compare(FeedbackMail m1, FeedbackMail m2) {
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
    
    private int getIndexOfMail(FeedbackMail mail)
	{
		for(int i=0; i<sentMailList.size(); i++)
		{
			FeedbackMail mail2 = sentMailList.get(i);
			
			if(mail2.getSenderUsername().equals(mail.getSenderUsername()) && mail2.getTime().equals(mail.getTime()))
				return i;
		}
		return -1;
	}
    
}
