package gui.post;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import client.login.Login;

import beans.post.Post;
/**这个panel主要用于包含各个角色帖子列表的tabbedPane与显示帖子的PostContent板块的切换
 * 该板块也就是加在MainFrame上的postPanel组件
 * 初始化显示包含各个角色帖子列表的tabbedPane
 * */
public class RealPostPanel extends JPanel{
	/**
	 * 初始化
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
	/**切换至显示帖子内容板块
	 * 
	 * @param tagR  角色板块号，查看帖子内容时，调用的函数需要此参数
	 * @param tagB  
	 * @param post  要查看的帖子
	 */
	public void changeToPost(int tagR, int tagB, Post post){
		this.removeAll();
		ReadPost pc = new ReadPost(tagR,tagB,post){
			/**
			 * 重画背景
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
	 * * 切换至帖子版块主界面
	 * @param tagR  用来保证看完帖子后切换回到查看前的页签面
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
