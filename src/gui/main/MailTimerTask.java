package gui.main;

import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import server.reflection.Call;
import beans.mail.Mail;
import client.login.Login;

public class MailTimerTask extends TimerTask {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			//��������������ʾ��socket
			Login.start3();
			
			Call call = new Call("server.interfaces.MailOperationInterface", "getReceivedMailList",
								new Class[]{String.class}, new Object[]{Login.person.getUsername()});
			Login.writeCall3(call);
			Login.readCall3();
	
			@SuppressWarnings("unchecked")
			ArrayList<Mail> receivedMailList = (ArrayList<Mail>) Login.resultCall3.getResult();
			
			//System.out.println("new: " + receivedMailList.size() + "old: " + Login.person.getReceivedMailSum());
			
			//�����������
			if(receivedMailList.size() > Login.person.getReceivedMailSum())
			{
				JOptionPane.showMessageDialog(null, "�����µ����ţ�����\"�ҵ�����\"�鿴", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				Login.person.setReceivedMailList(receivedMailList);
			}
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			//�ر�����������ʾ��socket
			Login.close3();
		}
		
		
	}

}
