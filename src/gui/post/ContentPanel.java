package gui.post;

import gui.data.DataSet;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import beans.post.Post;
import client.login.Login;

/**
 * 
 * @author Administrator
 *
 */
public class ContentPanel extends JPanel {
	/**
	 * ��ʾ�������ݵİ��
	 */
	private static final long serialVersionUID = 1L;
	private JLabel title = null;
	private JTextField title_c = null;
	private JLabel senderName = null;
	private JTextField senderName_c = null;
	private JLabel sendtime = null;
	private JTextField sendtime_c = null;
	private JLabel category = null;
	private JTextField category_c = null;
	private JLabel readnumber = null;
	private JTextField readnumber_c = null;
	private JTextArea content_c = null;
	private JScrollPane jScrollPane = null;
	private JLabel �ָ��� = null;
	private JButton reply = null;
	private JLabel delete = null;
	private JLabel top = null;
	private int tag_r=0;
	private Post M_post =null;  
	//  @jve:decl-index=0:
	
	/**
	 * 
	 * @param tagR �������ڰ��
	 * @param post ������Ҫ��ʾ����post�������
	 */
	public ContentPanel(int tagR, Post post) {
		super();
		tag_r=tagR;
		M_post=post;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		/**����Ա��Ȩ���������ö�
		 * ֻ�е��û�Ϊ����Աʱ����ʾ
		 * */
		top = new JLabel();
		top.setBounds(new Rectangle(622, 5, 56, 22));
		if(M_post.getIsTop())
		   top.setText("ȡ���ö�");
		else{
			top.setText("�ö�");
		}
		top.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		top.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated Event stub mouseClicked()
				boolean a = false;
				if(top.getText()=="�ö�")
					a=Login.person.makePostTop(tag_r, M_post);
				else
					a=Login.person.cancelPostTop(tag_r, M_post);
				if(a){
					JOptionPane.showMessageDialog(null, "�����ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					Login.person.updateOneBlock(tag_r);
				}
				else
					JOptionPane.showMessageDialog(null, "������˼������ʧ�ܣ��������粻�ȶ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		/**����Ա��Ȩ��������ɾ��
		 * ֻ�е��û�Ϊ����Աʱ����ʾ
		 */
		delete = new JLabel();
		delete.setBounds(new Rectangle(538, 6, 63, 21));
		delete.setFont(new Font("����", Font.BOLD, 12));
		delete.setText("ɾ��");
		delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		delete.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated Event stub mouseClicked()
				if(Login.person.deletePost(tag_r, M_post)){
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					Login.person.updateOneBlock(tag_r);
					DataSet.mainFrame.postPanel.changeTotabbedPane(tag_r);
				    DataSet.mainFrame.postPanel.updateUI();
				    DataSet.mainFrame.postPanel.repaint();
				}
				else
					JOptionPane.showMessageDialog(null, "������˼��ɾ��ʧ�ܣ��������粻�ȶ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		/**
		 * �ж��û��Ƿ�Ϊ����Ա���Ӷ�ȷ���ö���ɾ��������Label�Ƿ���ʾ
		 */
		if(Login.person.getAuthority()==5)
		{
			delete.setVisible(true);
			top.setVisible(true);
		}
			
		else
			{delete.setVisible(false);
			top.setVisible(false);
			}
		
		�ָ��� = new JLabel();
		�ָ���.setBounds(new Rectangle(3, 201, 698, 12));
		�ָ���.setText("==================================================================================================");
		�ָ���.setOpaque(false);
		readnumber = new JLabel();
		readnumber.setBounds(new Rectangle(397, 172, 56, 26));
		readnumber.setText("�������");
		category = new JLabel();
		category.setBounds(new Rectangle(238, 6, 51, 25));
		category.setText("���");
		sendtime = new JLabel();
		sendtime.setBounds(new Rectangle(205, 171, 67, 26));
		sendtime.setText("����ʱ�䣺");
		senderName = new JLabel();
		senderName.setBounds(new Rectangle(3, 171, 66, 26));
		senderName.setText("�����ˣ�");
		title = new JLabel();
		title.setBounds(new Rectangle(4, 6, 53, 25));
		title.setText("���⣺");
		this.setSize(704, 222);
		this.setLayout(null);
		this.add(title, null);
		this.add(getTitle_c(), null);
		this.add(senderName, null);
		this.add(getSenderName_c(), null);
		this.add(sendtime, null);
		this.add(getSendtime_c(), null);
		this.add(category, null);
		this.add(getCategory_c(), null);
		this.add(readnumber, null);
		this.add(getReadnumber_c(), null);
		//this.add(getContent_c(), null);
		this.add(getJScrollPane(),null);
		this.add(�ָ���, null);
		this.add(getReply(), null);
		this.add(delete, null);
		this.add(top, null);
		this.setOpaque(false);
	}

	/**
	 * This method initializes title_c	
	 * 	������ʾ���ӵı���
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTitle_c() {
		if (title_c == null) {
			title_c = new JTextField();
			title_c.setText(M_post.getTitle());
			title_c.setBounds(new Rectangle(63, 6, 148, 25));
			
			title_c.setEditable(false);
			title_c.setOpaque(false);
			title_c.setBorder(null);
		}
		return title_c;
	}

	/**
	 * This method initializes senderName_c	
	 * 	������ʾ�����˵�����
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSenderName_c() {
		if (senderName_c == null) {
			senderName_c = new JTextField();
			senderName_c.setText(M_post.getSenderUsername()+"  "+M_post.getSenderName());
			senderName_c.setBounds(new Rectangle(74, 171, 123, 26));
			
			senderName_c.setEditable(false);
			senderName_c.setOpaque(false);
			senderName_c.setBorder(null);
		}
		return senderName_c;
	}

	/**
	 * This method initializes sendtime_c	
	 * 	������ʾ����ʱ��
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSendtime_c() {
		if (sendtime_c == null) {
			sendtime_c = new JTextField();
			Calendar calendar = M_post.getTime();		
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			String s=year + "-" + month + "-" + day + " " + hour + ":" + minute;
			sendtime_c.setText(s);
			sendtime_c.setBounds(new Rectangle(277, 171, 115, 26));
			
			sendtime_c.setEditable(false);
			sendtime_c.setOpaque(false);
			sendtime_c.setBorder(null);
		}
		return sendtime_c;
	}

	/**
	 * This method initializes category_c	
	 * 	������ʾ��������
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCategory_c() {
		if (category_c == null) {
			category_c = new JTextField();
			category_c.setText(M_post.getCategory());
			category_c.setBounds(new Rectangle(298, 6, 93, 25));
			
			category_c.setEditable(false);
			category_c.setOpaque(false);
			category_c.setBorder(null);
		}
		return category_c;
	}

	/**
	 * This method initializes readnumber_c	
	 * 	������ʾ���ӵ������
	 * @return javax.swing.JTextField	
	 */
	private JTextField getReadnumber_c() {
		if (readnumber_c == null) {
			readnumber_c = new JTextField();
			readnumber_c.setText(String.valueOf(M_post.getReadSum()));
			readnumber_c.setBounds(new Rectangle(458, 171, 47, 26));
			
			readnumber_c.setEditable(false);
			readnumber_c.setOpaque(false);
			readnumber_c.setBorder(null);
		}
		return readnumber_c;
	}

	/**
	 * This method initializes content_c	
	 * 	������ʾ��������
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getContent_c() {
		if (content_c == null) {
			content_c = new JTextArea(){
				/**
				 * ��textArea�ӱ���
				 */
				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/postC.jpg");
					g.drawImage(img.getImage(),0,0,null); 
					super.paintComponent(g);
			    }
			};
			
			content_c.setLineWrap(true);//�Զ�����
			content_c.setText(M_post.getContent());
			//content_c.setBounds(new Rectangle(4, 34, 694, 133));
			
			content_c.setEditable(false);
			content_c.setOpaque(false);
			content_c.setBorder(null);
		}
		return content_c;
	}
	/**
	 * 
	 * @return
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(4, 34, 680, 133));
			jScrollPane.setViewportView(getContent_c());
			jScrollPane.setOpaque(false);
		}
		return jScrollPane;
	}

	/**
	 * This method initializes reply	
	 * 	�ظ���ť
	 * @return javax.swing.JButton	
	 */
	public JButton getReply() {
		if (reply == null) {
			reply = new JButton(){
				/**
				 * �ػ�����
				 */
				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/buttonbg.png");
					g.drawImage(img.getImage(),0,0,null); 
					
					super.paintComponent(g);
			    }
			};
			reply.setContentAreaFilled(false);
			reply.setOpaque(false);
			reply.setBounds(new Rectangle(574, 171, 60, 24));
			reply.setText("�ظ�");
			reply.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		return reply;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
