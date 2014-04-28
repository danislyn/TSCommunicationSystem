package gui.mail;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import java.awt.Point;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JList;

import beans.mail.FeedbackMail;

import client.login.Login;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;

public class SentMailPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField subjectTextField = null;
	private JTextField timeTextField = null;
	private JLabel tagLabel = null;
	private JTextArea contentTextArea = null;
	private JLabel feedbackInfoLabel = null;
	private JRadioButton feedbackListRadioButton = null;
	private JRadioButton receiverListRadioButton = null;
	private JList jList = null;
	
	private JScrollPane contentScrollPane = null;
	private JScrollPane listScrollPane = null;
	
	private FeedbackMail mail = null;  //  @jve:decl-index=0:
	private FeedbackMail detailFeedbackMail = null;
	
	/**
	 * This is the default constructor
	 */
	public SentMailPanel() {
		super();
		setOpaque(false);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		feedbackInfoLabel = new JLabel();
		feedbackInfoLabel.setBounds(new Rectangle(357, 59, 103, 30));
		feedbackInfoLabel.setText("");
		tagLabel = new JLabel();
		tagLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tagLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		tagLabel.setLocation(new Point(252, 59));
		tagLabel.setSize(new Dimension(91, 30));
		tagLabel.setText("");
		this.setSize(470, 444);
		this.setLayout(null);
		this.add(getSubjectTextField(), null);
		this.add(getTimeTextField(), null);
		this.add(tagLabel, null);
		//this.add(getContentTextArea(), null);
		this.add(feedbackInfoLabel, null);
		this.add(getFeedbackListRadioButton(), null);
		this.add(getReceiverListRadioButton(), null);
		//this.add(getJList(), null);
		this.add(getContentScrollPane(), null);
		this.add(getListScrollPane(), null);
	}
	
	private JScrollPane getContentScrollPane()
	{
		if(contentScrollPane == null)
		{
			contentScrollPane = new JScrollPane(getContentTextArea());
			contentScrollPane.setBounds(new Rectangle(21, 101, 321, 323));
		}
		return contentScrollPane;
	}
	
	private JScrollPane getListScrollPane()
	{
		if(listScrollPane == null)
		{
			listScrollPane = new JScrollPane(getJList());
			listScrollPane.setBounds(new Rectangle(357, 116, 102, 309));
			listScrollPane.setOpaque(false);
		}
		return listScrollPane;
	}

	/**
	 * This method initializes subjectTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSubjectTextField() {
		if (subjectTextField == null) {
			subjectTextField = new JTextField();
			subjectTextField.setEditable(false);
			subjectTextField.setSize(new Dimension(350, 30));
			subjectTextField.setLocation(new Point(18, 16));
		}
		return subjectTextField;
	}

	/**
	 * This method initializes timeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTimeTextField() {
		if (timeTextField == null) {
			timeTextField = new JTextField();
			timeTextField.setEditable(false);
			timeTextField.setSize(new Dimension(228, 30));
			timeTextField.setLocation(new Point(18, 59));
		}
		return timeTextField;
	}

	/**
	 * This method initializes contentTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getContentTextArea() {
		if (contentTextArea == null) {
			contentTextArea = new JTextArea();
			contentTextArea.setBounds(new Rectangle(21, 101, 321, 323));
			contentTextArea.setEditable(false);
		}
		return contentTextArea;
	}

	/**
	 * This method initializes feedbackListRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getFeedbackListRadioButton() {
		if (feedbackListRadioButton == null) {
			feedbackListRadioButton = new JRadioButton();
			feedbackListRadioButton.setSize(new Dimension(21, 21));
			feedbackListRadioButton.setMnemonic(KeyEvent.VK_UNDEFINED);
			feedbackListRadioButton.setLocation(new Point(369, 95));
			
			feedbackListRadioButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					receiverListRadioButton.setSelected(false);
					
					if(detailFeedbackMail == null)
					{
						detailFeedbackMail = Login.person.getFeedbackMailDetail(mail);						
					}
					
					//show list
					displayList(detailFeedbackMail.getFeedbackList());
				}
			});
		}
		return feedbackListRadioButton;
	}

	/**
	 * This method initializes receiverListRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getReceiverListRadioButton() {
		if (receiverListRadioButton == null) {
			receiverListRadioButton = new JRadioButton();
			receiverListRadioButton.setSize(new Dimension(21, 21));
			receiverListRadioButton.setLocation(new Point(429, 95));
			
			receiverListRadioButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					feedbackListRadioButton.setSelected(false);
					
					if(detailFeedbackMail == null)
					{
						detailFeedbackMail = Login.person.getFeedbackMailDetail(mail);					
					}
					
					//show list
					displayList(detailFeedbackMail.getReceiverList());
				}
			});
		}
		return receiverListRadioButton;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setBounds(new Rectangle(357, 116, 102, 309));
		}
		return jList;
	}
	
	
	/**
	 * 设置mail对象
	 * @param mail
	 */
	public void setMail(FeedbackMail mail)
	{
		this.mail = mail;
		
		//reset
		this.detailFeedbackMail = null;
		feedbackListRadioButton.setSelected(false);
		receiverListRadioButton.setSelected(false);
		jList.setModel(new DefaultListModel());
		
		subjectTextField.setText("Subject: " + mail.getTitle());
		contentTextArea.setText(mail.getContent());
		
		Calendar calendar = mail.getTime();		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		timeTextField.setText("Time: " + year + "-" + month + "-" + day + " " + hour + ":" + minute);
		
		boolean isNeedFeedback = mail.getIsNeedFeedback();
		
		//普通邮件
		if(isNeedFeedback == false)
		{
			tagLabel.setText("普通邮件");
			
			feedbackInfoLabel.setVisible(false);
			feedbackListRadioButton.setVisible(false);
			receiverListRadioButton.setVisible(false);
			jList.setVisible(false);
			listScrollPane.setVisible(false);
		}
		//需回馈邮件
		else 
		{
			tagLabel.setText("需回馈邮件");
			feedbackInfoLabel.setText(mail.getFeedbackSum() + "已回馈 / " + mail.getReceiverSum() + "总人数");
			
			feedbackInfoLabel.setVisible(true);
			feedbackListRadioButton.setVisible(true);
			receiverListRadioButton.setVisible(true);
			listScrollPane.setVisible(true);
			jList.setVisible(true);
			//jList.setModel(new DefaultListModel());
		}
		
		repaint();
	}
	
	
	/**
	 * 重新载入list
	 * @param usernameList
	 */
	private void displayList(ArrayList<String> usernameList)
	{
		DefaultListModel listModel = new DefaultListModel();
		
		for(int i=0; i<usernameList.size(); i++)
		{
			listModel.addElement(usernameList.get(i));
		}
		
		jList.setModel(listModel);
	}

}
