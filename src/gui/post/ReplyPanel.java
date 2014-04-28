package gui.post;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import beans.post.Comment;

public class ReplyPanel extends JPanel {
/**
 * ��ʾ���ӵĻظ����ݵİ��
 */
	private static final long serialVersionUID = 1L;
	private JLabel sendername = null;
	private JTextField sendername_c = null;
	private JLabel title = null;
	private JTextField title_c = null;
	private JTextField floor_c = null;
	private JTextField replytime_c = null;
	private JTextArea jTextArea = null;
	private JScrollPane jScrollPane = null;
	private JLabel �ָ��� = null;
	private String post_title=null;
	private int count=0;
	private Comment comment =null; //  @jve:decl-index=0:
	/**
	 * 
	 * @param post_name    Ҫ�ظ������ӵı���
	 * @param comment_c    �����ӵ�����
	 * @param i            ��ʾ�ǵ�i������
	 */
	public ReplyPanel(String post_name, Comment comment_c, int i) {
		super();
		post_title=post_name;  
		comment=comment_c;
		count=i;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		�ָ��� = new JLabel();
		�ָ���.setBounds(new Rectangle(2, 182, 695, 9));
		�ָ���.setText("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		title = new JLabel();
		title.setBounds(new Rectangle(2, 33, 74, 27));
		title.setText("���⣺  Re");
		sendername = new JLabel();
		sendername.setBounds(new Rectangle(3, 2, 73, 28));
		sendername.setText("�����ˣ�");
		this.setLayout(null);
		this.setBounds(new Rectangle(0, 0, 704, 195));
		this.add(sendername, null);
		this.add(getSendername_c(), null);
		this.add(title, null);
		this.add(getTitle_c(), null);
		this.add(getFloor_c(), null);
		this.add(getReplytime_c(), null);
		//this.add(getJTextArea(), null);
		this.add(getJScrollPane(),null);
		this.add(�ָ���, null);
		this.setOpaque(false);
	}

	/**
	 * This method initializes sendername_c	
	 * 	���ûظ��˵��û���������
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSendername_c() {
		if (sendername_c == null) {
			sendername_c = new JTextField();
			sendername_c.setText(comment.getSenderUsername()+"    "+comment.getSenderName());
			
			sendername_c.setBounds(new Rectangle(84, 2, 138, 29));
			
			sendername_c.setEditable(false);
			sendername_c.setOpaque(false);
			sendername_c.setBorder(null);
		}
		return sendername_c;
	}

	/**
	 * This method initializes title_c	
	 * 	���ñ��ظ����ӵı���
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTitle_c() {
		if (title_c == null) {
			title_c = new JTextField();
			
			title_c.setText(post_title);
			title_c.setBounds(new Rectangle(83, 35, 241, 25));
			
			title_c.setEditable(false);
			title_c.setOpaque(false);
			title_c.setBorder(null);
		}
		return title_c;
	}

	/**
	 * This method initializes floor_c	
	 * 	�������۵�¥��
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFloor_c() {
		if (floor_c == null) {
			floor_c = new JTextField();
			
			floor_c.setText(String.valueOf(count));
			floor_c.setBounds(new Rectangle(656, 2, 32, 28));
			
			floor_c.setEditable(false);
			floor_c.setOpaque(false);
			floor_c.setBorder(null);
		}
		return floor_c;
	}

	/**
	 * This method initializes replytime_c	
	 * 	���ûظ�ʱ��
	 * @return javax.swing.JTextField	
	 */
	private JTextField getReplytime_c() {
		if (replytime_c == null) {
			replytime_c = new JTextField();
			
			Calendar calendar = comment.getTime();		
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			String s=year + "-" + month + "-" + day + " " + hour + ":" + minute;
			replytime_c.setText(s);
			
			replytime_c.setBounds(new Rectangle(512, 2, 128, 27));
			replytime_c.setEditable(false);
			replytime_c.setOpaque(false);
			replytime_c.setBorder(null);
		}
		return replytime_c;
	}

	/**
	 * This method initializes jTextArea	
	 * 	�������۵�����
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea(){
				/**
				 * ��textArea�ӱ���
				 */
				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/reply.jpg");
					g.drawImage(img.getImage(),0,0,null); 
					super.paintComponent(g);
			    }
			};
			
			jTextArea.setLineWrap(true);
			jTextArea.setText(comment.getContent());
			//jTextArea.setBounds(new Rectangle(3, 63, 695, 113));
			
			jTextArea.setEditable(false);
			jTextArea.setOpaque(false);
			jTextArea.setBorder(null);
		}
		return jTextArea;
	}
	
	/**
	 * 
	 * @return
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(3, 63, 680, 113));
			jScrollPane.setViewportView(getJTextArea());
			jScrollPane.setOpaque(false);
		}
		return jScrollPane;
	}

}  //  @jve:decl-index=0:visual-constraint="6,12"
