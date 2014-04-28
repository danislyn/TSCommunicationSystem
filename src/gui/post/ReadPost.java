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
	 * �鿴���ӵ�Panel
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
		
		JLabel look = new JLabel("��������");
		JButton ret = new JButton("������������б�");
		ret.setContentAreaFilled(false);
		ret.setOpaque(false);
		look.setBounds(2, 2, 100, 27);
		ret.setBounds(450, 2, 150, 27);
		ret.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//���������������ݼ������ӵĻظ���Panel
		final JPanel p = new JPanel();
		
		//�������ݰ��
		ContentPanel content = new ContentPanel(tag_r,post_c){
			/**
			 * �ػ�����
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
		
		//reply��������post�е�CommentList
		final ArrayList<Comment> reply = post_c.getCommentList();//�õ�post�е�replylist
		
		//AllReplyPanel�����������post_c�����еĻظ����
		final JPanel AllReplyPanel = new JPanel();
		AllReplyPanel.setLayout(null);
		for(int i=0;i<reply.size();i++){
			ReplyPanel a = new ReplyPanel(post_c.getTitle(),reply.get(i),(i+1)){
				/**
				 * �ػ�����
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
		
		//���p����������ݰ���Լ����и����ӵĻظ�������
		p.setLayout(null);
		p.setOpaque(false);
		p.add(content);
		p.add(AllReplyPanel);
		p.setPreferredSize(new Dimension(710,10000));
		
		//�����p���Ϲ�����
		JScrollPane pScroll = new JScrollPane(p);
		pScroll.setOpaque(false);
		pScroll.setBounds(0, 35, 710, 1000);
		
		this.setLayout(null);
		this.setSize(710, 450);
		this.add(look);
		this.add(ret);
		this.add(pScroll);
		this.setOpaque(false);
		
		//���������ݰ���а�ť���ظ����ļ���
		content.getReply().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				p.remove(AllReplyPanel);
				com = new MakeCommentPanel(tag_r,tag_b,post_c){
					/**
					 * �ػ�����
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
		
		//�԰�ť�����������б��ļ���
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
