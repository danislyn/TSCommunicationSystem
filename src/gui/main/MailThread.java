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
			//开启用于来信提示的socket
			Login.start3();
			
			Call call = new Call("server.interfaces.MailOperationInterface", "getReceivedMailList",
								new Class[]{String.class}, new Object[]{Login.person.getUsername()});
			Login.writeCall3(call);
			Login.readCall3();
	
			@SuppressWarnings("unchecked")
			ArrayList<Mail> receivedMailList = (ArrayList<Mail>) Login.resultCall3.getResult();
			
			//如果有新来信
			if(receivedMailList.size() > Login.person.getReceivedMailSum())
			{
				JOptionPane.showMessageDialog(null, "您有新的来信!请点击\"我的信箱\"查看", "提示",JOptionPane.INFORMATION_MESSAGE);
				Login.person.setReceivedMailList(receivedMailList);
			}	
			
			//关闭用于来信提示的socket
			Login.close3();
			
			//间隔60秒
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
