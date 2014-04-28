package gui.announce;

import gui.data.DataSet;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import client.login.Login;

import beans.announcement.Announcement;
import beans.mail.Mail;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Dimension;

public class AnnounceP extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList jList = null;
	private JScrollPane jScrollPane = null;

	
	private boolean state=false;
	
	public boolean getState(){
		state=!state;
		return state;
	}
	
	/**
	 * This is the default constructor
	 */
	public AnnounceP() {
		super();
		this.setOpaque(false);
		
		initialize();
		
		displayList();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 196);
		this.setLayout(null);
		this.add(getJScrollPane(), null);
	}
	
	private JScrollPane getJScrollPane()
	{
		if(jScrollPane == null)
		{
			jScrollPane = new JScrollPane(getJList());
			jScrollPane.setBounds(new Rectangle(5, 4, 291, 185));
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
			jList.setBounds(new Rectangle(5, 4, 298, 191));
			//jList.setOpaque(false);
			
			jList.setCellRenderer(new MyRenderer());
			
			jList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					
					//监听双击
					if(e.getClickCount() == 2)
					{
						int index = jList.getSelectedIndex();
						
						if(index != -1)
						{
							Announcement announcement = DataSet.announcementList.get(index);
							
							//回馈
							Login.person.feedbackAnnouncement(announcement);
							
							DataSet.mainFrame.left.p2.setAnnouncement(announcement);
							DataSet.mainFrame.left.p2.updateUI();
							DataSet.mainFrame.left.card.show(DataSet.mainFrame.left.subpane, "announce");
							
							boolean aFlag = getState();  //???
							DataSet.mainFrame.left.subpane.setVisible(aFlag);
						}
					}
				}
			});
		}
		return jList;
	}
	
	
	/**
	 * 重新载入list
	 */
	public void displayList()
	{
		DefaultListModel defaultListModel = new DefaultListModel();
		
		DataSet.updateAnnouncementList();
		
		for(int i=0; i<DataSet.announcementList.size(); i++)
		{
			Announcement announcement = DataSet.announcementList.get(i);
			
			String tempString = announcement.getSenderName() + "：    " + announcement.getTitle();
			
			defaultListModel.addElement(tempString);
		}
		
		jList.setModel(defaultListModel);
	}
	
	
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
			
			Announcement announcement = DataSet.announcementList.get(index);
			if(announcement.getIsFeedback() == false)   
			{ 
				setBackground(Color.pink); 
			} 
			setFont(font1); 
	
			return this; 
		} 
		
	} 


}  //  @jve:decl-index=0:visual-constraint="10,10"
