package gui.post;

import gui.data.DataSet;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import beans.post.Comment;
import beans.post.Post;

public class ReadPost extends JPanel{
	/**
	 * 查看帖子的Panel
	 */
	private static final long serialVersionUID = 1L;
	private Post post_c =null;
	private MakeCommentPanel com = null;
	int tag_r=0;
	int tag_b=0;
	public ReadPost(int tagR, int tagB, Post post){
		post_c=post;
		tag_r=tagR;
		tag_b=tagB;
		init();
	}
	void init(){
		
		JLabel look = new JLabel("帖子内容");
		JButton ret = new JButton("点击返回帖子列表");
		ret.setContentAreaFilled(false);
		ret.setOpaque(false);
		look.setBounds(2, 2, 100, 27);
		ret.setBounds(450, 2, 150, 27);
		ret.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//用来放置帖子内容及该帖子的回复的Panel
		final JPanel p = new JPanel();
		
		//帖子内容板块
		ContentPanel content = new ContentPanel(tag_r,post_c){
			/**
			 * 重画背景
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) { 
		        g.setColor(Color.blue); 
		        ImageIcon img=null;
				img = new ImageIcon("images/postC.jpg");
				g.drawImage(img.getImage(),0,0,null); 
		    }
		};
		content.setBounds(2, 2, 704, 222);
		
		//reply用来放置post中的CommentList
		final ArrayList<Comment> reply = post_c.getCommentList();//得到post中的replylist
		
		//AllReplyPanel用来存放帖子post_c中所有的回复板块
		final JPanel AllReplyPanel = new JPanel();
		AllReplyPanel.setLayout(null);
		for(int i=0;i<reply.size();i++){
			ReplyPanel a = new ReplyPanel(post_c.getTitle(),reply.get(i),(i+1)){
				/**
				 * 重画背景
				 */
				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g) { 
			        g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/reply.jpg");
					g.drawImage(img.getImage(),0,0,null); 
			    }
			};
			a.setBounds(2,i*195 ,704,195);
			AllReplyPanel.add(a);
		}
		AllReplyPanel.setBounds(2, 225, 704, 195*reply.size());
		
		//板块p存放帖子内容版块以及所有该帖子的回复的帖子
		p.setLayout(null);
		p.setOpaque(false);
		p.add(content);
		p.add(AllReplyPanel);
		p.setPreferredSize(new Dimension(710,10000));
		
		//给版块p加上滚动条
		JScrollPane pScroll = new JScrollPane(p);
		pScroll.setOpaque(false);
		pScroll.setBounds(0, 35, 710, 1000);
		
		this.setLayout(null);
		this.setSize(710, 450);
		this.add(look);
		this.add(ret);
		this.add(pScroll);
		this.setOpaque(false);
		
		//对帖子内容板块中按钮“回复”的监听
		content.getReply().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				p.remove(AllReplyPanel);
				com = new MakeCommentPanel(tag_r,tag_b,post_c){
					/**
					 * 重画背景
					 */
					private static final long serialVersionUID = 1L;

					public void paintComponent(Graphics g) { 
				        g.setColor(Color.blue); 
				        ImageIcon img=null;
						img = new ImageIcon("images/comment.jpg");
						g.drawImage(img.getImage(),0,0,null); 
				    }
				};
				com.setBounds(2, 225, 704, 179);
				p.add(com);
				AllReplyPanel.setBounds(2, 400, 704, 195*reply.size());
				p.add(AllReplyPanel);
				updateUI();
				repaint();
			}
		});
		
		//对按钮“返回帖子列表”的监听
		ret.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tag_b==-1)
					DataSet.mainFrame.postPanel.changeTotabbedPane(0);
				else
					DataSet.mainFrame.postPanel.changeTotabbedPane(tag_r);
				DataSet.mainFrame.postPanel.updateUI();
				DataSet.mainFrame.postPanel.repaint();
			}
		});
	}
}
