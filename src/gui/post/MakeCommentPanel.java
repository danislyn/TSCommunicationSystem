package gui.post;

import gui.data.DataSet;

import java.awt.Color;
import java.awt.Cursor;
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

import beans.post.Comment;
import beans.post.Post;
import client.login.Login;

public class MakeCommentPanel extends JPanel {
/**
 * 评论帖子板块
 */
	private static final long serialVersionUID = 1L;
	private JLabel Re = null;
	private JTextField Re_c = null;
	private JButton submit = null;
	private JButton cancel = null;
	private JLabel 分割线 = null;
	private JTextArea reply_c = null;
	private JScrollPane jScrollPane = null;
	private Post post = null;
	private int tag_r=0;
	private int tag_b=0;

	/**
	 * 
	 * @param tagR    要做出评论的帖子所在版块
	 * @param tagB
	 * @param postC   要做出评论的帖子
	 */
	public MakeCommentPanel(int tagR, int tagB, Post postC) {
		super();
		tag_r = tagR;
		tag_b = tagB;
		post=postC;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		分割线 = new JLabel();
		分割线.setBounds(new Rectangle(5, 166, 696, 6));
		分割线.setText("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		Re = new JLabel();
		Re.setBounds(new Rectangle(8, 3, 41, 19));
		Re.setText("Re:");
		this.setLayout(null);
		
		this.add(Re, null);
		this.add(getRe_c(), null);
		this.add(getJScrollPane(), null);
		this.add(getSubmit(), null);
		this.add(getCancel(), null);
		this.add(分割线, null);
		this.setBounds(new Rectangle(0, 0, 704, 179));
		this.setOpaque(false);
	}

	/**
	 * This method initializes Re_c	
	 * 	填充要评论帖子的内容
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRe_c() {
		if (Re_c == null) {
			Re_c = new JTextField();
			Re_c.setBounds(new Rectangle(67, 3, 161, 21));
			Re_c.setText(post.getTitle());
			
			Re_c.setOpaque(false);
			Re_c.setEditable(false);
			Re_c.setBorder(null);
		}
		return Re_c;
	}

	/**
	 * This method initializes reply_c	
	 * 	评论的内容
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getReply_c() {
		if (reply_c == null) {
			reply_c = new JTextArea();
			reply_c.setEditable(true);
		}
		return reply_c;
	}

	/**
	 * 给JTextArea添加滚动条
	 * @return
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(9, 27, 680, 101));
			jScrollPane.setViewportView(getReply_c());
			jScrollPane.setOpaque(false);
		}
		return jScrollPane;
	}

	/**
	 * This method initializes submit	
	 * 	提交按钮
	 * @return javax.swing.JButton	
	 */
	private JButton getSubmit() {
		if (submit == null) {
			submit = new JButton(){
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
			submit.setContentAreaFilled(false);
			submit.setOpaque(false);
			submit.setBounds(new Rectangle(430, 139, 76, 24));
			submit.setText("提交");
			submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			//对提交按钮的监听，将评论的内容上传至数据库
			submit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated Event stub actionPerformed()
					System.out.println("hello");
					Calendar time = Calendar.getInstance();
					Comment com = new Comment();
					com.setSenderUsername(Login.person.getUsername());
					com.setSenderName(Login.person.getName());
					com.setContent(reply_c.getText());
					com.setTime(time);
					
					boolean t = Login.person.replyPost(tag_r, post, com);
					if(t==true){
						JOptionPane.showMessageDialog(null, "回复成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						DataSet.mainFrame.postPanel.changeToPost(tag_r,tag_b,post);
						DataSet.mainFrame.postPanel.updateUI();
						DataSet.mainFrame.postPanel.repaint();
						repaint();
					}
					else{
						JOptionPane.showMessageDialog(null, "不好意思，回复失败，可能网络不稳定", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}
		return submit;
	}

	/**
	 * This method initializes cancel	
	 * 	取消按钮
	 * @return javax.swing.JButton	
	 */
	public JButton getCancel() {
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
			cancel.setBounds(new Rectangle(550, 137, 69, 27));
			cancel.setText("取消");
			cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			//对取消按钮的监听
			cancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated Event stub actionPerformed()
					DataSet.mainFrame.postPanel.changeToPost(tag_r,tag_b,post);
					DataSet.mainFrame.postPanel.updateUI();
					DataSet.mainFrame.postPanel.repaint();
					repaint();
				}
			});
		}
		return cancel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
