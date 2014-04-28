package gui.main;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import server.reflection.Call;
import beans.mail.Mail;
import client.login.Login;

public class MailThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{
			//��������������ʾ��socket
			Login.start3();
			
			Call call = new Call("server.interfaces.MailOperationInterface", "getReceivedMailList",
								new Class[]{String.class}, new Object[]{Login.person.getUsername()});
			Login.writeCall3(call);
			Login.readCall3();
	
			@SuppressWarnings("unchecked")
			ArrayList<Mail> receivedMailList = (ArrayList<Mail>) Login.resultCall3.getResult();
			
			//�����������
			if(receivedMailList.size() > Login.person.getReceivedMailSum())
			{
				JOptionPane.showMessageDialog(null, "�����µ�����!����\"�ҵ�����\"�鿴", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				Login.person.setReceivedMailList(receivedMailList);
			}	
			
			//�ر�����������ʾ��socket
			Login.close3();
			
			//���60��
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
