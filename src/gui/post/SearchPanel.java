package gui.post;

import gui.data.DataSet;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;

import client.login.Login;

import beans.post.Post;
import javax.swing.JButton;

public class SearchPanel extends JPanel {
/**
 * 按条件搜索板块
 */
	private static final long serialVersionUID = 1L;
	private JComboBox searchCondition = null;
	private JRadioButton xuexi = null;
	private JRadioButton yule = null;
	private JRadioButton shenghuo = null;
	private JRadioButton qinggan = null;
	private JRadioButton qita = null;
	private JLabel hint = null;
	private JTextField sender_name = null;
	private JList post_list = null;
	private JScrollPane jScrollPane = null;
	private Vector<String> list = null;
	private ArrayList<Post> p = Login.person.searchGlobalByCategory("学习");//p初始化获得与学习有关的帖子，之后根据特定搜索条件获得不同的p
	private JLabel sender = null;
	private JLabel title = null;
	private JLabel time = null;
	private JButton search = null;//  @jve:decl-index=0:
	/**
	 * This is the default constructor
	 */
	public SearchPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		time = new JLabel();
		time.setBounds(new Rectangle(218, 61, 113, 34));
		time.setText("发表时间");
		title = new JLabel();
		title.setBounds(new Rectangle(115, 61, 52, 33));
		title.setText("标题");
		sender = new JLabel();
		sender.setBounds(new Rectangle(17, 61, 52, 31));
		sender.setText("发帖人");
		hint = new JLabel();
		hint.setBounds(new Rectangle(330, 8, 193, 33));
		hint.setText("（请输入发帖人姓名）");
		hint.setVisible(false);
		this.setLayout(null);
		this.setBounds(new Rectangle(0, 0, 704, 400));
		this.add(getSearchCondition(), null);
		this.add(getXuexi(), null);
		this.add(getYule(), null);
		this.add(getShenghuo(), null);
		this.add(getQinggan(), null);
		this.add(getQita(), null);
		this.add(hint, null);
		this.add(getSender_name(), null);
		this.add(getJScrollPane(), null);
		this.add(sender, null);
		this.add(title, null);
		this.add(time, null);
		this.add(getSearch(), null);
	}

	/**
	 * This method initializes searchCondition	
	 * 	搜索条件选择框
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getSearchCondition() {
		if (searchCondition == null) {
			Vector<String> type0=new Vector<String>();
			type0.add("按帖子类型查找");
			type0.add("按发帖人姓名查找");
			searchCondition = new JComboBox(type0);
			searchCondition.setOpaque(false);
			searchCondition.setBorder(null);
			searchCondition.setBounds(new Rectangle(15, 14, 138, 33));
			searchCondition.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated Event stub actionPerformed()
					int index = searchCondition.getSelectedIndex();
					if(index==0){
						shenghuo.setVisible(true);
						yule.setVisible(true);
						qinggan.setVisible(true);
						xuexi.setVisible(true);
						xuexi.setSelected(false);
						qita.setVisible(true);
						
						sender_name.setVisible(false);
						hint.setVisible(false);
						search.setVisible(false);
						
						list.removeAllElements();
						repaint();
					}
					else{
						if(index==1){
							shenghuo.setVisible(false);
							yule.setVisible(false);
							qinggan.setVisible(false);
							xuexi.setVisible(false);
							qita.setVisible(false);
							
							sender_name.setVisible(true);
							hint.setVisible(true);
							search.setVisible(true);
							
							list.removeAllElements();
							repaint();
						}
							
					}
				}
			});
		}
		return searchCondition;
	}

	/**
	 * This method initializes xuexi	
	 * 	选中它就会显示学习有关的帖子
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getXuexi() {
		if (xuexi == null) {
			xuexi = new JRadioButton();
			xuexi.setOpaque(false);
			xuexi.setBounds(new Rectangle(152, 15, 78, 30));
			xuexi.setText("学习");
			xuexi.setSelected(true);
			xuexi.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(xuexi.isSelected()){
						yule.setSelected(false);
						shenghuo.setSelected(false);
						qinggan.setSelected(false);
						qita.setSelected(false);
						
						p=Login.person.searchGlobalByCategory("学习");
						list.removeAllElements();
						if(p!=null){
							for(int i=0;i<p.size();i++)
							{
								Post temp = p.get(i);
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
									list.add("Top"+ad);
								else list.add(ad);
							}
							repaint();
							}
					}
				}
			});
		}
		return xuexi;
	}

	/**
	 * This method initializes yule	
	 * 	选中它就会显示娱乐有关的帖子
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getYule() {
		if (yule == null) {
			yule = new JRadioButton();
			yule.setOpaque(false);
			yule.setBounds(new Rectangle(250, 15, 77, 30));
			yule.setText("娱乐");
			yule.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(yule.isSelected()){
						xuexi.setSelected(false);
						shenghuo.setSelected(false);
						qinggan.setSelected(false);
						qita.setSelected(false);
						
						p=Login.person.searchGlobalByCategory("娱乐");
						list.removeAllElements();
						if(p!=null){
							for(int i=0;i<p.size();i++)
							{
								Post temp = p.get(i);
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
									list.add("Top"+ad);
								else list.add(ad);
							}
							repaint();
							}
					}
				}
				
			});
		}
		return yule;
	}

	/**
	 * This method initializes shenghuo	
	 * 	选中它就会显示生活有关的帖子
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getShenghuo() {
		if (shenghuo == null) {
			shenghuo = new JRadioButton();
			shenghuo.setOpaque(false);
			shenghuo.setBounds(new Rectangle(347, 15, 71, 30));
			shenghuo.setText("生活");
			shenghuo.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(shenghuo.isSelected()){
						xuexi.setSelected(false);
						yule.setSelected(false);
						qinggan.setSelected(false);
						qita.setSelected(false);
						
						p=Login.person.searchGlobalByCategory("生活");
						list.removeAllElements();
						if(p!=null){
							for(int i=0;i<p.size();i++)
							{
								Post temp = p.get(i);
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
									list.add("Top"+ad);
								else list.add(ad);
							}
							repaint();
							}
					}
				}
				
			});
		}
		return shenghuo;
	}

	/**
	 * This method initializes qinggan	
	 * 	选中它就会显示情感有关的帖子
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getQinggan() {
		if (qinggan == null) {
			qinggan = new JRadioButton();
			qinggan.setOpaque(false);
			qinggan.setBounds(new Rectangle(450, 15, 75, 30));
			qinggan.setText("情感");
			qinggan.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(qinggan.isSelected()){
						xuexi.setSelected(false);
						yule.setSelected(false);
						shenghuo.setSelected(false);
						qita.setSelected(false);
						
						p=Login.person.searchGlobalByCategory("情感");
						list.removeAllElements();
						if(p!=null){
							for(int i=0;i<p.size();i++)
							{
								Post temp = p.get(i);
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
									list.add("Top"+ad);
								else list.add(ad);
							}
							repaint();
							}
					}
				}
				
			});
		}
		return qinggan;
	}

	/**
	 * This method initializes qita	
	 * 	选中它就会显示其它的帖子
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getQita() {
		if (qita == null) {
			qita = new JRadioButton();
			qita.setOpaque(false);
			qita.setBounds(new Rectangle(561, 15, 78, 30));
			qita.setText("其它");
			qita.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(qita.isSelected()){
						xuexi.setSelected(false);
						yule.setSelected(false);
						qinggan.setSelected(false);
						shenghuo.setSelected(false);
						
						p=Login.person.searchGlobalByCategory("其它");
						list.removeAllElements();
						if(p!=null){
							for(int i=0;i<p.size();i++)
							{
								Post temp = p.get(i);
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
									list.add("Top"+ad);
								else list.add(ad);
							}
							repaint();
							}
					}
				}
				
			});
		}
		return qita;
	}

	/**
	 * This method initializes sender_name	
	 * 	按人名搜索时要用户输入人名之处
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSender_name() {
		if (sender_name == null) {
			sender_name = new JTextField();
			sender_name.setBounds(new Rectangle(197, 10, 118, 33));
			sender_name.setVisible(false);
		}
		return sender_name;
	}

	/**
	 * This method initializes post_list	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getPost_list() {
		list = new Vector<String>();
		for(int i=0;i<p.size();i++)
		{
			Post temp = p.get(i);
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
				list.add("Top"+ad);
			else list.add(ad);
		}
		if (post_list == null) {
			post_list = new JList(list);
		}
		post_list.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated Event stub mouseClicked()
				
				//监听双击
				if(e.getClickCount() == 2)
				{
					int index = post_list.getSelectedIndex();
					
					if(index != -1)
					{
						DataSet.mainFrame.postPanel.changeToPost(p.get(index).getTag(),-1,p.get(index));
						Login.person.readPost(p.get(index).getTag(), p.get(index));
						DataSet.mainFrame.postPanel.updateUI();
						DataSet.mainFrame.postPanel.repaint();
					}
					}
			}});
		return post_list;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	将JList放入ScrollPane中
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(9, 100, 680, 283));
			jScrollPane.setViewportView(getPost_list());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes search	
	 * 	按人名搜索时显示的搜索按钮，点击它获得符合条件的帖子列表
	 * @return javax.swing.JButton	
	 */
	private JButton getSearch() {
		if (search == null) {
			search = new JButton(){
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
			search.setOpaque(false);
			search.setContentAreaFilled(false);
			search.setBounds(new Rectangle(515, 8, 97, 30));
			search.setText("搜索");
			search.setVisible(false);
			search.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated Event stub actionPerformed()
					
					p=Login.person.searchGlobalBySenderName(sender_name.getText());
					list.removeAllElements();
					if(p!=null){
						for(int i=0;i<p.size();i++)
						{
							Post temp = p.get(i);
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
								list.add("Top"+ad);
							else list.add(ad);
						}
						repaint();
					}
					else{
						post_list = new JList(list);
						repaint();
					}
				}
			});
		}
		return search;
	}

}  //  @jve:decl-index=0:visual-constraint="9,11"
