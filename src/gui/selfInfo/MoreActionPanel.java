package gui.selfInfo;

import gui.address.AddressManageFrame;
import gui.announce.SendAnnouncementFrame;
import gui.announce.SentAnnouncementListFrame;
import gui.data.DataSet;
import gui.information.ReviseInformationFrame;
import gui.information.RevisePasswordFrame;
import gui.schedule.CalendarPad;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import client.login.Login;

public class MoreActionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton revisePasswordButton = null;
	private JButton reviseInfoButton = null;
	private JButton scheduleButton = null;
	private JButton sendAnnouncementButton = null;
	private JButton sentAnnouncementListButton = null;
	private JButton groupManageButton = null;

	/**
	 * This is the default constructor
	 */
	public MoreActionPanel() {
		super();
		initialize();
		
		//如果是学生，没有日程安排
		if(Login.person.getAuthority() == 0)
		{
			scheduleButton.setVisible(false);
			sendAnnouncementButton.setVisible(false);
			sentAnnouncementListButton.setVisible(false);
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(310, 200);
		this.setLayout(null);
		this.add(getRevisePasswordButton(), null);
		this.add(getReviseInfoButton(), null);
		this.add(getScheduleButton(), null);
		this.add(getSendAnnouncementButton(), null);
		this.add(getSentAnnouncementListButton(), null);
		this.add(getGroupManageButton(), null);
	}

	/**
	 * This method initializes revisePasswordButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRevisePasswordButton() {
		if (revisePasswordButton == null) {
			revisePasswordButton = new JButton(){
				/**
				 * 重绘按钮
				 */
				private static final long serialVersionUID = 1L;
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
			revisePasswordButton.setText("修改密码");
			revisePasswordButton.setSize(new Dimension(90, 30));
			revisePasswordButton.setLocation(new Point(43, 62));
			revisePasswordButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			revisePasswordButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					
					if(DataSet.mainFrame.revisePasswordFrame == null)
					{
						DataSet.mainFrame.revisePasswordFrame = new RevisePasswordFrame();
					}
					
					DataSet.mainFrame.revisePasswordFrame.reset();
					DataSet.mainFrame.revisePasswordFrame.setVisible(true);					
				}
			});
		}
		return revisePasswordButton;
	}

	/**
	 * This method initializes reviseInfoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getReviseInfoButton() {
		if (reviseInfoButton == null) {
			reviseInfoButton = new JButton(){
				/**
				 * 重绘按钮
				 */
				private static final long serialVersionUID = 1L;
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
			reviseInfoButton.setPreferredSize(new Dimension(90, 30));
			reviseInfoButton.setSize(new Dimension(90, 30));
			reviseInfoButton.setLocation(new Point(156, 62));
			reviseInfoButton.setText("修改信息");
			reviseInfoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			reviseInfoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					
					if(DataSet.mainFrame.reviseInformationFrame == null)
					{
						DataSet.mainFrame.reviseInformationFrame = new ReviseInformationFrame();
					}
					
					DataSet.mainFrame.reviseInformationFrame.setInformation(Login.person.getMyInformation());
					DataSet.mainFrame.reviseInformationFrame.setVisible(true);				
				}
			});
		}
		return reviseInfoButton;
	}
	
	/**
	 * This method initializes groupManageButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGroupManageButton() {
		if (groupManageButton == null) {
			groupManageButton = new JButton(){
				/**
				 * 重绘按钮
				 */
				private static final long serialVersionUID = 1L;
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
			groupManageButton.setPreferredSize(new Dimension(90, 30));
			groupManageButton.setSize(new Dimension(90, 30));
			groupManageButton.setLocation(new Point(43, 106));
			groupManageButton.setText("分组管理");
			groupManageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			groupManageButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated Event stub actionPerformed()
					
					if(DataSet.mainFrame.addressManageFrame == null)
					{
						DataSet.mainFrame.addressManageFrame = new AddressManageFrame();
					}
					
					DataSet.mainFrame.addressManageFrame.setVisible(true);
				}
			});
		}
		return groupManageButton;
	}

	/**
	 * This method initializes scheduleButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getScheduleButton() {
		if (scheduleButton == null) {
			scheduleButton = new JButton(){
				/**
				 * 重绘按钮
				 */
				private static final long serialVersionUID = 1L;
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
			scheduleButton.setText("日程安排");
			scheduleButton.setPreferredSize(new Dimension(90, 30));
			scheduleButton.setSize(new Dimension(90, 30));
			scheduleButton.setLocation(new Point(156, 106));
			scheduleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					
			scheduleButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					
					CalendarPad calendarPad = new CalendarPad(1, null);  //1表示老师
				    calendarPad.setVisible(true);
				}
			});
		}
		return scheduleButton;
	}

	/**
	 * This method initializes sendAnnouncementButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSendAnnouncementButton() {
		if (sendAnnouncementButton == null) {
			sendAnnouncementButton = new JButton(){
				/**
				 * 重绘按钮
				 */
				private static final long serialVersionUID = 1L;
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
			sendAnnouncementButton.setText("发布公告");
			sendAnnouncementButton.setSize(new Dimension(90, 30));
			sendAnnouncementButton.setLocation(new Point(43, 150));
			sendAnnouncementButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			sendAnnouncementButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					
					if(DataSet.mainFrame.sendAnnouncementFrame == null)
					{
						DataSet.mainFrame.sendAnnouncementFrame = new SendAnnouncementFrame();
					}
					
					DataSet.mainFrame.sendAnnouncementFrame.reset();
					DataSet.mainFrame.sendAnnouncementFrame.setVisible(true);
				}
			});
		}
		return sendAnnouncementButton;
	}

	/**
	 * This method initializes sentAnnouncementListButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSentAnnouncementListButton() {
		if (sentAnnouncementListButton == null) {
			sentAnnouncementListButton = new JButton(){
				/**
				 * 重绘背景
				 */
				private static final long serialVersionUID = 1L;
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
			sentAnnouncementListButton.setText("已发公告");
			sentAnnouncementListButton.setSize(new Dimension(90, 30));
			sentAnnouncementListButton.setLocation(new Point(156, 150));
			sentAnnouncementListButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			sentAnnouncementListButton.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							 // TODO Auto-generated Event stub actionPerformed()
							
							if(DataSet.mainFrame.sentAnnouncementListFrame == null)
							{
								DataSet.mainFrame.sentAnnouncementListFrame = new SentAnnouncementListFrame();
							}
							
							DataSet.mainFrame.sentAnnouncementListFrame.displayList();
							DataSet.mainFrame.sentAnnouncementListFrame.setVisible(true);
						}
					});
		}
		return sentAnnouncementListButton;
	}

}
