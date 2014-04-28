package gui.post;

import gui.data.DataSet;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JScrollPane;
import beans.post.Post;
import javax.swing.JButton;

import client.login.Login;

public class PostList extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel sender_name = null;
	private JLabel biaoti = null;
	private JList senderList = null;
	private JScrollPane senderScrollPane = null;
	private Vector<String> list  = null;
	private JLabel time = null;
	private JButton newPost = null;
	private ArrayList<Post> postList = new ArrayList<Post>();  //  @jve:decl-index=0:
	private int tag_r=0;
	private int tag_b=0;
	
	/**
	 * 
	 * @param role ���Ӱ��
	 * @param block
	 */
	public PostList(int role,int block) {
		super();
		tag_r=role;
		tag_b=block;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		time = new JLabel();
		time.setBounds(new Rectangle(218, 7, 81, 22));
		time.setText("����ʱ��");
		biaoti = new JLabel();
		biaoti.setBounds(new Rectangle(106, 7, 81, 22));
		biaoti.setText("����");
		sender_name = new JLabel();
		sender_name.setBounds(new Rectangle(7, 6, 72, 23));
		sender_name.setText("������");
		
		this.setSize(700, 440);
		this.setLayout(null);
		this.add(sender_name, null);
		this.add(biaoti, null);
		this.add(getSenderScrollPane(), null);
		this.add(time, null);
		this.add(getNewPost(), null);
		this.setOpaque(false);
	}

	/**
	 * This method initializes senderList	
	 * 	SenderList�з��÷��������������б�
	 * @return javax.swing.JList	
	 */
	private JList getSenderList() {
		//list�ǽ�Post����ת��ΪString���ͣ������շ��õ�JList SenderList��
		list = new Vector<String>();
		//����roleȷ��list��Ҫ��õ����Ŀ�PostList
		DataSet.updatePostList();
		if(tag_r==1)
			postList=DataSet.postList1;
		else{
			if(tag_r==2)
				postList=DataSet.postList2;
			else{
				if(tag_r==3)
					postList=DataSet.postList3;
				else if(tag_r==4)
						postList=DataSet.postList4;
			}
		}
			
		for(int i=0;i<postList.size();i++)
		{
			Post temp = postList.get(i);
			Calendar calendar = temp.getTime();		
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			String s=year + "-" + month + "-" + day + " " + hour + ":" + minute;
			String empty = "                      ";
			String ad = temp.getSenderUsername()+temp.getSenderName()+"           "+temp.getTitle()+empty+s;
			if(temp.getIsTop())
				list.add("Top"+ad);    //����ö�
			else list.add(ad);
		}
		
		if (senderList == null) {
			senderList = new JList(list);
		}
		
		senderList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				 // TODO Auto-generated Event stub mouseClicked()
				
				//����˫��
				if(e.getClickCount() == 2)
				{
					int index = senderList.getSelectedIndex();
					
					if(index != -1)
					{
						Login.person.readPost(tag_r, postList.get(index));
						DataSet.mainFrame.postPanel.changeToPost(tag_r,tag_b,postList.get(index));
						DataSet.mainFrame.postPanel.updateUI();
						DataSet.mainFrame.postPanel.repaint();
					}
					}
			}});
		return senderList;
		
	}
	
	
	/**
	 * This method initializes senderScrollPane	
	 * 	��SenderList���ScrollPane
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSenderScrollPane() {
		if (senderScrollPane == null) {
			senderScrollPane = new JScrollPane();
			senderScrollPane.setOpaque(false);
			senderScrollPane.setBounds(new Rectangle(9, 33, 691, 370));
			senderScrollPane.setViewportView(getSenderList());
		}
		return senderScrollPane;
	}

	/**
	 * This method initializes newPost	
	 * 	����������ť
	 * @return javax.swing.JButton	
	 */
	protected JButton getNewPost() {
		if (newPost == null) {
			newPost = new JButton(){
				/**
				 * ���ı���
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
			newPost.setEnabled(false);
			newPost.setContentAreaFilled(false);
			newPost.setOpaque(false);
			newPost.setBounds(new Rectangle(526, 4, 86, 24));
			newPost.setText("��������");
			newPost.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		//�԰�ť�ļ���
		newPost.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				change();
				updateUI();
				repaint();
			}
		});
		return newPost;
	}
	/**
	 * �л�������������ť
	 */
	public void change(){
		this.removeAll();
		NewPostPanel a = new NewPostPanel(tag_r,tag_b);
		a.setBounds(0, 0, 704, 430);
		this.add(a);
		this.updateUI();
		repaint();
	}
}  //  @jve:decl-index=0:visual-constraint="12,7"
