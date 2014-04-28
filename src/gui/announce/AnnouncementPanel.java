package gui.announce;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Rectangle;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.JTextArea;

import beans.announcement.Announcement;

public class AnnouncementPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField title_c = null;
	private JTextField sendername_c = null;
	private JLabel hint = null;
	private JLabel category = null;
	private JTextField category_c = null;
	private JTextField time_c = null;
	private JTextArea content_c = null;  //  @jve:decl-index=0:visual-constraint="330,10"
	private JScrollPane jScrollPane = null;

	/**
	 * This is the default constructor
	 */
	public AnnouncementPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		category = new JLabel();
		category.setBounds(new Rectangle(26, 66, 53, 25));
		category.setText("类别：");
		hint = new JLabel();
		hint.setBounds(new Rectangle(85, 36, 19, 25));
		hint.setText("：");
		this.setSize(310, 200);
		this.setLayout(null);
		this.add(getTitle_c(), null);
		this.add(getSendername_c(), null);
		this.add(hint, null);
		this.add(category, null);
		this.add(getCategory_c(), null);
		this.add(getTime_c(), null);
		//this.add(getContent_c(), null);
		this.add(getJScrollPane(), null);
	}

	private JScrollPane getJScrollPane()
	{
		if(jScrollPane == null)
		{
			jScrollPane = new JScrollPane(getContent_c());
			jScrollPane.setBounds(new Rectangle(25, 93, 263, 96));
		}
		return jScrollPane;
	}
	
	/**
	 * This method initializes title_c	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTitle_c() {
		if (title_c == null) {
			title_c = new JTextField();
			title_c.setBounds(new Rectangle(112, 36, 168, 25));
			title_c.setBorder(null);
			title_c.setEditable(false);
		}
		return title_c;
	}

	/**
	 * This method initializes sendername_c	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSendername_c() {
		if (sendername_c == null) {
			sendername_c = new JTextField();
			sendername_c.setBounds(new Rectangle(25, 36, 56, 25));
			sendername_c.setBorder(null);
			sendername_c.setEditable(false);
		}
		return sendername_c;
	}

	/**
	 * This method initializes category_c	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCategory_c() {
		if (category_c == null) {
			category_c = new JTextField();
			category_c.setBounds(new Rectangle(86, 66, 89, 25));
			category_c.setBorder(null);
			category_c.setEditable(false);
		}
		return category_c;
	}

	/**
	 * This method initializes time_c	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTime_c() {
		if (time_c == null) {
			time_c = new JTextField();
			time_c.setBounds(new Rectangle(201, 66, 83, 25));
			time_c.setBorder(null);
			time_c.setEditable(false);
			//time_c.setOpaque(false);
		}
		return time_c;
	}

	/**
	 * This method initializes content_c	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getContent_c() {
		if (content_c == null) {
			content_c = new JTextArea();
			content_c.setBounds(new Rectangle(5, 66, 270, 121));
			content_c.setBorder(null);
			content_c.setEditable(false);
		}
		return content_c;
	}
	
	
	/**
	 * 设置公告对象（用于reset）
	 * @param announcement
	 */
	public void setAnnouncement(Announcement announcement)
	{
		sendername_c.setText(announcement.getSenderName());
		title_c.setText(announcement.getTitle());
		category_c.setText(announcement.getCategory());
		content_c.setText(announcement.getContent());
		
		Calendar calendar = announcement.getTime();	
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		
		String tempsString = "时间：    " + year + "-" + month + "-" + day + " " + hour + ":" + minute;
		time_c.setText(tempsString);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
