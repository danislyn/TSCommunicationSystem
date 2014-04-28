package gui.mail;

import gui.data.DataSet;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Rectangle;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import beans.mail.Mail;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JButton;

public class ReceivedMailPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField senderTextField = null;
	private JTextField subjectTextField = null;
	private JTextField timeTextField = null;
	private JLabel tagLabel = null;
	private JTextArea contentTextArea = null;
	private JScrollPane jScrollPane = null;
	
	private Mail mail = null;  //  @jve:decl-index=0:
	private JButton replyButton = null;

	/**
	 * This is the default constructor
	 */
	public ReceivedMailPanel() {
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
		tagLabel = new JLabel();
		tagLabel.setBounds(new Rectangle(280, 101, 95, 28));
		tagLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tagLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		tagLabel.setText("");
		this.setSize(470, 444);
		this.setLayout(null);
		this.add(getSenderTextField(), null);
		this.add(getSubjectTextField(), null);
		this.add(getTimeTextField(), null);
		this.add(tagLabel, null);
		//this.add(getContentTextArea(), null);
		this.add(getReplyButton(), null);
		this.add(getJScrollPane(), null);
	}

	
	/**
	 * This method initializes jcrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane()
	{
		if(jScrollPane == null)
		{
			jScrollPane = new JScrollPane(getContentTextArea());
			jScrollPane.setBounds(new Rectangle(23, 147, 421, 279));
		}
		return jScrollPane;
	}
	
	/**
	 * This method initializes senderTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSenderTextField() {
		if (senderTextField == null) {
			senderTextField = new JTextField();
			senderTextField.setEditable(false);
			senderTextField.setSize(new Dimension(350, 30));
			senderTextField.setLocation(new Point(23, 13));
		}
		return senderTextField;
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
			subjectTextField.setLocation(new Point(23, 57));
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
			timeTextField.setBounds(new Rectangle(23, 100, 220, 29));
			timeTextField.setEditable(false);
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
			contentTextArea.setBounds(new Rectangle(23, 147, 421, 279));
			contentTextArea.setEditable(false);
		}
		return contentTextArea;
	}
	
	/**
	 * This method initializes replyButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getReplyButton() {
		if (replyButton == null) {
			replyButton = new JButton();
			replyButton.setBounds(new Rectangle(394, 14, 62, 28));
			replyButton.setText("回复");
			replyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			replyButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					DataSet.mainFrame.mailBoxFrame.centerPanel.removeAll();
					DataSet.mainFrame.mailBoxFrame.writeMailPanel.reset();  //claer
					DataSet.mainFrame.mailBoxFrame.writeMailPanel.setMail(mail);  //set
					DataSet.mainFrame.mailBoxFrame.centerPanel.add(DataSet.mainFrame.mailBoxFrame.writeMailPanel, null);
					DataSet.mainFrame.mailBoxFrame.panelTag = DataSet.mainFrame.mailBoxFrame.WRITE_MAIL_PANEL;
					DataSet.mainFrame.mailBoxFrame.writeMailPanel.updateUI();
					DataSet.mainFrame.mailBoxFrame.repaint();
				}
			});
		}
		return replyButton;
	}
	
	
	/**
	 * 设置mail对象
	 * @param mail
	 */
	public void setMail(Mail mail)
	{
		this.mail = mail;
		
		senderTextField.setText("From: " + mail.getSenderName() + "    (ID: " + mail.getSenderUsername() + " )");
		subjectTextField.setText("Subject: " + mail.getTitle());
		
		Calendar calendar = mail.getTime();		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		timeTextField.setText("Time: " + year + "-" + month + "-" + day + " " + hour + ":" + minute);
		
		boolean isNeedFeedback = mail.getIsNeedFeedback();
		if(isNeedFeedback == true)
		{
			tagLabel.setText("需回馈邮件");
		}
		else
		{
			tagLabel.setText("普通邮件");
		}
		
		contentTextArea.setText(mail.getContent());
		
		repaint();
	}


}
