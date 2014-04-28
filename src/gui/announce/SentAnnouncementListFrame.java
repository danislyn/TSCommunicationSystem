package gui.announce;

import gui.data.DataSet;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JList;
import java.awt.Rectangle;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JTextArea;
import javax.swing.JRadioButton;

import client.login.Login;

import beans.announcement.FeedbackAnnouncement;

public class SentAnnouncementListFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JList jList = null;
	private JScrollPane jScrollPane = null;
	private JLabel titleLabel = null;
	private JTextField titleTextField = null;
	private JLabel gradeLabel = null;
	private JComboBox gradeComboBox = null;
	private JTextArea contentTextArea = null;
	private JScrollPane contentScrollPane = null;
	private JLabel feedbackLabel = null;
	private JList feedbackList = null;
	private JScrollPane feedbackScrollPane = null;
	private JRadioButton jRadioButton = null;
	
	private HashMap<Integer, String> gradeToStringMap;
	
	private FeedbackAnnouncement myAnnouncement;
	
	
	/**
	 * This is the default constructor
	 */
	public SentAnnouncementListFrame() {
		super();
		
		gradeToStringMap = new HashMap<Integer, String>();
		gradeToStringMap.put(0, "老师");
		gradeToStringMap.put(1, "大一");
		gradeToStringMap.put(2, "大二");
		gradeToStringMap.put(3, "大三");
		gradeToStringMap.put(4, "大四");
		
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(709, 402);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			feedbackLabel = new JLabel();
			feedbackLabel.setText("");
			feedbackLabel.setLocation(new Point(534, 48));
			feedbackLabel.setSize(new Dimension(149, 30));
			gradeLabel = new JLabel();
			gradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			gradeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			gradeLabel.setLocation(new Point(263, 48));
			gradeLabel.setSize(new Dimension(75, 30));
			gradeLabel.setText("可见群体");
			titleLabel = new JLabel();
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			titleLabel.setLocation(new Point(262, 10));
			titleLabel.setSize(new Dimension(60, 30));
			titleLabel.setText("标题");
			jContentPane = new JPanel(){
				{
					setOpaque(false);
				}
				
				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/b2.jpg");
					g.drawImage(img.getImage(),0,0,null); 
			    }
			};
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(titleLabel, null);
			jContentPane.add(getTitleTextField(), null);
			jContentPane.add(gradeLabel, null);
			jContentPane.add(getGradeComboBox(), null);
			jContentPane.add(getContentScrollPane(), null);
			jContentPane.add(feedbackLabel, null);
			jContentPane.add(getFeedbackScrollPane(), null);
			jContentPane.add(getJRadioButton(), null);
		}
		return jContentPane;
	}
	
	private JScrollPane getJScrollPane()
	{
		if(jScrollPane == null)
		{
			jScrollPane = new JScrollPane(getJList());
			jScrollPane.setBounds(new Rectangle(8, 8, 243, 349));
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
			jList.setBounds(new Rectangle(8, 8, 243, 349));
			
			jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
					//System.out.println("valueChanged()"); // TODO Auto-generated Event stub valueChanged()
					
					int index = jList.getSelectedIndex();
					
					if(index != -1)
					{
						FeedbackAnnouncement feedbackAnnouncement = DataSet.sentAnnouncementList.get(index);
						
						setFeedbackAnnouncement(feedbackAnnouncement);
						repaint();
					}
										
				}
			});
		}
		return jList;
	}

	/**
	 * This method initializes titleTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTitleTextField() {
		if (titleTextField == null) {
			titleTextField = new JTextField();
			titleTextField.setSize(new Dimension(179, 28));
			titleTextField.setEditable(false);
			titleTextField.setLocation(new Point(333, 10));
		}
		return titleTextField;
	}

	/**
	 * This method initializes gradeComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getGradeComboBox() {
		if (gradeComboBox == null) {
			gradeComboBox = new JComboBox();
			gradeComboBox.setSize(new Dimension(100, 30));
			gradeComboBox.setLocation(new Point(357, 48));
			
			for(int i=0; i<gradeToStringMap.keySet().size(); i++)
			{
				gradeComboBox.addItem(gradeToStringMap.get(i));
			}
			
			gradeComboBox.setSelectedIndex(-1);
			gradeComboBox.setEditable(false);
			//gradeComboBox.setEnabled(false);
		}
		return gradeComboBox;
	}

	private JScrollPane getContentScrollPane()
	{
		if(contentScrollPane == null)
		{
			contentScrollPane = new JScrollPane(getContentTextArea());
			contentScrollPane.setBounds(new Rectangle(264, 86, 255, 270));
		}
		return contentScrollPane;
	}
	
	/**
	 * This method initializes contentTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getContentTextArea() {
		if (contentTextArea == null) {
			contentTextArea = new JTextArea();
			contentTextArea.setBounds(new Rectangle(264, 86, 255, 270));
			
			contentTextArea.setEditable(false);
		}
		return contentTextArea;
	}
	
	private JScrollPane getFeedbackScrollPane()
	{
		if(feedbackScrollPane == null)
		{
			feedbackScrollPane = new JScrollPane(getFeedbackList());
			feedbackScrollPane.setBounds(534, 87, 111, 268);
		}
		return feedbackScrollPane;
	}

	/**
	 * This method initializes feedbackList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getFeedbackList() {
		if (feedbackList == null) {
			feedbackList = new JList();
			feedbackList.setSize(new Dimension(111, 268));
			feedbackList.setLocation(new Point(534, 87));
		}
		return feedbackList;
	}

	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton() {
		if (jRadioButton == null) {
			jRadioButton = new JRadioButton();
			jRadioButton.setBounds(new Rectangle(657, 90, 21, 21));
			
			jRadioButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					if(jRadioButton.isSelected() == true)
					{
						displayFeedbackList(myAnnouncement.getFeedbackList());						
					}
					else
					{
						displayFeedbackList(new ArrayList<String>());
					}
						
				}
			});
			
			
		}
		return jRadioButton;
	}
	
	
	/**
	 * 重新载入list（jList）
	 */
	public void displayList()
	{
		DefaultListModel defaultListModel = new DefaultListModel();
		
		DataSet.updateSentAnnouncementList();
		
		for(int i=0; i<DataSet.sentAnnouncementList.size(); i++)
		{
			FeedbackAnnouncement feedbackAnnouncement = DataSet.sentAnnouncementList.get(i);
			
			Calendar calendar = feedbackAnnouncement.getTime();	
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			
			String tempsString = "标题: " + feedbackAnnouncement.getTitle() +"  ----  时间：    " + year + "-" + month + "-" + day + " " + hour + ":" + minute;
			
			defaultListModel.addElement(tempsString);
		}
		
		jList.setModel(defaultListModel);
	}
	
	/**
	 * 重新载入list（feedbackList）
	 * @param list
	 */
	private void displayFeedbackList(ArrayList<String> list)
	{
		DefaultListModel defaultListModel = new DefaultListModel();
		
		for(int i=0; i<list.size(); i++)
			defaultListModel.addElement(list.get(i));
		
		feedbackList.setModel(defaultListModel);
	}
	
	
	/**
	 * 设置公告对象（用于reset）
	 * @param feedbackAnnouncement
	 */
	public void setFeedbackAnnouncement(FeedbackAnnouncement feedbackAnnouncement)
	{
		this.myAnnouncement = feedbackAnnouncement;
		
		titleTextField.setText(feedbackAnnouncement.getTitle());
		contentTextArea.setText(feedbackAnnouncement.getContent());
		gradeComboBox.setSelectedIndex(feedbackAnnouncement.getGrade());
		
		feedbackLabel.setText(feedbackAnnouncement.getFeedbackSum() + " 人已回馈                  " + "查看");
		
		jRadioButton.setSelected(false);
		//清空feedback
		displayFeedbackList(new ArrayList<String>());
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
