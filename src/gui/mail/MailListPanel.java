package gui.mail;

import gui.data.DataSet;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import beans.mail.FeedbackMail;
import beans.mail.Mail;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Calendar;
import javax.swing.JButton;

import client.login.Login;

public class MailListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JList jList = null;
	private JButton refreshButton = null;
	private JButton deleteButton = null;
	
	private int tag;  //0表示收件箱，1表示发件箱
	
	/**
	 * This is the default constructor
	 */
	public MailListPanel() {
		super();
		setOpaque(false);
		
		//默认tag=0
		this.tag = 0;
		
		initialize();
		displayList();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(null);
		this.setBounds(new Rectangle(121, 2, 470, 444));
		this.add(getJScrollPane(), null);
		this.add(getRefreshButton(), null);
		this.add(getDeleteButton(), null);
	}

	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane()
	{
		if(jScrollPane == null)
		{
			jScrollPane = new JScrollPane(getJList());
			jScrollPane.setBounds(new Rectangle(19, 56, 431, 376));	
			jScrollPane.setOpaque(false);
		}
		return jScrollPane;
	}
	
	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setBounds(new Rectangle(19, 56, 431, 376));
			
			jList.setCellRenderer(new MyRenderer()); 
			
			jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
					// TODO Auto-generated Event stub valueChanged()
					//System.out.println("valueChanged()  " + index);
					
				}
			});
			
			jList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					
					//监听双击
					if(e.getClickCount() == 2)
					{
						//JList source = (JList) e.getSource();
						int index = jList.getSelectedIndex();
						
						if(index != -1)
						{
							//显示mail
							if(tag == 0 )
							{
								Mail mail = DataSet.receivedMailList.get(index);
								//read (feedback when need)
								Login.person.readReceivedMail(mail);
								
								DataSet.mainFrame.mailBoxFrame.receivedMailPanel.setMail(mail);
								DataSet.mainFrame.mailBoxFrame.receivedMailPanel.setVisible(true);
								DataSet.mainFrame.mailBoxFrame.receivedMailPanel.setBounds(new Rectangle(0, 0, 470, 444));
								
								DataSet.mainFrame.mailBoxFrame.centerPanel.removeAll();
								DataSet.mainFrame.mailBoxFrame.centerPanel.add(DataSet.mainFrame.mailBoxFrame.receivedMailPanel, null);
								DataSet.mainFrame.mailBoxFrame.panelTag = DataSet.mainFrame.mailBoxFrame.RECEIVED_MAIL_PANEL;
								
								DataSet.mainFrame.mailBoxFrame.receivedMailPanel.updateUI();
								DataSet.mainFrame.mailBoxFrame.centerPanel.repaint();
							}
							else 
							{
								FeedbackMail mail = DataSet.sentMailList.get(index);
								DataSet.mainFrame.mailBoxFrame.sentMailPanel.setMail(mail);
								DataSet.mainFrame.mailBoxFrame.sentMailPanel.setVisible(true);
								DataSet.mainFrame.mailBoxFrame.sentMailPanel.setBounds(new Rectangle(0, 0, 470, 444));
								
								DataSet.mainFrame.mailBoxFrame.centerPanel.removeAll();
								DataSet.mainFrame.mailBoxFrame.centerPanel.add(DataSet.mainFrame.mailBoxFrame.sentMailPanel, null);
								DataSet.mainFrame.mailBoxFrame.panelTag = DataSet.mainFrame.mailBoxFrame.SENT_MAIL_PANEL;
								
								DataSet.mainFrame.mailBoxFrame.sentMailPanel.updateUI();
								DataSet.mainFrame.mailBoxFrame.centerPanel.repaint();							
							}
						}
						
					}
				}
			});
		}
		return jList;
	}

	/**
	 * This method initializes refreshButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshButton() {
		if (refreshButton == null) {
			refreshButton = new JButton(){
				{
					setContentAreaFilled(false);
					setOpaque(false);
				}
				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/buttonbg.png");
					g.drawImage(img.getImage(),0,0,null); 
					super.paintComponent(g);
			    }
			};
			refreshButton.setBounds(new Rectangle(285, 12, 77, 34));
			refreshButton.setText("刷新");
			refreshButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			refreshButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					if(tag == 0)
					{
						Login.person.updateMailManager();
						DataSet.updateReceivedMailList();
						displayList();
					}
					else 
					{
						DataSet.updateSentMailList();
						displayList();
					}
					
				}
			});
		}
		return refreshButton;
	}

	/**
	 * This method initializes deleteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton(){
				{
					setContentAreaFilled(false);
					setOpaque(false);
				}
				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/buttonbg.png");
					g.drawImage(img.getImage(),0,0,null); 
					super.paintComponent(g);
			    }
			};
			deleteButton.setBounds(new Rectangle(373, 13, 77, 32));
			deleteButton.setText("删除");
			deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			deleteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					int index = -1;
					index = jList.getSelectedIndex();
					
					if(index > -1)
					{
						if(tag == 0)
						{
							Mail mail = DataSet.receivedMailList.get(index);	
							Login.person.deleteReceivedMail(mail);
							
							DataSet.updateReceivedMailList();
							displayList();
						}
						else 
						{
							FeedbackMail feedbackMail = DataSet.sentMailList.get(index);
							Login.person.deleteSentMail(feedbackMail);
							
							DataSet.updateSentMailList();
							displayList();
						}
					}
				}
			});
		}
		return deleteButton;
	}
	
	
	/**
	 * 重新载入list
	 */
	private void displayList()
	{
		DefaultListModel listModel = new DefaultListModel();
		
		if(tag == 0)
		{
			DataSet.updateReceivedMailList();
			
			for(int i=0; i<DataSet.receivedMailList.size(); i++)
			{
				Mail mail = DataSet.receivedMailList.get(i);
							
				Calendar calendar = mail.getTime();
				
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				
				String tempsString = "Subject: " + mail.getTitle() + "    From: " + mail.getSenderName()
									+ "    " + year + "-" + month + "-" + day + " " + hour + ":" + minute;
				
				listModel.addElement(tempsString);
			}
		}
		else 
		{
			DataSet.updateSentMailList();
			
			for(int i=0; i<DataSet.sentMailList.size(); i++)
			{
				FeedbackMail feedbackMail = DataSet.sentMailList.get(i);
				
				Calendar calendar = feedbackMail.getTime();
				
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				
				String tempsString = "Subject: " + feedbackMail.getTitle() + "    " + year + "-" + month + "-" + day + " " + hour + ":" + minute;
				
				if(feedbackMail.getIsNeedFeedback() == true)
				{
					tempsString +=  ("        " + feedbackMail.getFeedbackSum() + "已回馈 / " + feedbackMail.getReceiverSum() + "总人数");
				}
				listModel.addElement(tempsString);	
			}
		}
		
		jList.setModel(listModel);
		
	}
	
	/**
	 * 设置标记
	 * @param tag  0表示收件箱，1表示发件箱
	 */
	public void setTag(int tag)
	{
		this.tag = tag;
		displayList();
	}
	
	
	//用于设定JList中具体项的显示风格
	class MyRenderer extends DefaultListCellRenderer 
	{ 
		private Font font1; 

		public MyRenderer() 
		{ 
			this.font1 = getFont(); 
		} 
	
		public Component getListCellRendererComponent(JList list, Object value,   
					int index, boolean isSelected, boolean cellHasFocus) 
		{ 
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); 
			
			//只在收件箱列表中区分颜色
			if(tag == 0)
			{
				Mail mail = DataSet.receivedMailList.get(index);
				if(mail.getIsRead() == false)   
				{ 
					setBackground(Color.pink); 
				} 
				setFont(font1); 
			}
	
			return this; 
		} 
		
	} 

}
