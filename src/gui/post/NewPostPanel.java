package gui.post;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import beans.post.Post;
import client.login.Login;

public class NewPostPanel extends JPanel {
/**
 * 发表新帖板块
 */
	private static final long serialVersionUID = 1L;
	private JLabel title = null;
	private JTextField title_c = null;
	private JLabel category = null;
	private JComboBox category_c = null;
	private JTextArea jTextArea = null;
	private JButton sure = null;
	private JButton cancel = null;
	private int tag_r=0;
	private int tag_b=0;
	private JScrollPane jScrollPane = null;
	/**
	 * 
	 * @param tagR  帖子所在版块
	 * @param tagB
	 */
	public NewPostPanel(int tagR,int tagB) {
		super();
		tag_r=tagR;
		tag_b=tagB;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		category = new JLabel();
		category.setBounds(new Rectangle(8, 49, 57, 26));
		category.setText("类别：");
		title = new JLabel();
		title.setBounds(new Rectangle(8, 12, 60, 26));
		title.setText("标题：");
		this.setLayout(null);
		this.add(title, null);
		this.add(getTitle_c(), null);
		this.add(category, null);
		this.add(getCategory_c(), null);
		this.add(getSure(), null);
		this.add(getCancel(), null);
		this.add(getJScrollPane(), null);
		this.setOpaque(false);
		this.setBounds(new Rectangle(0, 0, 702, 440));
	}

	/**
	 * This method initializes title_c	
	 * 	放置发表的新帖的标题
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTitle_c() {
		if (title_c == null) {
			title_c = new JTextField();
			title_c.setBounds(new Rectangle(80, 12, 131, 26));
		}
		return title_c;
	}

	/**
	 * This method initializes category_c	
	 * 	放置发表的新帖的类别
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCategory_c() {
		if (category_c == null) {
			Vector<String> type0=new Vector<String>();
			type0.add("学习");
			type0.add("娱乐");
			type0.add("生活");
			type0.add("情感");
			type0.add("其它");
			category_c = new JComboBox(type0);
			category_c.setBounds(new Rectangle(80, 48, 125, 28));
		}
		return category_c;
	}

	/**
	 * This method initializes jTextArea	
	 * 	放置发表的新帖的内容
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
		}
		return jTextArea;
	}

	/**
	 * This method initializes sure	
	 * 	确定按钮
	 * @return javax.swing.JButton	
	 */
	private JButton getSure() {
		if (sure == null) {
			sure = new JButton(){
				/**
				 * 更改背景
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
			sure.setContentAreaFilled(false);
			sure.setOpaque(false);
			sure.setBounds(new Rectangle(381, 375, 88, 34));
			sure.setText("确定");
			sure.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			//对确定按钮的监听，从界面上取出内容包装成post存入数据库
			sure.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Calendar date = Calendar.getInstance();
					Post p = new Post();
					p.setTime(date);
					p.setTitle(title_c.getText());
					p.setContent(jTextArea.getText());
					
					int i=category_c.getSelectedIndex();
					if(i==0)
						p.setCategory("学习");
					else{if(i==1)
						p.setCategory("娱乐");
					else{
						if(i==2)
							p.setCategory("生活");
						else{
							if(i==3)
								p.setCategory("情感");
							else
								p.setCategory("其它");
						}
					}
					}
					p.setSenderName(Login.person.getName());
					p.setSenderUsername(Login.person.getUsername());
					p.setReadSum(0);
					p.setIsTop(false);
					p.setCommentsSum(0);
					if(Login.person.sendPost(p)==true){
						JOptionPane.showMessageDialog(null, "发表成功", "提示", JOptionPane.INFORMATION_MESSAGE);	
						Login.person.updateAllBlock();
						change();
						repaint();
					}
					else
						JOptionPane.showMessageDialog(null, "不好意思，发表失败，可能网络不稳定", "提示", JOptionPane.INFORMATION_MESSAGE);
					}	
		});		
	}
		return sure;
	}

	/**
	 * This method initializes cancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancel() {
		if (cancel == null) {
			cancel = new JButton(){
				/**
				 * 更改背景
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
			cancel.setContentAreaFilled(false);
			cancel.setOpaque(false);
			cancel.setBounds(new Rectangle(531, 375, 88, 34));
			cancel.setText("取消");
			cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			//对取消的监听
			cancel.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					change();
					repaint();
				}
		});		
		}
		return cancel;
	}
	/**
	 * 帖子列表板块和发表新帖板块的切换
	 */
	public void change(){
		this.removeAll();
		PostList a = new PostList(tag_r,tag_b);
		a.getNewPost().setEnabled(true);
		a.setBounds(0, 0, 702, 430);
		this.add(a);
		this.updateUI();
		repaint();
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(10, 92, 686, 272));
			jScrollPane.setViewportView(getJTextArea());
			jScrollPane.setOpaque(false);
		}
		return jScrollPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
