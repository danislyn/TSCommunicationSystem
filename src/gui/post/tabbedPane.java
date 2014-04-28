package gui.post;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import client.login.Login;
/**
 * 
 * @author Administrator
 *
 */
public class tabbedPane extends JPanel{
	/**
	 * ���Ӱ�������棬����������ɫ���ӵ��б�
	 */
	private static final long serialVersionUID = 1L;
	PostList student = null;
	PostList teacher =  null;
	PostList jiaowuyuan = null;
	PostList admin =  null;
	SearchPanel search =  null;
	JTabbedPane tb = null;
	public tabbedPane (){
		init();
	}
	/**
	 * ����ʼ������ͬ��ɫ��ʼ����ͬ��list
	 */
	void init(){
		this.setLayout(null);
		this.setOpaque(false);
		tb = new JTabbedPane();
		
		student =  new PostList(1,1){
			/**
			 * �ػ�����
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) { 
		        g.setColor(Color.blue); 
		        ImageIcon img=null;
				img = new ImageIcon("images/list.png");
				g.drawImage(img.getImage(),0,0,null); 
		    }
		};
		teacher =  new PostList(2,1){
			/**
			 * �ػ�����
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) { 
		        g.setColor(Color.blue); 
		        ImageIcon img=null;
				img = new ImageIcon("images/list.png");
				g.drawImage(img.getImage(),0,0,null); 
		    }
		};
		jiaowuyuan =  new PostList(3,1){
			/**
			 * �ػ�����
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) { 
		        g.setColor(Color.blue); 
		        ImageIcon img=null;
				img = new ImageIcon("images/list.png");
				g.drawImage(img.getImage(),0,0,null); 
		    }
		};
		
		admin =  new PostList(4,1){
			/**
			 * �ػ�����
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) { 
		        g.setColor(Color.blue); 
		        ImageIcon img=null;
				img = new ImageIcon("images/list.png");
				g.drawImage(img.getImage(),0,0,null); 
		    }
		};
		/*search =  new SearchPanel(){
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) { 
		        g.setColor(Color.blue); 
		        ImageIcon img=null;
				img = new ImageIcon("images/list.png");
				g.drawImage(img.getImage(),0,0,null); 
		    }
		};*/
		/**
		 * �Է��������ƣ�ֻ�����Լ���ɫ�İ�鷢��
		 */
		int authority = Login.person.getAuthority();
		if (authority==2||authority==3||authority==4)
			jiaowuyuan.getNewPost().setEnabled(true);
		else{
			if(authority==5)
				admin.getNewPost().setEnabled(true);
			else{
				if(authority==0)
					student.getNewPost().setEnabled(true);
				else
				{
					if(authority==1)
						teacher.getNewPost().setEnabled(true);
				}
					
			}
		}
		
		tb.addTab("ѧ��",student);
		tb.addTab("�ο���ʦ", teacher);
		tb.addTab("����Ա", jiaowuyuan);
		tb.addTab("����Ա", admin);
//		tb.addTab("����",search );
		tb.setBounds(0, 0, 710, 450);
		tb.setOpaque(false);
		this.add(tb);
	}
}
