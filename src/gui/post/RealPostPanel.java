package gui.post;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import client.login.Login;

import beans.post.Post;
/**���panel��Ҫ���ڰ���������ɫ�����б��tabbedPane����ʾ���ӵ�PostContent�����л�
 * �ð��Ҳ���Ǽ���MainFrame�ϵ�postPanel���
 * ��ʼ����ʾ����������ɫ�����б��tabbedPane
 * */
public class RealPostPanel extends JPanel{
	/**
	 * ��ʼ��
	 */
	private static final long serialVersionUID = 1L;
	public RealPostPanel(){
		this.setOpaque(false);
		init();
	}
	public void init(){
		tabbedPane tb = new tabbedPane();
		this.setLayout(null);
		tb.setBounds(0, 0, 710, 450);
		this.add(tb);
	}
	/**�л�����ʾ�������ݰ��
	 * 
	 * @param tagR  ��ɫ���ţ��鿴��������ʱ�����õĺ�����Ҫ�˲���
	 * @param tagB  
	 * @param post  Ҫ�鿴������
	 */
	public void changeToPost(int tagR, int tagB, Post post){
		this.removeAll();
		ReadPost pc = new ReadPost(tagR,tagB,post){
			/**
			 * �ػ�����
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) { 
		        g.setColor(Color.blue); 
		        ImageIcon img=null;
				img = new ImageIcon("images/file.png");
				g.drawImage(img.getImage(),0,0,null); 
		    }
		};
		pc.setOpaque(false);
		pc.setBounds(0, 0, 710, 450);
		this.add(pc);
		repaint();
	}
	
	/**
	 * * �л������Ӱ��������
	 * @param tagR  ������֤�������Ӻ��л��ص��鿴ǰ��ҳǩ��
	 */
	public void changeTotabbedPane(int tagR){
		this.removeAll();
		tabbedPane tab = new tabbedPane();
		
		if(tagR==1)
		{
			Login.person.updateOneBlock(1);
			tab.tb.setSelectedComponent(tab.student);
		}
		else if(tagR==2)
		{
			Login.person.updateOneBlock(2);
			tab.tb.setSelectedComponent(tab.teacher);
		}
		else if(tagR==3)
		{
			Login.person.updateOneBlock(3);
			tab.tb.setSelectedComponent(tab.jiaowuyuan);
		}
		else if(tagR==4)
		{
			Login.person.updateOneBlock(4);
			tab.tb.setSelectedComponent(tab.admin);
		}
		else if(tagR==0)
		{
			tab.tb.setSelectedComponent(tab.search);
		}
		
		tab.setBounds(0, 0, 710, 450);
		this.add(tab);
		repaint();
	}
	
}
